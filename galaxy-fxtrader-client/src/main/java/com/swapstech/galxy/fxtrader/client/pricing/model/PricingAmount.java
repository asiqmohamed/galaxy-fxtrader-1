package com.swapstech.galxy.fxtrader.client.pricing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PricingAmountTier
 */
@Validated
public class PricingAmount {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("tierName")
  private String tierName = null;

  @JsonProperty("inTermsOf")
  private Integer inTermsOf = null;
  
  @JsonProperty("isEnabled")
  private Integer isEnabled = null;

  @JsonProperty("amountRanges")
  private List<PricingAmountTierRange> amountRanges = null;

  public PricingAmount id(String id) {
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

  public PricingAmount tierName(String tierName) {
    this.tierName = tierName;
    return this;
  }

  /**
   * Get tierName
   * @return tierName
   **/
  @Schema(description = "")
  
    public String getTierName() {
    return tierName;
  }

  public void setTierName(String tierName) {
    this.tierName = tierName;
  }

  public PricingAmount inTermsOf(Integer inTermsOf) {
    this.inTermsOf = inTermsOf;
    return this;
  }

  /**
   * Get inTermsOf
   * @return inTermsOf
   **/
  @Schema(description = "")
  
    public Integer getInTermsOf() {
    return inTermsOf;
  }

  public void setInTermsOf(Integer inTermsOf) {
    this.inTermsOf = inTermsOf;
  }

  public PricingAmount amountRanges(List<PricingAmountTierRange> amountRanges) {
    this.amountRanges = amountRanges;
    return this;
  }

  public PricingAmount addAmountRangesItem(PricingAmountTierRange amountRangesItem) {
    if (this.amountRanges == null) {
      this.amountRanges = new ArrayList<PricingAmountTierRange>();
    }
    this.amountRanges.add(amountRangesItem);
    return this;
  }

  /**
   * Get amountRanges
   * @return amountRanges
   **/
  @Schema(description = "")
    public List<PricingAmountTierRange> getAmountRanges() {
    return amountRanges;
  }

  public void setAmountRanges(List<PricingAmountTierRange> amountRanges) {
    this.amountRanges = amountRanges;
  }
  
  /**
   * Get isEnabled
   * @return isEnabled
   **/
  public Integer getIsEnabled() {
	return isEnabled;
  }

  public void setIsEnabled(Integer isEnabled) {
	this.isEnabled = isEnabled;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PricingAmount pricingAmountTier = (PricingAmount) o;
    return Objects.equals(this.id, pricingAmountTier.id) &&
        Objects.equals(this.tierName, pricingAmountTier.tierName) &&
        Objects.equals(this.inTermsOf, pricingAmountTier.inTermsOf) &&
        Objects.equals(this.amountRanges, pricingAmountTier.amountRanges);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tierName, inTermsOf, amountRanges);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PricingAmountTier {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tierName: ").append(toIndentedString(tierName)).append("\n");
    sb.append("    inTermsOf: ").append(toIndentedString(inTermsOf)).append("\n");
    sb.append("    amountRanges: ").append(toIndentedString(amountRanges)).append("\n");
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
