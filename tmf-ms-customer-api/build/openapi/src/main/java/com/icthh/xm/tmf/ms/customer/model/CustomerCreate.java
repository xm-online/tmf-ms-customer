package com.icthh.xm.tmf.ms.customer.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.icthh.xm.tmf.ms.customer.model.AccountRef;
import com.icthh.xm.tmf.ms.customer.model.AgreementRef;
import com.icthh.xm.tmf.ms.customer.model.Characteristic;
import com.icthh.xm.tmf.ms.customer.model.ContactMedium;
import com.icthh.xm.tmf.ms.customer.model.CreditProfile;
import com.icthh.xm.tmf.ms.customer.model.PartyRef;
import com.icthh.xm.tmf.ms.customer.model.PaymentMethodRef;
import com.icthh.xm.tmf.ms.customer.model.RelatedPartyRef;
import com.icthh.xm.tmf.ms.customer.model.TimePeriod;
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
 *  Skipped properties: id,href
 */

@Schema(name = "Customer_Create", description = " Skipped properties: id,href")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-11-14T10:50:34.682086418+02:00[Europe/Kyiv]")
public class CustomerCreate   {

  @JsonProperty("baseType")
  private String baseType;

  @JsonProperty("schemaLocation")
  private String schemaLocation;

  @JsonProperty("type")
  private String type;

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

  public CustomerCreate baseType(String baseType) {
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

  public CustomerCreate schemaLocation(String schemaLocation) {
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

  public CustomerCreate type(String type) {
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

  public CustomerCreate name(String name) {
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

  public CustomerCreate status(String status) {
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

  public CustomerCreate statusReason(String statusReason) {
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

  public CustomerCreate validFor(TimePeriod validFor) {
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

  public CustomerCreate engagedParty(PartyRef engagedParty) {
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

  public CustomerCreate account(List<AccountRef> account) {
    this.account = account;
    return this;
  }

  public CustomerCreate addAccountItem(AccountRef accountItem) {
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

  public CustomerCreate paymentMethod(List<PaymentMethodRef> paymentMethod) {
    this.paymentMethod = paymentMethod;
    return this;
  }

  public CustomerCreate addPaymentMethodItem(PaymentMethodRef paymentMethodItem) {
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

  public CustomerCreate contactMedium(List<ContactMedium> contactMedium) {
    this.contactMedium = contactMedium;
    return this;
  }

  public CustomerCreate addContactMediumItem(ContactMedium contactMediumItem) {
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

  public CustomerCreate characteristic(List<Characteristic> characteristic) {
    this.characteristic = characteristic;
    return this;
  }

  public CustomerCreate addCharacteristicItem(Characteristic characteristicItem) {
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

  public CustomerCreate creditProfile(List<CreditProfile> creditProfile) {
    this.creditProfile = creditProfile;
    return this;
  }

  public CustomerCreate addCreditProfileItem(CreditProfile creditProfileItem) {
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

  public CustomerCreate agreement(List<AgreementRef> agreement) {
    this.agreement = agreement;
    return this;
  }

  public CustomerCreate addAgreementItem(AgreementRef agreementItem) {
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

  public CustomerCreate relatedParty(List<RelatedPartyRef> relatedParty) {
    this.relatedParty = relatedParty;
    return this;
  }

  public CustomerCreate addRelatedPartyItem(RelatedPartyRef relatedPartyItem) {
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
    CustomerCreate customerCreate = (CustomerCreate) o;
    return Objects.equals(this.baseType, customerCreate.baseType) &&
        Objects.equals(this.schemaLocation, customerCreate.schemaLocation) &&
        Objects.equals(this.type, customerCreate.type) &&
        Objects.equals(this.name, customerCreate.name) &&
        Objects.equals(this.status, customerCreate.status) &&
        Objects.equals(this.statusReason, customerCreate.statusReason) &&
        Objects.equals(this.validFor, customerCreate.validFor) &&
        Objects.equals(this.engagedParty, customerCreate.engagedParty) &&
        Objects.equals(this.account, customerCreate.account) &&
        Objects.equals(this.paymentMethod, customerCreate.paymentMethod) &&
        Objects.equals(this.contactMedium, customerCreate.contactMedium) &&
        Objects.equals(this.characteristic, customerCreate.characteristic) &&
        Objects.equals(this.creditProfile, customerCreate.creditProfile) &&
        Objects.equals(this.agreement, customerCreate.agreement) &&
        Objects.equals(this.relatedParty, customerCreate.relatedParty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(baseType, schemaLocation, type, name, status, statusReason, validFor, engagedParty, account, paymentMethod, contactMedium, characteristic, creditProfile, agreement, relatedParty);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerCreate {\n");
    sb.append("    baseType: ").append(toIndentedString(baseType)).append("\n");
    sb.append("    schemaLocation: ").append(toIndentedString(schemaLocation)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

