package com.swapstech.galaxy.fxtrader.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.math.BigDecimal;
import java.util.UUID;

@Table
@Entity(name = "pricing_amount_range")
public class PricingAmountRange {

	public PricingAmountRange() {
	}

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@Column(name = "id")
	private UUID id;

	@Column(name = "amount_from")
	private BigDecimal amountFrom;

	@Column(name = "amount_to")
	private BigDecimal amountTo;

	@Column(name = "bank_buys")
	private BigDecimal bankBuys;

	@Column(name = "bank_sells")
	private BigDecimal bankSells;

	@Column(name = "spread_unit")
	private int spreadUnit;

	@ManyToOne
	@JoinColumn(name = "pricing_amount_id")
	private PricingAmount pricingAmount;


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public BigDecimal getAmountFrom() {
		return amountFrom;
	}

	public void setAmountFrom(BigDecimal amountFrom) {
		this.amountFrom = amountFrom;
	}

	public BigDecimal getAmountTo() {
		return amountTo;
	}

	public void setAmountTo(BigDecimal amountTo) {
		this.amountTo = amountTo;
	}

	public BigDecimal getBankBuys() {
		return bankBuys;
	}

	public void setBankBuys(BigDecimal bankBuys) {
		this.bankBuys = bankBuys;
	}

	public BigDecimal getBankSells() {
		return bankSells;
	}

	public void setBankSells(BigDecimal bankSells) {
		this.bankSells = bankSells;
	}

	public SpreadUnit getSpreadUnit() {
		return SpreadUnit.fromValue(spreadUnit);
	}

	public void setSpreadUnit(SpreadUnit spreadUnit) {
		this.spreadUnit = spreadUnit.getValue();
	}

	public PricingAmount getPricingAmount() { return pricingAmount; }

	public void setPricingAmount(PricingAmount pricingAmount) { this.pricingAmount = pricingAmount; }
}
