package com.icthh.xm.tmf.ms.customer.model.v31;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Error
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.814388773+02:00[Europe/Kyiv]")
public class Error   {

  @JsonProperty("code")
  private Integer code;

  @JsonProperty("reason")
  private Integer reason;

  @JsonProperty("message")
  private String message;

  @JsonProperty("status")
  private Integer status;

  @JsonProperty("referenceError")
  private String referenceError;

  @JsonProperty("type")
  private String type;

  @JsonProperty("schemaLocation")
  private String schemaLocation;

  public Error code(Integer code) {
    this.code = code;
    return this;
  }

  /**
   * Application related code.
   * @return code
  */
  @NotNull 
  @Schema(name = "code", description = "Application related code.", required = true)
  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public Error reason(Integer reason) {
    this.reason = reason;
    return this;
  }

  /**
   * Text that explains the reason for error.
   * @return reason
  */
  @NotNull 
  @Schema(name = "reason", description = "Text that explains the reason for error.", required = true)
  public Integer getReason() {
    return reason;
  }

  public void setReason(Integer reason) {
    this.reason = reason;
  }

  public Error message(String message) {
    this.message = message;
    return this;
  }

  /**
   * (optional) Text that provide more details and corrective actions related to the error.
   * @return message
  */
  
  @Schema(name = "message", description = "(optional) Text that provide more details and corrective actions related to the error.", required = false)
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Error status(Integer status) {
    this.status = status;
    return this;
  }

  /**
   * (optional) http error code extension like 400-2
   * @return status
  */
  
  @Schema(name = "status", description = "(optional) http error code extension like 400-2", required = false)
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Error referenceError(String referenceError) {
    this.referenceError = referenceError;
    return this;
  }

  /**
   * (optional) A URL to online documentation that provides more information about the error.
   * @return referenceError
  */
  
  @Schema(name = "referenceError", description = "(optional) A URL to online documentation that provides more information about the error.", required = false)
  public String getReferenceError() {
    return referenceError;
  }

  public void setReferenceError(String referenceError) {
    this.referenceError = referenceError;
  }

  public Error type(String type) {
    this.type = type;
    return this;
  }

  /**
   * (optional) The class type of a REST resource.
   * @return type
  */
  
  @Schema(name = "type", description = "(optional) The class type of a REST resource.", required = false)
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Error schemaLocation(String schemaLocation) {
    this.schemaLocation = schemaLocation;
    return this;
  }

  /**
   * (optional) A link to the schema describing a REST resource.
   * @return schemaLocation
  */
  
  @Schema(name = "schemaLocation", description = "(optional) A link to the schema describing a REST resource.", required = false)
  public String getSchemaLocation() {
    return schemaLocation;
  }

  public void setSchemaLocation(String schemaLocation) {
    this.schemaLocation = schemaLocation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.code, error.code) &&
        Objects.equals(this.reason, error.reason) &&
        Objects.equals(this.message, error.message) &&
        Objects.equals(this.status, error.status) &&
        Objects.equals(this.referenceError, error.referenceError) &&
        Objects.equals(this.type, error.type) &&
        Objects.equals(this.schemaLocation, error.schemaLocation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, reason, message, status, referenceError, type, schemaLocation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    referenceError: ").append(toIndentedString(referenceError)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    schemaLocation: ").append(toIndentedString(schemaLocation)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

