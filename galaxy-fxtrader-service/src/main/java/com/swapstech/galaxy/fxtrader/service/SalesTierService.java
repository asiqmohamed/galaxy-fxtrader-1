package com.swapstech.galaxy.fxtrader.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTierItem;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.swapstech.galaxy.fxtrader.model.TierType;
import com.swapstech.galaxy.fxtrader.repository.PricingTierRepository;
import com.swapstech.galxy.fxtrader.client.pricing.model.FXTraderException;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTier;

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
    
    public PricingTier getSalesTier(String id) {
    	return pricingUtilService.getTierById(id);
    }
    
    public PricingTier getTierByName(String name) {
    	return pricingUtilService.getTierByName(name);
    }
    
    public PricingTier createSalesTier(PricingTier pricingTier) {
    	return pricingUtilService.savePricingTier(pricingTier);
    }

    public PricingTierItem createSalesTierItem(String tierId, PricingTierItem pricingTierItem) {
        return pricingUtilService.savePricingTierItem(tierId, pricingTierItem);
    }

    public PricingTierItem updateSalesTierItem(PricingTierItem pricingTierItem, String tierId) {
        return pricingUtilService.updatePricingTierItem(pricingTierItem, tierId);
    }
    
    public String deleteSalesTier(String tierId, String tierItemId) {
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
            return "Sales Tier " + tierId + " has been deleted successfully";
        } else {
            throw new RuntimeException("PricingTier not found");
        }
    }


}
