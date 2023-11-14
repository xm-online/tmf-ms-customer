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
 *  Patch operation object corresponding to json-patch+json specification
 */

@Schema(name = "Patch_Operation", description = " Patch operation object corresponding to json-patch+json specification")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.814388773+02:00[Europe/Kyiv]")
public class PatchOperation   {

  @JsonProperty("op")
  private String op;

  @JsonProperty("path")
  private String path;

  @JsonProperty("value")
  private Object value;

  public PatchOperation op(String op) {
    this.op = op;
    return this;
  }

  /**
   * Operation type: 'add', 'remove', 'replace'
   * @return op
  */
  
  @Schema(name = "op", description = "Operation type: 'add', 'remove', 'replace'", required = false)
  public String getOp() {
    return op;
  }

  public void setOp(String op) {
    this.op = op;
  }

  public PatchOperation path(String path) {
    this.path = path;
    return this;
  }

  /**
   * Json pointer, e.g. '/characteristic/-'
   * @return path
  */
  
  @Schema(name = "path", description = "Json pointer, e.g. '/characteristic/-'", required = false)
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public PatchOperation value(Object value) {
    this.value = value;
    return this;
  }

  /**
   * Get value
   * @return value
  */
  
  @Schema(name = "value", required = false)
  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PatchOperation patchOperation = (PatchOperation) o;
    return Objects.equals(this.op, patchOperation.op) &&
        Objects.equals(this.path, patchOperation.path) &&
        Objects.equals(this.value, patchOperation.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(op, path, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PatchOperation {\n");
    sb.append("    op: ").append(toIndentedString(op)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

