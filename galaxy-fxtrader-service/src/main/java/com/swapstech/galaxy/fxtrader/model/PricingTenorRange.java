package com.swapstech.galaxy.fxtrader.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.swapstech.galaxy.common.util.JsonLocalDateTimeDeserializer;
import com.swapstech.galaxy.common.util.JsonLocalDateTimeSerializer;
import com.swapstech.galaxy.common.util.LocalDateTimeAttributeConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.javers.core.metamodel.annotation.DiffIgnore;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Entity(name = "pricing_tenor_range")
public class PricingTenorRange {

	public PricingTenorRange() {
	}

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@Column(name = "id")
	private UUID id;

	@Column(name = "range_from")
	private String rangeFrom;

	@Column(name = "range_to")
	private String rangeTo;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "ccy_group_id")
	private PricingCurrencyGroup pricingCurrencyGroup;

	@DiffIgnore
	@OneToOne(mappedBy = "pricingTenorRange", orphanRemoval = true, cascade = CascadeType.ALL)
	private PricingAmount pricingAmount;

	@Column(name = "created_by")
//	@FieldValidation(length = 50,truncate = true)
	private String createdBy;

	@DiffIgnore
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	@Column(name = "creation_time")
	private LocalDateTime creationTime;

	@Column(name = "last_updated_by")
//	@FieldValidation(length = 50,truncate = true)
	private String lastUpdatedBy;

	@DiffIgnore
	@Column(name = "last_updated_time")
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	private LocalDateTime lastUpdatedTime;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getRangeFrom() {
		return rangeFrom;
	}

	public void setRangeFrom(String rangeFrom) {
		this.rangeFrom = rangeFrom;
	}

	public String getRangeTo() {
		return rangeTo;
	}

	public void setRangeTo(String rangeTo) {
		this.rangeTo = rangeTo;
	}

	public PricingCurrencyGroup getPricingCurrencyGroup() { return pricingCurrencyGroup; }

	public void setPricingCurrencyGroup(PricingCurrencyGroup pricingCurrencyGroup) { this.pricingCurrencyGroup = pricingCurrencyGroup; }

	public PricingAmount getPricingAmount() {
		return pricingAmount;
	}

	public void setPricingAmount(PricingAmount pricingAmount) {
		this.pricingAmount = pricingAmount;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public LocalDateTime getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

}
