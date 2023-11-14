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
 * RelatedParty reference. A related party defines party or party role linked to a specific entity.
 */

@Schema(name = "RelatedPartyRef", description = "RelatedParty reference. A related party defines party or party role linked to a specific entity.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.682086418+02:00[Europe/Kyiv]")
public class RelatedPartyRef   {

  @JsonProperty("referredType")
  private String referredType;

  @JsonProperty("href")
  private String href;

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("role")
  private String role;

  public RelatedPartyRef referredType(String referredType) {
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

  public RelatedPartyRef href(String href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the related party, could be a party reference or a party role reference
   * @return href
  */
  
  @Schema(name = "href", description = "Reference of the related party, could be a party reference or a party role reference", required = false)
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public RelatedPartyRef id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of a related party
   * @return id
  */
  
  @Schema(name = "id", description = "Unique identifier of a related party", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public RelatedPartyRef name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the related party
   * @return name
  */
  
  @Schema(name = "name", description = "Name of the related party", required = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RelatedPartyRef role(String role) {
    this.role = role;
    return this;
  }

  /**
   * Role of the related party.
   * @return role
  */
  
  @Schema(name = "role", description = "Role of the related party.", required = false)
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RelatedPartyRef relatedPartyRef = (RelatedPartyRef) o;
    return Objects.equals(this.referredType, relatedPartyRef.referredType) &&
        Objects.equals(this.href, relatedPartyRef.href) &&
        Objects.equals(this.id, relatedPartyRef.id) &&
        Objects.equals(this.name, relatedPartyRef.name) &&
        Objects.equals(this.role, relatedPartyRef.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(referredType, href, id, name, role);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RelatedPartyRef {\n");
    sb.append("    referredType: ").append(toIndentedString(referredType)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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

