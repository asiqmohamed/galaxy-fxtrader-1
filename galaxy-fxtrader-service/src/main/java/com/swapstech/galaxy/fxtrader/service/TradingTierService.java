package com.swapstech.galaxy.fxtrader.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
    
    public PricingTier getTradingTier(String id) {
        return pricingUtilService.getTierById(id);
    }
    
    public PricingTier createTradingTier(PricingTier pricingTier) {
    	// TODO Implementation
        return pricingUtilService.savePricingTier(pricingTier);
    }

    public PricingTier updateTradingTier(PricingTier pricingTier) {
    	// TODO Implementation
        return pricingTier;
    }
    
    public String deleteTradingTier(String tierId, String tierItemId) {
    	Optional<com.swapstech.galaxy.fxtrader.model.PricingTier> existingTier = pricingTierRepository.findById(UUID.fromString(tierId));
        if (existingTier.isPresent()) {
        	if(StringUtils.isNotBlank(tierItemId)) {
				existingTier.ifPresent(tier -> {
					tier.getPricingItem().stream().filter(item -> tierItemId.equals(item.getId())).findFirst()
							.ifPresent(item -> {
								tier.getPricingItem().remove(item);
							});
					pricingTierRepository.save(tier);
				});
        	}else {
            	pricingTierRepository.deleteById(UUID.fromString(tierId));
        	}
            return "Trading Tier " + tierId + " has been deleted successfully";
        } else {
            throw new RuntimeException("PricingTier not found");
        }
    }



}
