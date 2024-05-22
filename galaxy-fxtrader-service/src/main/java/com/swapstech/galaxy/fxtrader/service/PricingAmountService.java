package com.swapstech.galaxy.fxtrader.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swapstech.galaxy.fxtrader.model.TierType;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingAmount;

@Component
public class PricingAmountService {

    private static Logger LOGGER = LoggerFactory.getLogger(PricingAmountService.class);

    @Autowired
    private PricingUtilService pricingUtilService;
    
//    public List<PricingAmount> getAllPricingAmount(Boolean isParent) {
//        List<PricingAmount> allPricingAmount = pricingUtilService.getAllTiers(TierType.SALES, isParent);
//        return allPricingAmount;
//    }
//    
//    public PricingAmount getPricingAmount(String name) {
//    	List<PricingAmount> salesTier = pricingUtilService.getAllTiers(Arrays.asList(TierType.DEFAULT_SALES, TierType.SALES), name);
//    	return CollectionUtils.isNotEmpty(salesTier) ? salesTier.get(0): null;
//    }
//
//    public PricingAmount getDefaultSalesTier(String name) {
//        List<PricingAmount> allSalesTiers = pricingUtilService.getAllTiers(Arrays.asList(TierType.DEFAULT_SALES), name);
//        return CollectionUtils.isNotEmpty(allSalesTiers) ? allSalesTiers.get(0): null;
//    }

    public PricingAmount createPricingAmount(PricingAmount pricingAmount) {
    	pricingAmount.setId(UUID.randomUUID().toString());// TODO Implementation
        return pricingAmount;
    }

    public PricingAmount updatePricingAmount(PricingAmount pricingAmount) {
    	pricingAmount.setId(UUID.randomUUID().toString());// TODO Implementation
        return pricingAmount;
    }
    
    public String deletePricingAmount(String pricingAmountId) {
    	// TODO Implementation
        return "Pricing Amount with ID: "+pricingAmountId+" has been deleted successfully" ;
    }

}
