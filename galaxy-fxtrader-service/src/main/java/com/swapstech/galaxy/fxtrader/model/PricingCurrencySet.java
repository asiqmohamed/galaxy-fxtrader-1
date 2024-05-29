package com.swapstech.galaxy.fxtrader.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	@JoinColumn(name = "pricing_item_id")
	private PricingTierItem pricingTierItem;

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
}
