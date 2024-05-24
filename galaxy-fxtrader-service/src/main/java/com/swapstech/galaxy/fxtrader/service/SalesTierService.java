package com.swapstech.galaxy.fxtrader.service;

import com.swapstech.galaxy.fxtrader.model.TierType;
import com.swapstech.galaxy.fxtrader.repository.PricingTierRepository;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTier;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class SalesTierService {

    private static Logger LOGGER = LoggerFactory.getLogger(SalesTierService.class);

    @Autowired
    private PricingUtilService pricingUtilService;
    
    @Autowired
    PricingTierRepository pricingTierRepository;

    public List<PricingTier> getAllSalesTiers(Boolean isParent) {
        List<PricingTier> allSalesTiers = pricingUtilService.getAllTiers(TierType.SALES, isParent);
        return allSalesTiers;
    }
    
    public PricingTier getSalesTier(String name) {
    	List<PricingTier> salesTier = pricingUtilService.getAllTiers(Arrays.asList(TierType.DEFAULT_SALES, TierType.SALES), name);
    	return CollectionUtils.isNotEmpty(salesTier) ? salesTier.get(0): null;
    }

    public PricingTier getDefaultSalesTier(String name) {
        List<PricingTier> allSalesTiers = pricingUtilService.getAllTiers(Arrays.asList(TierType.DEFAULT_SALES), name);
        return CollectionUtils.isNotEmpty(allSalesTiers) ? allSalesTiers.get(0): null;
    }
    
    public PricingTier createSalesTier(PricingTier pricingTier) {
    	// TODO Implementation
    	return pricingUtilService.savePricingTier(pricingTier);
    }

    public PricingTier updateSalesTier(PricingTier pricingTier) {
    	// TODO Implementation
        return pricingTier;
    }
    
    public String deleteSalesTier(String tierId) {
    	// TODO Implementation
        return "Sales Tier "+tierId+" has been deleted successfully" ;
    }


}
