package com.swapstech.galaxy.fxtrader.model;

import com.swapstech.galxy.fxtrader.client.pricing.model.PricingCcyGroup;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.util.UUID;

@Table
@Entity(name = "pricing_currency_set")
public class PricingCurrencySet {

	public PricingCurrencySet() {
	}

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@Column(name = "id")
	private UUID id;

	@Column(name = "ccy_pair")
	private String ccyPair;

	@ManyToOne
	@JoinColumn(name = "ccy_group_id")
	private PricingCurrencyGroup pricingCcyGrp;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCcyPair() {
		return ccyPair;
	}

	public void setCcyPair(String ccyPair) {
		this.ccyPair = ccyPair;
	}

	public PricingCurrencyGroup getPricingCcyGrp() {
		return pricingCcyGrp;
	}

	public void setPricingCcyGrp(PricingCurrencyGroup pricingCcyGrp) {
		this.pricingCcyGrp = pricingCcyGrp;
	}
}
