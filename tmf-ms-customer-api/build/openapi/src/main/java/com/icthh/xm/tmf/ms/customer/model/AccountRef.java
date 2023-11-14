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
 * Account reference. A account may be a party account or a financial account.
 */

@Schema(name = "AccountRef", description = "Account reference. A account may be a party account or a financial account.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.682086418+02:00[Europe/Kyiv]")
public class AccountRef   {

  @JsonProperty("referredType")
  private String referredType;

  @JsonProperty("description")
  private String description;

  @JsonProperty("href")
  private String href;

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  public AccountRef referredType(String referredType) {
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

  public AccountRef description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Detailed description of the account
   * @return description
  */
  
  @Schema(name = "description", description = "Detailed description of the account", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public AccountRef href(String href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the account
   * @return href
  */
  @NotNull 
  @Schema(name = "href", description = "Reference of the account", required = true)
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public AccountRef id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the account
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "Unique identifier of the account", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AccountRef name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the account
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "Name of the account", required = true)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountRef accountRef = (AccountRef) o;
    return Objects.equals(this.referredType, accountRef.referredType) &&
        Objects.equals(this.description, accountRef.description) &&
        Objects.equals(this.href, accountRef.href) &&
        Objects.equals(this.id, accountRef.id) &&
        Objects.equals(this.name, accountRef.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(referredType, description, href, id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountRef {\n");
    sb.append("    referredType: ").append(toIndentedString(referredType)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

