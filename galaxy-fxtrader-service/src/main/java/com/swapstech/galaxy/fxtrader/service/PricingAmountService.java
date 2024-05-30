package com.swapstech.galaxy.fxtrader.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swapstech.galaxy.fxtrader.repository.PricingAmountRepository;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingAmount;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingAmountTierRange;

@Component
public class PricingAmountService {

    private static Logger LOGGER = LoggerFactory.getLogger(PricingAmountService.class);

    @Autowired
    private PricingUtilService pricingUtilService;

    @Autowired
    PricingAmountRepository pricingAmountRepository;
    
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
		if (pricingAmount.getId() == null) {
			validateAmountRange(pricingAmount.getAmountRanges());
		}
        return pricingUtilService.savePricingAmount(pricingAmount);
    }

    public PricingAmount updatePricingAmount(PricingAmount pricingAmount) {
    	validateAmountRange(pricingAmount.getAmountRanges());
        return pricingUtilService.updatePricingAmount(pricingAmount);
    }
    
    public String deletePricingAmount(String pricingAmountId) {
    	// TODO Implementation
    	Optional<com.swapstech.galaxy.fxtrader.model.PricingAmount> existingPricingAmt = pricingAmountRepository.findById(UUID.fromString(pricingAmountId));
        if (existingPricingAmt.isPresent()) {
        	pricingAmountRepository.deleteById(UUID.fromString(pricingAmountId));
            return "Pricing Amount with ID: "+pricingAmountId+" has been deleted successfully";
        } else {
            throw new RuntimeException("PricingAmount not found");
        }
    }
    
    public void validateAmountRange(List<PricingAmountTierRange> pricingAmountRange) {
		if (pricingAmountRange == null || pricingAmountRange.isEmpty()) {
			throw new IllegalArgumentException("Amount range list cannot be null or empty.");
		}
		for (int i = 0; i < pricingAmountRange.size(); i++) {
			PricingAmountTierRange amountRange = pricingAmountRange.get(i);

			if (amountRange.getAmountFrom() == null || amountRange.getAmountTo() == null) {
				throw new IllegalArgumentException("From Amount or To Amount cannot be NULL.");
			}
			if (amountRange.getAmountFrom().compareTo(BigDecimal.ZERO) < 0
					|| amountRange.getAmountTo().compareTo(BigDecimal.ZERO) <= 0) {
				throw new IllegalArgumentException("From Amount or To Amount cannot be less than zero.");
			}
			if (amountRange.getAmountFrom().compareTo(amountRange.getAmountTo()) >= 0) {
				throw new IllegalArgumentException("From Amount should be less than To Amount.");
			}

			if (i > 0) {
				PricingAmountTierRange previousRange = pricingAmountRange.get(i - 1);
				if (amountRange.getAmountFrom().compareTo(previousRange.getAmountTo()) <= 0) {
					throw new IllegalArgumentException("The 'amountFrom' of the range with ID: " + amountRange.getId()
							+ " must be greater than the 'amountTo' of the range with ID: " + previousRange.getId());
				}
			}
		}
	}

}
