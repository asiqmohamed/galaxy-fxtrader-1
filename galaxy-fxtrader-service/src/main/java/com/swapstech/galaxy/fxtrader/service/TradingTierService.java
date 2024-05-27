package com.swapstech.galaxy.fxtrader.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swapstech.galaxy.fxtrader.model.TierType;
import com.swapstech.galaxy.fxtrader.repository.PricingTierRepository;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTier;

@Component
public class TradingTierService {

    private static Logger LOGGER = LoggerFactory.getLogger(TradingTierService.class);

    @Autowired
    private PricingUtilService pricingUtilService;
    
    @Autowired
    PricingTierRepository pricingTierRepository;

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
    	Optional<com.swapstech.galaxy.fxtrader.model.PricingTier> existingTier = pricingTierRepository.findById(UUID.fromString(tierId));
        if (existingTier.isPresent()) {
        	pricingTierRepository.deleteById(UUID.fromString(tierId));
            return "Trading Tier " + tierId + " has been deleted successfully";
        } else {
            throw new RuntimeException("PricingTier not found");
        }
    }



}
