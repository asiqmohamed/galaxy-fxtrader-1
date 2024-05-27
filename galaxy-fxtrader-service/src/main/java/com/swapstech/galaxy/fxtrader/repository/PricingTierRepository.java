package com.swapstech.galaxy.fxtrader.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swapstech.galaxy.fxtrader.model.PricingTier;

@Repository
public interface PricingTierRepository extends JpaRepository<PricingTier, UUID>, JpaSpecificationExecutor<PricingTier> {

	public PricingTier findByName( String name);
	
	@Query(nativeQuery = true, value = "SELECT pt.id, pt.name, pt.is_default FROM pricing_tier pt INNER JOIN pricing_item pti ON pt.id = pti.pricing_tier_id WHERE pti.tier_type = :tierType")
	public List<Object[]> getAllTiers(@Param("tierType") int tierType);

}