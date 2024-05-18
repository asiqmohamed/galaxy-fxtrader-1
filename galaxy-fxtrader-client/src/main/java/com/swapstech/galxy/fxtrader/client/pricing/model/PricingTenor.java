package com.swapstech.galxy.fxtrader.client.pricing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * PricingTenor
 */
@Validated
public class PricingTenor   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("rangeFrom")
  private String rangeFrom = null;

  @JsonProperty("rangeTo")
  private String rangeTo = null;

  @JsonProperty("pricingAmount")
  private PricingAmount pricingAmount = null;

  public PricingTenor id(String id) {
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

  public PricingTenor rangeFrom(String rangeFrom) {
    this.rangeFrom = rangeFrom;
    return this;
  }

  /**
   * Get rangeFrom
   * @return rangeFrom
   **/
  @Schema(description = "")
  
    public String getRangeFrom() {
    return rangeFrom;
  }

  public void setRangeFrom(String rangeFrom) {
    this.rangeFrom = rangeFrom;
  }

  public PricingTenor rangeTo(String rangeTo) {
    this.rangeTo = rangeTo;
    return this;
  }

  /**
   * Get rangeTo
   * @return rangeTo
   **/
  @Schema(description = "")
  
    public String getRangeTo() {
    return rangeTo;
  }

  public void setRangeTo(String rangeTo) {
    this.rangeTo = rangeTo;
  }

  public PricingTenor pricingAmount(PricingAmount pricingAmount) {
    this.pricingAmount = pricingAmount;
    return this;
  }

  /**
   * Get amountTier
   * @return amountTier
   **/
  @Schema(description = "")
    public PricingAmount getPricingAmount() {
    return pricingAmount;
  }

  public void setPricingAmount(PricingAmount pricingAmount) {
    this.pricingAmount = pricingAmount;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PricingTenor pricingTenor = (PricingTenor) o;
    return Objects.equals(this.id, pricingTenor.id) &&
        Objects.equals(this.rangeFrom, pricingTenor.rangeFrom) &&
        Objects.equals(this.rangeTo, pricingTenor.rangeTo) &&
        Objects.equals(this.pricingAmount, pricingTenor.pricingAmount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, rangeFrom, rangeTo, pricingAmount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PricingTenor {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    rangeFrom: ").append(toIndentedString(rangeFrom)).append("\n");
    sb.append("    rangeTo: ").append(toIndentedString(rangeTo)).append("\n");
    sb.append("    pricingAmount: ").append(toIndentedString(pricingAmount)).append("\n");
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
