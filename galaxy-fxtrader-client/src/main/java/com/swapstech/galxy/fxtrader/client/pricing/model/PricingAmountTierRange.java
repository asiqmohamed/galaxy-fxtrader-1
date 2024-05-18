package com.swapstech.galxy.fxtrader.client.pricing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * PricingAmountTierRange
 */
@Validated
public class PricingAmountTierRange   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("amountTierId")
  private PricingAmount amountTierId = null;

  @JsonProperty("amountFrom")
  private BigDecimal amountFrom = null;

  @JsonProperty("amountTo")
  private BigDecimal amountTo = null;

  @JsonProperty("bankBuys")
  private BigDecimal bankBuys = null;

  @JsonProperty("bankSells")
  private BigDecimal bankSells = null;

  @JsonProperty("spreadUnit")
  private String spreadUnit = null;

  public PricingAmountTierRange id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(description = "")
  
    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PricingAmountTierRange amountTierId(PricingAmount amountTierId) {
    this.amountTierId = amountTierId;
    return this;
  }

  /**
   * Get amountTierId
   * @return amountTierId
   **/
  @Schema(description = "")

    public PricingAmount getAmountTierId() {
    return amountTierId;
  }

  public void setAmountTierId(PricingAmount amountTierId) {
    this.amountTierId = amountTierId;
  }

  public PricingAmountTierRange amountFrom(BigDecimal amountFrom) {
    this.amountFrom = amountFrom;
    return this;
  }

  /**
   * Get amountFrom
   * @return amountFrom
   **/
  @Schema(description = "")

    public BigDecimal getAmountFrom() {
    return amountFrom;
  }

  public void setAmountFrom(BigDecimal amountFrom) {
    this.amountFrom = amountFrom;
  }

  public PricingAmountTierRange amountTo(BigDecimal amountTo) {
    this.amountTo = amountTo;
    return this;
  }

  /**
   * Get amountTo
   * @return amountTo
   **/
  @Schema(description = "")
    public BigDecimal getAmountTo() {
    return amountTo;
  }

  public void setAmountTo(BigDecimal amountTo) {
    this.amountTo = amountTo;
  }

  public PricingAmountTierRange bankBuys(BigDecimal bankBuys) {
    this.bankBuys = bankBuys;
    return this;
  }

  /**
   * Get bankBuys
   * @return bankBuys
   **/
  @Schema(description = "")
    public BigDecimal getBankBuys() {
    return bankBuys;
  }

  public void setBankBuys(BigDecimal bankBuys) {
    this.bankBuys = bankBuys;
  }

  public PricingAmountTierRange bankSells(BigDecimal bankSells) {
    this.bankSells = bankSells;
    return this;
  }

  /**
   * Get bankSells
   * @return bankSells
   **/
  @Schema(description = "")
    public BigDecimal getBankSells() {
    return bankSells;
  }

  public void setBankSells(BigDecimal bankSells) {
    this.bankSells = bankSells;
  }

  public PricingAmountTierRange spreadUnit(String spreadUnit) {
    this.spreadUnit = spreadUnit;
    return this;
  }

  /**
   * Get spreadUnit
   * @return spreadUnit
   **/
  @Schema(description = "")
  
    public String getSpreadUnit() {
    return spreadUnit;
  }

  public void setSpreadUnit(String spreadUnit) {
    this.spreadUnit = spreadUnit;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PricingAmountTierRange pricingAmountTierRange = (PricingAmountTierRange) o;
    return Objects.equals(this.id, pricingAmountTierRange.id) &&
        Objects.equals(this.amountTierId, pricingAmountTierRange.amountTierId) &&
        Objects.equals(this.amountFrom, pricingAmountTierRange.amountFrom) &&
        Objects.equals(this.amountTo, pricingAmountTierRange.amountTo) &&
        Objects.equals(this.bankBuys, pricingAmountTierRange.bankBuys) &&
        Objects.equals(this.bankSells, pricingAmountTierRange.bankSells) &&
        Objects.equals(this.spreadUnit, pricingAmountTierRange.spreadUnit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, amountTierId, amountFrom, amountTo, bankBuys, bankSells, spreadUnit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PricingAmountTierRange {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    amountTierId: ").append(toIndentedString(amountTierId)).append("\n");
    sb.append("    amountFrom: ").append(toIndentedString(amountFrom)).append("\n");
    sb.append("    amountTo: ").append(toIndentedString(amountTo)).append("\n");
    sb.append("    bankBuys: ").append(toIndentedString(bankBuys)).append("\n");
    sb.append("    bankSells: ").append(toIndentedString(bankSells)).append("\n");
    sb.append("    spreadUnit: ").append(toIndentedString(spreadUnit)).append("\n");
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
