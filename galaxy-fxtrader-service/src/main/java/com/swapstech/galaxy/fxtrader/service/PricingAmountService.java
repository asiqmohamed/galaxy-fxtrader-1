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
    
    public List<PricingAmount> getAllPricingAmounts() {
        List<PricingAmount> allPricingAmount = null;
    	allPricingAmount = pricingUtilService.getAllPricingAmount();
        return CollectionUtils.isNotEmpty(allPricingAmount) ? allPricingAmount: null ;
    }
    
    public PricingAmount getPricingAmount(String pricingAmountId) {
    	PricingAmount pricingAmount = null;
        //pricingAmount = pricingUtilService.getPricingAmount(pricingAmountId);
    	return pricingAmount;
    }

    public PricingAmount createPricingAmount(PricingAmount pricingAmount) {
    	// TODO Implementation
        return pricingUtilService.savePricingAmount(pricingAmount);
    }

    public PricingAmount updatePricingAmount(PricingAmount pricingAmount) {
    	// TODO Implementation
        return pricingAmount;
    }
    
    public String deletePricingAmount(String pricingAmountId) {
    	// TODO Implementation
        return "Pricing Amount with ID: "+pricingAmountId+" has been deleted successfully" ;
    }

}
