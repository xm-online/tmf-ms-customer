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
 * PaymentMethod reference. A payment method defines a specific mean of payment (e.g direct debit).
 */

@Schema(name = "PaymentMethodRef", description = "PaymentMethod reference. A payment method defines a specific mean of payment (e.g direct debit).")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.682086418+02:00[Europe/Kyiv]")
public class PaymentMethodRef   {

  @JsonProperty("referredType")
  private String referredType;

  @JsonProperty("href")
  private String href;

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  public PaymentMethodRef referredType(String referredType) {
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

  public PaymentMethodRef href(String href) {
    this.href = href;
    return this;
  }

  /**
   * Reference of the payment mean
   * @return href
  */
  @NotNull 
  @Schema(name = "href", description = "Reference of the payment mean", required = true)
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public PaymentMethodRef id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier of the payment mean
   * @return id
  */
  @NotNull 
  @Schema(name = "id", description = "Unique identifier of the payment mean", required = true)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PaymentMethodRef name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the payment mean
   * @return name
  */
  
  @Schema(name = "name", description = "Name of the payment mean", required = false)
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
    PaymentMethodRef paymentMethodRef = (PaymentMethodRef) o;
    return Objects.equals(this.referredType, paymentMethodRef.referredType) &&
        Objects.equals(this.href, paymentMethodRef.href) &&
        Objects.equals(this.id, paymentMethodRef.id) &&
        Objects.equals(this.name, paymentMethodRef.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(referredType, href, id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentMethodRef {\n");
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

