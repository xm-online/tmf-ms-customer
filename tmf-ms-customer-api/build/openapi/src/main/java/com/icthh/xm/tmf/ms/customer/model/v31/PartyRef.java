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
 * Party reference. A party represents an organization or an individual.
 */

@Schema(name = "PartyRef", description = "Party reference. A party represents an organization or an individual.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.814388773+02:00[Europe/Kyiv]")
public class PartyRef   {

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

  public PartyRef referredType(String referredType) {
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

  public PartyRef description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Text describing the referred party
   * @return description
  */
  
  @Schema(name = "description", description = "Text describing the referred party", required = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PartyRef href(String href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the referred party (such as a partner or any other party role).
   * @return href
  */
  @NotNull 
  @Schema(name = "href", description = "Reference of the referred party (such as a partner or any other party role).", required = true)
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public PartyRef id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the referred party
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "Unique identifier of the referred party", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PartyRef name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the referred party (such as a partner or any other party role)
   * @return name
  */
  
  @Schema(name = "name", description = "Name of the referred party (such as a partner or any other party role)", required = false)
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
    PartyRef partyRef = (PartyRef) o;
    return Objects.equals(this.referredType, partyRef.referredType) &&
        Objects.equals(this.description, partyRef.description) &&
        Objects.equals(this.href, partyRef.href) &&
        Objects.equals(this.id, partyRef.id) &&
        Objects.equals(this.name, partyRef.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(referredType, description, href, id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PartyRef {\n");
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

