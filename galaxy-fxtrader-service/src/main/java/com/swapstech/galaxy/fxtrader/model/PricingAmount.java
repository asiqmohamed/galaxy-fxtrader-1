package com.swapstech.galaxy.fxtrader.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Table
@Entity(name = "pricing_amount")
public class PricingAmount {

	public PricingAmount() {
	}

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@Column(name = "id")
	private UUID id;

	@Column(name = "name")
	private String name;

	@Column(name = "ccy_type")
	private int ccyType;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "pricing_tenor_range_id")
	private PricingTenorRange pricingTenorRange;

	@OneToMany(mappedBy="pricingAmount", fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<PricingAmountRange> pricingAmountRanges;


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PricingCurrencyType getCcyType() {
		return PricingCurrencyType.fromValue(ccyType);
	}

	public void setCcyType(PricingCurrencyType ccyType) {
		this.ccyType = ccyType.getValue();
	}

	public PricingTenorRange getPricingTenorRange() {
		return pricingTenorRange;
	}

	public void setPricingTenorRange(PricingTenorRange pricingTenorRange) {
		this.pricingTenorRange = pricingTenorRange;
	}

	public List<PricingAmountRange> getPricingAmountRanges() {
		return pricingAmountRanges;
	}

	public void setPricingAmountRanges(List<PricingAmountRange> pricingAmountRanges) {
		this.pricingAmountRanges = pricingAmountRanges;
	}

}
