package com.swapstech.galaxy.fxtrader.repository;

import java.util.List;
import java.util.UUID;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.swapstech.galaxy.fxtrader.model.PricingTier;

@Repository
@JaversSpringDataAuditable
public interface PricingTierRepository extends JpaRepository<PricingTier, UUID>, JpaSpecificationExecutor<PricingTier> {

	public PricingTier findByName( String name);

	<T> List<T> findAllByTierType(int tierType, Class<T> type);


}