package com.swapstech.galaxy.fxtrader.service;

import com.swapstech.galaxy.fxtrader.model.TierType;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTier;

import org.apache.commons.collections4.CollectionUtils;
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
public class TradingTierService {

    private static Logger LOGGER = LoggerFactory.getLogger(TradingTierService.class);

    @Autowired
    private PricingUtilService pricingUtilService;

    public List<PricingTier> getAllTradingTiers(Boolean isParent) {
        List<PricingTier> allTradingTiers = pricingUtilService.getAllTiers(TierType.TRADING, isParent);
        return allTradingTiers;
    }
    
    public PricingTier getTradingTier(String name) {
    	List<PricingTier> tradingTier = pricingUtilService.getAllTiers(Arrays.asList(TierType.DEFAULT_TRADING, TierType.TRADING), name);
    	return CollectionUtils.isNotEmpty(tradingTier) ? tradingTier.get(0): null;
    }

    public PricingTier getDefaultTradingTier(String name) {
        List<PricingTier> allTradingTiers = pricingUtilService.getAllTiers(Arrays.asList(TierType.DEFAULT_TRADING), name);
        return CollectionUtils.isNotEmpty(allTradingTiers) ? allTradingTiers.get(0): null;
    }
    
    public PricingTier createTradingTier(PricingTier pricingTier) {
    	// TODO Implementation
        return pricingUtilService.savePricingTier(pricingTier);
    }

    public PricingTier updateTradingTier(PricingTier pricingTier) {
    	// TODO Implementation
        return pricingTier;
    }
    
    public String deleteTradingTier(String tierId) {
    	// TODO Implementation
        return "Trading Tier "+tierId+" has been deleted successfully" ;
    }



}
