package com.swapstech.galxy.fxtrader.client.pricing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * PricingCcySet
 */
@Validated
public class PricingCcySet   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("ccyPair")
  private String ccyPair = null;

  public PricingCcySet id(String id) {
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

  public PricingCcySet ccyPair(String ccyPair) {
    this.ccyPair = ccyPair;
    return this;
  }

  /**
   * Get ccyPair
   * @return ccyPair
   **/
  @Schema(description = "")
  
    public String getCcyPair() {
    return ccyPair;
  }

  public void setCcyPair(String ccyPair) {
    this.ccyPair = ccyPair;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PricingCcySet pricingCcySet = (PricingCcySet) o;
    return Objects.equals(this.id, pricingCcySet.id) &&
        Objects.equals(this.ccyPair, pricingCcySet.ccyPair);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ccyPair);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PricingCcySet {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    ccyPair: ").append(toIndentedString(ccyPair)).append("\n");
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
