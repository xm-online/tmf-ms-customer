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
 * Agreement reference. An agreement represents a contract or arrangement, either written or verbal and sometimes enforceable by law, such as a service level agreement or a customer price agreement. An agreement involves a number of other business entities, such as products, services, and resources and/or their specifications.
 */

@Schema(name = "AgreementRef", description = "Agreement reference. An agreement represents a contract or arrangement, either written or verbal and sometimes enforceable by law, such as a service level agreement or a customer price agreement. An agreement involves a number of other business entities, such as products, services, and resources and/or their specifications.")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.814388773+02:00[Europe/Kyiv]")
public class AgreementRef   {

  @JsonProperty("referredType")
  private String referredType;

  @JsonProperty("href")
  private String href;

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  public AgreementRef referredType(String referredType) {
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

  public AgreementRef href(String href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the agreement
   * @return href
  */
  
  @Schema(name = "href", description = "Reference of the agreement", required = false)
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public AgreementRef id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Identifier of the agreement
   * @return id
  */
  
  @Schema(name = "id", description = "Identifier of the agreement", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AgreementRef name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the agreement
   * @return name
  */
  
  @Schema(name = "name", description = "Name of the agreement", required = false)
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
    AgreementRef agreementRef = (AgreementRef) o;
    return Objects.equals(this.referredType, agreementRef.referredType) &&
        Objects.equals(this.href, agreementRef.href) &&
        Objects.equals(this.id, agreementRef.id) &&
        Objects.equals(this.name, agreementRef.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(referredType, href, id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AgreementRef {\n");
    sb.append("    referredType: ").append(toIndentedString(referredType)).append("\n");
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

