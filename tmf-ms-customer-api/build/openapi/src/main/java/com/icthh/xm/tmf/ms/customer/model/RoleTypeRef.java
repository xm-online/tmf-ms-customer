package com.icthh.xm.tmf.ms.customer.model;

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
 * RoleType reference.
 */

@Schema(name = "RoleTypeRef", description = "RoleType reference.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.682086418+02:00[Europe/Kyiv]")
public class RoleTypeRef   {

  @JsonProperty("referredType")
  private String referredType;

  @JsonProperty("name")
  private String name;

  @JsonProperty("partnershipHref")
  private String partnershipHref;

  @JsonProperty("partnershipId")
  private String partnershipId;

  @JsonProperty("partnershipName")
  private String partnershipName;

  public RoleTypeRef referredType(String referredType) {
    this.referredType = referredType;
    return this;
  }

  /**
   * Generic attribute indicating the name of the class type of the referred resource entity.
   * @return referredType
  */
  
  @Schema(name = "referredType", description = "Generic attribute indicating the name of the class type of the referred resource entity.", required = false)
  public String getReferredType() {
    return referredType;
  }

  public void setReferredType(String referredType) {
    this.referredType = referredType;
  }

  public RoleTypeRef name(String name) {
    this.name = name;
    return this;
  }

  /**
   * The name of the role type. It uniquely identifies the role type within the partnership type.
   * @return name
  */
  
  @Schema(name = "name", description = "The name of the role type. It uniquely identifies the role type within the partnership type.", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RoleTypeRef partnershipHref(String partnershipHref) {
    this.partnershipHref = partnershipHref;
    return this;
  }

  /**
   * Reference url of the partnership type containing the role type
   * @return partnershipHref
  */
  
  @Schema(name = "partnershipHref", description = "Reference url of the partnership type containing the role type", required = false)
  public String getPartnershipHref() {
    return partnershipHref;
  }

  public void setPartnershipHref(String partnershipHref) {
    this.partnershipHref = partnershipHref;
  }

  public RoleTypeRef partnershipId(String partnershipId) {
    this.partnershipId = partnershipId;
    return this;
  }

  /**
   * The identifier of the partnership type containing the role type
   * @return partnershipId
  */
  
  @Schema(name = "partnershipId", description = "The identifier of the partnership type containing the role type", required = false)
  public String getPartnershipId() {
    return partnershipId;
  }

  public void setPartnershipId(String partnershipId) {
    this.partnershipId = partnershipId;
  }

  public RoleTypeRef partnershipName(String partnershipName) {
    this.partnershipName = partnershipName;
    return this;
  }

  /**
   * The name of the partnership type defining this role type
   * @return partnershipName
  */
  
  @Schema(name = "partnershipName", description = "The name of the partnership type defining this role type", required = false)
  public String getPartnershipName() {
    return partnershipName;
  }

  public void setPartnershipName(String partnershipName) {
    this.partnershipName = partnershipName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoleTypeRef roleTypeRef = (RoleTypeRef) o;
    return Objects.equals(this.referredType, roleTypeRef.referredType) &&
        Objects.equals(this.name, roleTypeRef.name) &&
        Objects.equals(this.partnershipHref, roleTypeRef.partnershipHref) &&
        Objects.equals(this.partnershipId, roleTypeRef.partnershipId) &&
        Objects.equals(this.partnershipName, roleTypeRef.partnershipName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(referredType, name, partnershipHref, partnershipId, partnershipName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoleTypeRef {\n");
    sb.append("    referredType: ").append(toIndentedString(referredType)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    partnershipHref: ").append(toIndentedString(partnershipHref)).append("\n");
    sb.append("    partnershipId: ").append(toIndentedString(partnershipId)).append("\n");
    sb.append("    partnershipName: ").append(toIndentedString(partnershipName)).append("\n");
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

