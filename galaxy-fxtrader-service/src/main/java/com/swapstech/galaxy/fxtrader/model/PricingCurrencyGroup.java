package com.swapstech.galaxy.fxtrader.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.swapstech.galaxy.common.util.JsonLocalDateTimeDeserializer;
import com.swapstech.galaxy.common.util.JsonLocalDateTimeSerializer;
import com.swapstech.galaxy.common.util.LocalDateTimeAttributeConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.javers.core.metamodel.annotation.DiffIgnore;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table
@Entity(name = "pricing_currency_group")
public class PricingCurrencyGroup {

	public PricingCurrencyGroup() {
	}

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@Column(name = "id")
	private UUID id;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "pricing_tier_id")
	private PricingTier pricingTier;

	@OneToMany(mappedBy="pricingCurrencyGroup", fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<PricingTenorRange> pricingTenorRanges;

	@OneToMany(mappedBy="pricingCcyGrp", fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<PricingCurrencySet> pricingCurrencies;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PricingTier getPricingTier() {
		return pricingTier;
	}

	public void setPricingTier(PricingTier pricingTier) {
		this.pricingTier = pricingTier;
	}

	public List<PricingTenorRange> getPricingTenorRanges() { return pricingTenorRanges; }

	public void setPricingTenorRanges(List<PricingTenorRange> pricingTenorRanges) { this.pricingTenorRanges = pricingTenorRanges; }
	public List<PricingCurrencySet> getPricingCurrencies() {
		return pricingCurrencies;
	}

	public void setPricingCurrencies(List<PricingCurrencySet> pricingCurrencies) { this.pricingCurrencies = pricingCurrencies; }

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
