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
 * Describes a given characteristic of an object or entity through a name/value pair.
 */

@Schema(name = "Characteristic", description = "Describes a given characteristic of an object or entity through a name/value pair.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.814388773+02:00[Europe/Kyiv]")
public class Characteristic   {

  @JsonProperty("name")
  private String name;

  @JsonProperty("value")
  private String value;

  public Characteristic name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the characteristic
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "Name of the characteristic", required = true)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Characteristic value(String value) {
    this.value = value;
    return this;
  }

  /**
   * The value of the characteristic provided as a string.
   * @return value
  */
  @NotNull 
  @Schema(name = "value", description = "The value of the characteristic provided as a string.", required = true)
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
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
    Characteristic characteristic = (Characteristic) o;
    return Objects.equals(this.name, characteristic.name) &&
        Objects.equals(this.value, characteristic.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Characteristic {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

