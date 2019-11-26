package com.icthh.xm.tmf.ms.customer.config;

import com.codahale.metrics.MetricRegistry;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import com.zaxxer.hikari.HikariDataSource;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableMetrics(proxyTargetClass = true)
public class AppMetricsConfiguration extends MetricsConfigurerAdapter {

    private final Logger log = LoggerFactory.getLogger(AppMetricsConfiguration.class);


    private final MetricRegistry metricRegistry;
    private final PrometheusMeterRegistry prometheusMeterRegistry;

    public AppMetricsConfiguration(@Qualifier("getMetricRegistry") MetricRegistry metricRegistry,
                                   PrometheusMeterRegistry prometheusMeterRegistry) {
        this.metricRegistry = metricRegistry;
        this.prometheusMeterRegistry = prometheusMeterRegistry;
    }

    private HikariDataSource hikariDataSource;

    @Autowired(required = false)
    public void setHikariDataSource(HikariDataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
    }

    @PostConstruct
    public void init() {
        prometheusMeterRegistry.getPrometheusRegistry().register(new DropwizardExports(metricRegistry));
        if (hikariDataSource != null) {
            log.debug("Monitoring the datasource");
            // remove the factory created by HikariDataSourceMetricsPostProcessor until JHipster migrate to Micrometer
            hikariDataSource.setMetricsTrackerFactory(null);
            hikariDataSource.setMetricRegistry(metricRegistry);
        }
    }
}
