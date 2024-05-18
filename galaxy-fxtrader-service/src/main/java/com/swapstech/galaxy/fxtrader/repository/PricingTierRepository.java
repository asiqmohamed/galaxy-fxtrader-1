package com.swapstech.galaxy.fxtrader.repository;

import com.swapstech.galaxy.fxtrader.model.PricingTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface PricingTierRepository extends JpaRepository<PricingTier, UUID>, JpaSpecificationExecutor<PricingTier> {

	public PricingTier findByName( String name);

}