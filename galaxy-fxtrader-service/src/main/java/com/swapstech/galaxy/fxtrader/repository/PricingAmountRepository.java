package com.swapstech.galaxy.fxtrader.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.swapstech.galaxy.fxtrader.model.PricingAmount;

@Repository
public interface PricingAmountRepository extends JpaRepository<PricingAmount, UUID>, JpaSpecificationExecutor<PricingAmount> {

	public PricingAmount findByName( String name);

}