package com.icthh.xm.tmf.ms.customer.model.v31;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.icthh.xm.tmf.ms.customer.model.v31.AccountRef;
import com.icthh.xm.tmf.ms.customer.model.v31.AgreementRef;
import com.icthh.xm.tmf.ms.customer.model.v31.Characteristic;
import com.icthh.xm.tmf.ms.customer.model.v31.ContactMedium;
import com.icthh.xm.tmf.ms.customer.model.v31.CreditProfile;
import com.icthh.xm.tmf.ms.customer.model.v31.PartyRef;
import com.icthh.xm.tmf.ms.customer.model.v31.PaymentMethodRef;
import com.icthh.xm.tmf.ms.customer.model.v31.RelatedPartyRef;
import com.icthh.xm.tmf.ms.customer.model.v31.TimePeriod;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Customer
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.814388773+02:00[Europe/Kyiv]")
public class Customer   {

  @JsonProperty("baseType")
  private String baseType;

  @JsonProperty("schemaLocation")
  private String schemaLocation;

  @JsonProperty("type")
  private String type;

  @JsonProperty("href")
  private String href;

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("status")
  private String status;

  @JsonProperty("statusReason")
  private String statusReason;

  @JsonProperty("validFor")
  private TimePeriod validFor;

  @JsonProperty("engagedParty")
  private PartyRef engagedParty;

  @JsonProperty("account")
  @Valid
  private List<AccountRef> account = null;

  @JsonProperty("paymentMethod")
  @Valid
  private List<PaymentMethodRef> paymentMethod = null;

  @JsonProperty("contactMedium")
  @Valid
  private List<ContactMedium> contactMedium = null;

  @JsonProperty("characteristic")
  @Valid
  private List<Characteristic> characteristic = null;

  @JsonProperty("creditProfile")
  @Valid
  private List<CreditProfile> creditProfile = null;

  @JsonProperty("agreement")
  @Valid
  private List<AgreementRef> agreement = null;

  @JsonProperty("relatedParty")
  @Valid
  private List<RelatedPartyRef> relatedParty = null;

  public Customer baseType(String baseType) {
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

  public Customer schemaLocation(String schemaLocation) {
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

  public Customer type(String type) {
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

  public Customer href(String href) {
    this.href = href;
    return this;
  }

  /**
   * Url used to reference the party role.
   * @return href
  */
  
  @Schema(name = "href", description = "Url used to reference the party role.", required = false)
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public Customer id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier for PartyRoles
   * @return id
  */
  
  @Schema(name = "id", description = "Unique identifier for PartyRoles", required = false)
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Customer name(String name) {
    this.name = name;
    return this;
  }

  /**
   * A word, term, or phrase by which the PartyRole is known and distinguished from other PartyRoles.
   * @return name
  */
  @NotNull 
  @Schema(name = "name", description = "A word, term, or phrase by which the PartyRole is known and distinguished from other PartyRoles.", required = true)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Customer status(String status) {
    this.status = status;
    return this;
  }

  /**
   * Used to track the lifecycle status of the party role.
   * @return status
  */
  
  @Schema(name = "status", description = "Used to track the lifecycle status of the party role.", required = false)
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Customer statusReason(String statusReason) {
    this.statusReason = statusReason;
    return this;
  }

  /**
   * A string providing an explanation on the value of the status lifecycle. For instance if the status is Rejected, statusReason will provide the reason for rejection.
   * @return statusReason
  */
  
  @Schema(name = "statusReason", description = "A string providing an explanation on the value of the status lifecycle. For instance if the status is Rejected, statusReason will provide the reason for rejection.", required = false)
  public String getStatusReason() {
    return statusReason;
  }

  public void setStatusReason(String statusReason) {
    this.statusReason = statusReason;
  }

  public Customer validFor(TimePeriod validFor) {
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

  public Customer engagedParty(PartyRef engagedParty) {
    this.engagedParty = engagedParty;
    return this;
  }

  /**
   * Get engagedParty
   * @return engagedParty
  */
  @Valid 
  @Schema(name = "engagedParty", required = false)
  public PartyRef getEngagedParty() {
    return engagedParty;
  }

  public void setEngagedParty(PartyRef engagedParty) {
    this.engagedParty = engagedParty;
  }

  public Customer account(List<AccountRef> account) {
    this.account = account;
    return this;
  }

  public Customer addAccountItem(AccountRef accountItem) {
    if (this.account == null) {
      this.account = new ArrayList<>();
    }
    this.account.add(accountItem);
    return this;
  }

  /**
   * Get account
   * @return account
  */
  @Valid 
  @Schema(name = "account", required = false)
  public List<AccountRef> getAccount() {
    return account;
  }

  public void setAccount(List<AccountRef> account) {
    this.account = account;
  }

  public Customer paymentMethod(List<PaymentMethodRef> paymentMethod) {
    this.paymentMethod = paymentMethod;
    return this;
  }

  public Customer addPaymentMethodItem(PaymentMethodRef paymentMethodItem) {
    if (this.paymentMethod == null) {
      this.paymentMethod = new ArrayList<>();
    }
    this.paymentMethod.add(paymentMethodItem);
    return this;
  }

  /**
   * Get paymentMethod
   * @return paymentMethod
  */
  @Valid 
  @Schema(name = "paymentMethod", required = false)
  public List<PaymentMethodRef> getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(List<PaymentMethodRef> paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public Customer contactMedium(List<ContactMedium> contactMedium) {
    this.contactMedium = contactMedium;
    return this;
  }

  public Customer addContactMediumItem(ContactMedium contactMediumItem) {
    if (this.contactMedium == null) {
      this.contactMedium = new ArrayList<>();
    }
    this.contactMedium.add(contactMediumItem);
    return this;
  }

  /**
   * Get contactMedium
   * @return contactMedium
  */
  @Valid 
  @Schema(name = "contactMedium", required = false)
  public List<ContactMedium> getContactMedium() {
    return contactMedium;
  }

  public void setContactMedium(List<ContactMedium> contactMedium) {
    this.contactMedium = contactMedium;
  }

  public Customer characteristic(List<Characteristic> characteristic) {
    this.characteristic = characteristic;
    return this;
  }

  public Customer addCharacteristicItem(Characteristic characteristicItem) {
    if (this.characteristic == null) {
      this.characteristic = new ArrayList<>();
    }
    this.characteristic.add(characteristicItem);
    return this;
  }

  /**
   * Describes the characteristic of a party role.
   * @return characteristic
  */
  @Valid 
  @Schema(name = "characteristic", description = "Describes the characteristic of a party role.", required = false)
  public List<Characteristic> getCharacteristic() {
    return characteristic;
  }

  public void setCharacteristic(List<Characteristic> characteristic) {
    this.characteristic = characteristic;
  }

  public Customer creditProfile(List<CreditProfile> creditProfile) {
    this.creditProfile = creditProfile;
    return this;
  }

  public Customer addCreditProfileItem(CreditProfile creditProfileItem) {
    if (this.creditProfile == null) {
      this.creditProfile = new ArrayList<>();
    }
    this.creditProfile.add(creditProfileItem);
    return this;
  }

  /**
   * Get creditProfile
   * @return creditProfile
  */
  @Valid 
  @Schema(name = "creditProfile", required = false)
  public List<CreditProfile> getCreditProfile() {
    return creditProfile;
  }

  public void setCreditProfile(List<CreditProfile> creditProfile) {
    this.creditProfile = creditProfile;
  }

  public Customer agreement(List<AgreementRef> agreement) {
    this.agreement = agreement;
    return this;
  }

  public Customer addAgreementItem(AgreementRef agreementItem) {
    if (this.agreement == null) {
      this.agreement = new ArrayList<>();
    }
    this.agreement.add(agreementItem);
    return this;
  }

  /**
   * Get agreement
   * @return agreement
  */
  @Valid 
  @Schema(name = "agreement", required = false)
  public List<AgreementRef> getAgreement() {
    return agreement;
  }

  public void setAgreement(List<AgreementRef> agreement) {
    this.agreement = agreement;
  }

  public Customer relatedParty(List<RelatedPartyRef> relatedParty) {
    this.relatedParty = relatedParty;
    return this;
  }

  public Customer addRelatedPartyItem(RelatedPartyRef relatedPartyItem) {
    if (this.relatedParty == null) {
      this.relatedParty = new ArrayList<>();
    }
    this.relatedParty.add(relatedPartyItem);
    return this;
  }

  /**
   * Get relatedParty
   * @return relatedParty
  */
  @Valid 
  @Schema(name = "relatedParty", required = false)
  public List<RelatedPartyRef> getRelatedParty() {
    return relatedParty;
  }

  public void setRelatedParty(List<RelatedPartyRef> relatedParty) {
    this.relatedParty = relatedParty;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Customer customer = (Customer) o;
    return Objects.equals(this.baseType, customer.baseType) &&
        Objects.equals(this.schemaLocation, customer.schemaLocation) &&
        Objects.equals(this.type, customer.type) &&
        Objects.equals(this.href, customer.href) &&
        Objects.equals(this.id, customer.id) &&
        Objects.equals(this.name, customer.name) &&
        Objects.equals(this.status, customer.status) &&
        Objects.equals(this.statusReason, customer.statusReason) &&
        Objects.equals(this.validFor, customer.validFor) &&
        Objects.equals(this.engagedParty, customer.engagedParty) &&
        Objects.equals(this.account, customer.account) &&
        Objects.equals(this.paymentMethod, customer.paymentMethod) &&
        Objects.equals(this.contactMedium, customer.contactMedium) &&
        Objects.equals(this.characteristic, customer.characteristic) &&
        Objects.equals(this.creditProfile, customer.creditProfile) &&
        Objects.equals(this.agreement, customer.agreement) &&
        Objects.equals(this.relatedParty, customer.relatedParty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(baseType, schemaLocation, type, href, id, name, status, statusReason, validFor, engagedParty, account, paymentMethod, contactMedium, characteristic, creditProfile, agreement, relatedParty);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Customer {\n");
    sb.append("    baseType: ").append(toIndentedString(baseType)).append("\n");
    sb.append("    schemaLocation: ").append(toIndentedString(schemaLocation)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    statusReason: ").append(toIndentedString(statusReason)).append("\n");
    sb.append("    validFor: ").append(toIndentedString(validFor)).append("\n");
    sb.append("    engagedParty: ").append(toIndentedString(engagedParty)).append("\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
    sb.append("    paymentMethod: ").append(toIndentedString(paymentMethod)).append("\n");
    sb.append("    contactMedium: ").append(toIndentedString(contactMedium)).append("\n");
    sb.append("    characteristic: ").append(toIndentedString(characteristic)).append("\n");
    sb.append("    creditProfile: ").append(toIndentedString(creditProfile)).append("\n");
    sb.append("    agreement: ").append(toIndentedString(agreement)).append("\n");
    sb.append("    relatedParty: ").append(toIndentedString(relatedParty)).append("\n");
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

