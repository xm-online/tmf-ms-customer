package com.icthh.xm.tmf.ms.customer.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.icthh.xm.tmf.ms.customer.model.TimePeriod;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Credit profile for the party (containing credit scoring, ...). By default only the current credit profile  is retrieved. It can be used as a list to give the party credit profiles history, the first one in the list will be the current one.
 */

@Schema(name = "CreditProfile", description = "Credit profile for the party (containing credit scoring, ...). By default only the current credit profile  is retrieved. It can be used as a list to give the party credit profiles history, the first one in the list will be the current one.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.682086418+02:00[Europe/Kyiv]")
public class CreditProfile   {

  @JsonProperty("baseType")
  private String baseType;

  @JsonProperty("schemaLocation")
  private String schemaLocation;

  @JsonProperty("type")
  private String type;

  @JsonProperty("creditProfileDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime creditProfileDate;

  @JsonProperty("creditRiskRating")
  private Integer creditRiskRating;

  @JsonProperty("creditScore")
  private Integer creditScore;

  @JsonProperty("validFor")
  private TimePeriod validFor;

  public CreditProfile baseType(String baseType) {
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

  public CreditProfile schemaLocation(String schemaLocation) {
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

  public CreditProfile type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Generic attribute containing the name of the resource class type
   * @return type
  */
  
  @Schema(name = "type", description = "Generic attribute containing the name of the resource class type", required = false)
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public CreditProfile creditProfileDate(OffsetDateTime creditProfileDate) {
    this.creditProfileDate = creditProfileDate;
    return this;
  }

  /**
   * The date the profile was established
   * @return creditProfileDate
  */
  @NotNull @Valid 
  @Schema(name = "creditProfileDate", description = "The date the profile was established", required = true)
  public OffsetDateTime getCreditProfileDate() {
    return creditProfileDate;
  }

  public void setCreditProfileDate(OffsetDateTime creditProfileDate) {
    this.creditProfileDate = creditProfileDate;
  }

  public CreditProfile creditRiskRating(Integer creditRiskRating) {
    this.creditRiskRating = creditRiskRating;
    return this;
  }

  /**
   * This is an integer whose value is used to rate the risk
   * @return creditRiskRating
  */
  
  @Schema(name = "creditRiskRating", description = "This is an integer whose value is used to rate the risk", required = false)
  public Integer getCreditRiskRating() {
    return creditRiskRating;
  }

  public void setCreditRiskRating(Integer creditRiskRating) {
    this.creditRiskRating = creditRiskRating;
  }

  public CreditProfile creditScore(Integer creditScore) {
    this.creditScore = creditScore;
    return this;
  }

  /**
   * A measure of a person's or an organization's creditworthiness calculated on the basis of a combination of factors such as their income and credit history.
   * @return creditScore
  */
  
  @Schema(name = "creditScore", description = "A measure of a person's or an organization's creditworthiness calculated on the basis of a combination of factors such as their income and credit history.", required = false)
  public Integer getCreditScore() {
    return creditScore;
  }

  public void setCreditScore(Integer creditScore) {
    this.creditScore = creditScore;
  }

  public CreditProfile validFor(TimePeriod validFor) {
    this.validFor = validFor;
    return this;
  }

  /**
   * Get validFor
   * @return validFor
  */
  @NotNull @Valid 
  @Schema(name = "validFor", required = true)
  public TimePeriod getValidFor() {
    return validFor;
  }

  public void setValidFor(TimePeriod validFor) {
    this.validFor = validFor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreditProfile creditProfile = (CreditProfile) o;
    return Objects.equals(this.baseType, creditProfile.baseType) &&
        Objects.equals(this.schemaLocation, creditProfile.schemaLocation) &&
        Objects.equals(this.type, creditProfile.type) &&
        Objects.equals(this.creditProfileDate, creditProfile.creditProfileDate) &&
        Objects.equals(this.creditRiskRating, creditProfile.creditRiskRating) &&
        Objects.equals(this.creditScore, creditProfile.creditScore) &&
        Objects.equals(this.validFor, creditProfile.validFor);
  }

  @Override
  public int hashCode() {
    return Objects.hash(baseType, schemaLocation, type, creditProfileDate, creditRiskRating, creditScore, validFor);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreditProfile {\n");
    sb.append("    baseType: ").append(toIndentedString(baseType)).append("\n");
    sb.append("    schemaLocation: ").append(toIndentedString(schemaLocation)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    creditProfileDate: ").append(toIndentedString(creditProfileDate)).append("\n");
    sb.append("    creditRiskRating: ").append(toIndentedString(creditRiskRating)).append("\n");
    sb.append("    creditScore: ").append(toIndentedString(creditScore)).append("\n");
    sb.append("    validFor: ").append(toIndentedString(validFor)).append("\n");
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

