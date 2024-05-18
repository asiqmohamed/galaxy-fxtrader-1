package com.swapstech.galxy.fxtrader.client.pricing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * PricingRateSource
 */
@Validated
public class PricingRateSource   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("groupName")
  private String groupName = null;

  @JsonProperty("isDefault")
  private Boolean isDefault = null;

  @JsonProperty("venue")
  private String venue = null;

  @JsonProperty("ccyGroup")
  private PricingCcyGroup ccyGroup = null;

  public PricingRateSource id(String id) {
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

  public PricingRateSource groupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  /**
   * Get groupName
   * @return groupName
   **/
  @Schema(description = "")
  
    public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public PricingRateSource isDefault(Boolean isDefault) {
    this.isDefault = isDefault;
    return this;
  }

  /**
   * Get isDefault
   * @return isDefault
   **/
  @Schema(description = "")
  
    public Boolean isIsDefault() {
    return isDefault;
  }

  public void setIsDefault(Boolean isDefault) {
    this.isDefault = isDefault;
  }

  public PricingRateSource venue(String venue) {
    this.venue = venue;
    return this;
  }

  /**
   * Get venue
   * @return venue
   **/
  @Schema(description = "")
  
    public String getVenue() {
    return venue;
  }

  public void setVenue(String venue) {
    this.venue = venue;
  }

  public PricingRateSource ccyGroup(PricingCcyGroup ccyGroup) {
    this.ccyGroup = ccyGroup;
    return this;
  }

  /**
   * Get ccyGroup
   * @return ccyGroup
   **/
  @Schema(description = "")
    public PricingCcyGroup getCcyGroup() {
    return ccyGroup;
  }

  public void setCcyGroup(PricingCcyGroup ccyGroup) {
    this.ccyGroup = ccyGroup;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PricingRateSource pricingRateSource = (PricingRateSource) o;
    return Objects.equals(this.id, pricingRateSource.id) &&
        Objects.equals(this.groupName, pricingRateSource.groupName) &&
        Objects.equals(this.isDefault, pricingRateSource.isDefault) &&
        Objects.equals(this.venue, pricingRateSource.venue) &&
        Objects.equals(this.ccyGroup, pricingRateSource.ccyGroup);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, groupName, isDefault, venue, ccyGroup);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PricingRateSource {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
    sb.append("    isDefault: ").append(toIndentedString(isDefault)).append("\n");
    sb.append("    venue: ").append(toIndentedString(venue)).append("\n");
    sb.append("    ccyGroup: ").append(toIndentedString(ccyGroup)).append("\n");
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
