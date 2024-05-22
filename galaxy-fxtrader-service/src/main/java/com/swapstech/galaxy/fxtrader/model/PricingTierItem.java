package com.swapstech.galaxy.fxtrader.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.swapstech.galaxy.common.util.JsonLocalDateTimeDeserializer;
import com.swapstech.galaxy.common.util.JsonLocalDateTimeSerializer;
import com.swapstech.galaxy.common.util.LocalDateTimeAttributeConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.javers.core.metamodel.annotation.DiffIgnore;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Table
@Entity(name = "pricing_item")
public class PricingTierItem {

	public PricingTierItem() {
	}

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	@Column(name = "id")
	private UUID id;

	@Column(name = "tier_type")
	private int tierType;

	@Column(name = "default_tier_id")
	private String defaultTierId;

	@Column(name = "is_enabled")
	private boolean isEnabled = true;

	@Column(name = "is_deleted")
	private boolean isDeleted = false;

	@Column(name = "channels")
	private List<String> channels;

	@Column(name = "is_all_day")
	private boolean isAllDay = true;

	@Column(name = "from_time")
	private Integer fromTime;

	@Column(name = "to_time")
	private Integer toTime;

	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "pricing_tier_id")
	private PricingTier pricingTier;

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

	public TierType getTierType() {
		return TierType.fromValue(tierType);
	}

	public void setTierType(TierType tierType) {
		this.tierType = tierType.getValue();
	}

	public String getDefaultTierId() {
		return defaultTierId;
	}

	public void setDefaultTierId(String defaultTierId) {
		this.defaultTierId = defaultTierId;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}

	public List<String> getChannels() {
		return channels;
	}

	public void setChannels(List<String> channels) {
		this.channels = channels;
	}

	public boolean isAllDay() { return isAllDay; }

	public void setAllDay(boolean allDay) { isAllDay = allDay; }

	public Integer getFromTime() { return fromTime; }

	public void setFromTime(Integer fromTime) { this.fromTime = fromTime; }

	public Integer getToTime() { return toTime; }

	public void setToTime(Integer toTime) { this.toTime = toTime; }

	public PricingTier getPricingTier() {
		return pricingTier;
	}

	public void setPricingTier(PricingTier pricingTier) {
		this.pricingTier = pricingTier;
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
