import java.nio.charset.StandardCharsets
import java.util.Base64

import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.LoggerContext
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
  * Performance test for customer api.
  */
class CustomerGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://127.0.0.1:8711/"""

    val httpConf = http
        .baseUrl(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")

    val accept_application_json_header = Map(
        "Accept" -> """application/json"""
    )

    val authorization_token = "Basic " + Base64.getEncoder.encodeToString("web_app:".getBytes(StandardCharsets.UTF_8))

    val headers_http_authentication = Map(
        "Content-Type" -> """application/x-www-form-urlencoded""",
        "Accept" -> """application/json""",
        "Authorization" -> authorization_token
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "Bearer ${access_token}"
    )

    val scn = scenario("Test customer API")
        .exec(http("First unauthenticated request")
            .get("/api/customerManagement/v3/customer/1?profile=xm&fields=firebase_id")
            .headers(accept_application_json_header)
            .check(status.is(401))).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
            .post("/uaa/oauth/token")
            .headers(headers_http_authentication)
            .formParam("username", "admin")
            .formParam("password", "admin")
            .formParam("grant_type", "password")
            .check(jsonPath("$.access_token").saveAs("access_token"))).exitHereIfFailed
        .pause(1)
        .exec(http("Authenticated request")
            .get("/api/customerManagement/v3/customer/1?profile=xm&fields=firebase_id")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get customer attributes")
                .get("/api/customerManagement/v3/customer/1?profile=xm&fields=firebase_id")
                .headers(headers_http_authenticated)
                .check(status.is(200)))
                .pause(10 seconds, 20 seconds)
                .exec(http("Update customer attribute")
                    .patch("/api/customerManagement/v3/customer/1")
                    .headers(headers_http_authenticated)
                    .body(StringBody("""{"characteristic": [{"key": "language","value": "uk","default": true}]}""")).asJson
                    .check(status.is(200)))
                .pause(10)
                .repeat(5) {
                    exec(http("Get patched attribute")
                        .get("/api/customerManagement/v3/customer/1?profile=xm&fields=language")
                        .headers(headers_http_authenticated))
                        .pause(10)
                }
        }

    val users = scenario("Users").exec(scn)

    setUp(users.inject(rampUsers(5) during (1 minutes))).protocols(httpConf)
}
