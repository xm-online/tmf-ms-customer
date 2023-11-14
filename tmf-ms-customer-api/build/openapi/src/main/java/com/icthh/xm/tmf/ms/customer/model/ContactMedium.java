package com.icthh.xm.tmf.ms.customer.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.icthh.xm.tmf.ms.customer.model.MediumCharacteristic;
import com.icthh.xm.tmf.ms.customer.model.TimePeriod;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Indicates the contact medium that could be used to contact the party.
 */

@Schema(name = "ContactMedium", description = "Indicates the contact medium that could be used to contact the party.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.682086418+02:00[Europe/Kyiv]")
public class ContactMedium   {

  @JsonProperty("baseType")
  private String baseType;

  @JsonProperty("schemaLocation")
  private String schemaLocation;

  @JsonProperty("preferred")
  private Boolean preferred;

  @JsonProperty("type")
  private String type;

  @JsonProperty("validFor")
  private TimePeriod validFor;

  @JsonProperty("characteristic")
  private MediumCharacteristic characteristic;

  public ContactMedium baseType(String baseType) {
    this.baseType = baseType;
    return this;
  }

  /**
   * Generic attribute indicating the base class type of the extension class of the current object. Useful only when the class type of the current  object is unknown to the implementation.
   * @return baseType
  */
  
  @Schema(name = "baseType", description = "Generic attribute indicating the base class type of the extension class of the current object. Useful only when the class type of the current  object is unknown to the implementation.", required = false)
  public String getBaseType() {
    return baseType;
  }

  public void setBaseType(String baseType) {
    this.baseType = baseType;
  }

  public ContactMedium schemaLocation(String schemaLocation) {
    this.schemaLocation = schemaLocation;
    return this;
  }

  /**
   * Generic attribute containing the link to the schema that defines the structure of the class type of the current object.
   * @return schemaLocation
  */
  
  @Schema(name = "schemaLocation", description = "Generic attribute containing the link to the schema that defines the structure of the class type of the current object.", required = false)
  public String getSchemaLocation() {
    return schemaLocation;
  }

  public void setSchemaLocation(String schemaLocation) {
    this.schemaLocation = schemaLocation;
  }

  public ContactMedium preferred(Boolean preferred) {
    this.preferred = preferred;
    return this;
  }

  /**
   * If true, indicates that is the preferred contact medium
   * @return preferred
  */
  
  @Schema(name = "preferred", description = "If true, indicates that is the preferred contact medium", required = false)
  public Boolean getPreferred() {
    return preferred;
  }

  public void setPreferred(Boolean preferred) {
    this.preferred = preferred;
  }

  public ContactMedium type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Type of the contact medium, such as: email address, telephone number, postal address
   * @return type
  */
  @NotNull 
  @Schema(name = "type", description = "Type of the contact medium, such as: email address, telephone number, postal address", required = true)
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ContactMedium validFor(TimePeriod validFor) {
    this.validFor = validFor;
    return this;
  }

  /**
   * Get validFor
   * @return validFor
  */
  @Valid 
  @Schema(name = "validFor", required = false)
  public TimePeriod getValidFor() {
    return validFor;
  }

  public void setValidFor(TimePeriod validFor) {
    this.validFor = validFor;
  }

  public ContactMedium characteristic(MediumCharacteristic characteristic) {
    this.characteristic = characteristic;
    return this;
  }

  /**
   * Get characteristic
   * @return characteristic
  */
  @NotNull @Valid 
  @Schema(name = "characteristic", required = true)
  public MediumCharacteristic getCharacteristic() {
    return characteristic;
  }

  public void setCharacteristic(MediumCharacteristic characteristic) {
    this.characteristic = characteristic;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ContactMedium contactMedium = (ContactMedium) o;
    return Objects.equals(this.baseType, contactMedium.baseType) &&
        Objects.equals(this.schemaLocation, contactMedium.schemaLocation) &&
        Objects.equals(this.preferred, contactMedium.preferred) &&
        Objects.equals(this.type, contactMedium.type) &&
        Objects.equals(this.validFor, contactMedium.validFor) &&
        Objects.equals(this.characteristic, contactMedium.characteristic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(baseType, schemaLocation, preferred, type, validFor, characteristic);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContactMedium {\n");
    sb.append("    baseType: ").append(toIndentedString(baseType)).append("\n");
    sb.append("    schemaLocation: ").append(toIndentedString(schemaLocation)).append("\n");
    sb.append("    preferred: ").append(toIndentedString(preferred)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    validFor: ").append(toIndentedString(validFor)).append("\n");
    sb.append("    characteristic: ").append(toIndentedString(characteristic)).append("\n");
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

