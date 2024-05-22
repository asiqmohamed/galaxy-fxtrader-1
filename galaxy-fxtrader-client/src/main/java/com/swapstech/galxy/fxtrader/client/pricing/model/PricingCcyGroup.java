package com.swapstech.galxy.fxtrader.client.pricing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * PricingCcyGroup
 */
@Validated
public class PricingCcyGroup   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("pricingCcySet")
  private List<PricingCcySet> pricingCcySet = null;

  @SerializedName("tenors")
  private List<PricingTenor> tenors = null;

  public PricingCcyGroup id(String id) {
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

  public PricingCcyGroup name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @Schema(description = "")

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PricingCcyGroup pricingCcySet(List<PricingCcySet> pricingCcySet) {
    this.pricingCcySet = pricingCcySet;
    return this;
  }

  public PricingCcyGroup addPricingCcySetItem(PricingCcySet pricingCcySetItem) {
    if (this.pricingCcySet == null) {
      this.pricingCcySet = new ArrayList<PricingCcySet>();
    }
    this.pricingCcySet.add(pricingCcySetItem);
    return this;
  }

  /**
   * Get pricingCcySet
   * @return pricingCcySet
   **/
  @Schema(description = "")
    public List<PricingCcySet> getPricingCcySet() {
    return pricingCcySet;
  }

  public void setPricingCcySet(List<PricingCcySet> pricingCcySet) {
    this.pricingCcySet = pricingCcySet;
  }

  public PricingCcyGroup tenors(List<PricingTenor> tenors) {
    this.tenors = tenors;
    return this;
  }

  /**
   * Get tenors
   * @return tenors
   **/
  @Schema(description = "")
  public List<PricingTenor> getTenors() {
    return tenors;
  }

  public void setTenors(List<PricingTenor> tenors) {
    this.tenors = tenors;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PricingCcyGroup pricingCcyGroup = (PricingCcyGroup) o;
    return Objects.equals(this.id, pricingCcyGroup.id) &&
            Objects.equals(this.name, pricingCcyGroup.name) &&
            Objects.equals(this.pricingCcySet, pricingCcyGroup.pricingCcySet) &&
            Objects.equals(this.tenors, pricingCcyGroup.tenors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, pricingCcySet, tenors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PricingCcyGroup {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    pricingCcySet: ").append(toIndentedString(pricingCcySet)).append("\n");
    sb.append("    tenors: ").append(toIndentedString(tenors)).append("\n");
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
