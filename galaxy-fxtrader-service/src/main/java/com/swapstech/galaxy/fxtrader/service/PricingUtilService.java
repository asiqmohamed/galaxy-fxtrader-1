package com.swapstech.galaxy.fxtrader.service;

import com.swapstech.galaxy.fxtrader.model.*;
import com.swapstech.galaxy.fxtrader.repository.PricingTierRepository;
import com.swapstech.galxy.fxtrader.client.pricing.model.*;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingAmount;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTier;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PricingUtilService {

    private static Logger LOGGER = LoggerFactory.getLogger(PricingUtilService.class);

    @Autowired
    PricingTierRepository pricingTierRepository;


    public List<PricingTier> getAllTiers(List<TierType> tierTypes, String tierName) {
        return fetchTierDetails(tierTypes, tierName);
    }
    
    public PricingTier savePricingTier(PricingTier pricingTier) {
    	com.swapstech.galaxy.fxtrader.model.PricingTier convertedServiceObj = null;
    	if(Objects.nonNull(pricingTier)) {
        	convertedServiceObj = convertToServiceModel(pricingTier);
        	pricingTierRepository.save(convertedServiceObj);
        	return pricingTier;
        }
        return null;
    }
    
    public List<PricingTier> getAllTiers(TierType tierType, Boolean isParent) {
    	List<com.swapstech.galaxy.fxtrader.model.PricingTier> pricingTiers = pricingTierRepository.getAllTiers();
    	if(CollectionUtils.isNotEmpty(pricingTiers)) {
            return pricingTiers.stream().map(pricingTier -> convertToClientModel(pricingTier)).collect(Collectors.toList());
        }
        return null;
    }

    private List<PricingTier> fetchTierDetails(List<TierType> tierTypes, String tierName) {
        List<com.swapstech.galaxy.fxtrader.model.PricingTier> pricingTiers =
                pricingTierRepository.findAll(PricingSpecification.getPricingTiers(tierTypes.stream().map(tierType -> tierType.getValue()).toList(), tierName));
        if(CollectionUtils.isNotEmpty(pricingTiers)) {
            return pricingTiers.stream().map(pricingTier -> convertToClientModel(pricingTier)).collect(Collectors.toList());
        }
        return null;
    }

    private PricingTier convertToClientModel(com.swapstech.galaxy.fxtrader.model.PricingTier pricingTierEntity) {
        PricingTier pricingTier = new PricingTier();
        pricingTier.setId(pricingTierEntity.getId().toString());
        pricingTier.setTierName(pricingTierEntity.getName());

        pricingTier.setTierType(pricingTierEntity.getPricingItem().getTierType().name());

        pricingTier.setDefaultTierId(String.valueOf(pricingTierEntity.getPricingItem().getId()));

        pricingTier.setCcyGroups(covertCcyGrpToClientModel(pricingTierEntity.getPricingCcyGroups()));
        pricingTier.setChannels(pricingTierEntity.getPricingItem().getChannels());

        return pricingTier;
    }
    
    private com.swapstech.galaxy.fxtrader.model.PricingTier convertToServiceModel(PricingTier pricingTierEntity) {
    	com.swapstech.galaxy.fxtrader.model.PricingTier pricingTier = new com.swapstech.galaxy.fxtrader.model.PricingTier();
    	pricingTier.setId(UUID.fromString(pricingTierEntity.getId()));
    	
    	pricingTier.setName(pricingTierEntity.getTierName());
    	
    	com.swapstech.galaxy.fxtrader.model.PricingTierItem pricingTierPricingItem = new com.swapstech.galaxy.fxtrader.model.PricingTierItem();
    	pricingTierPricingItem.setTierType(TierType.fromValue(Integer.valueOf(pricingTierEntity.getTierType())));
    	pricingTierPricingItem.setId(UUID.fromString(pricingTierEntity.getDefaultTierId()));
    	pricingTierPricingItem.setChannels(pricingTierEntity.getChannels());
    	pricingTier.setPricingItem(pricingTierPricingItem);
    	
    	
    	pricingTier.setPricingCcyGroups(covertCcyGrpToServiceModel(pricingTierEntity.getCcyGroups()));
    	
    	
    	return pricingTier;
    }

    private List<PricingCcyGroup> covertCcyGrpToClientModel(List<PricingCurrencyGroup> currencyGroups) {
        List<PricingCcyGroup> ccyGroups = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(currencyGroups)) {
            return currencyGroups.stream().map(ccyGrp -> covertCcyGrpToClientModel(ccyGrp)).collect(Collectors.toList());
        }
        return null;
    }
    
    private List<PricingCurrencyGroup> covertCcyGrpToServiceModel(List<PricingCcyGroup> currencyGroups) {
    	List<PricingCurrencyGroup> ccyGroups = new ArrayList<>();
    	if(CollectionUtils.isNotEmpty(currencyGroups)) {
    		return currencyGroups.stream().map(ccyGrp -> covertCcyGrpToServiceModel(ccyGrp)).collect(Collectors.toList());
    	}
    	return null;
    }

    private PricingCcyGroup covertCcyGrpToClientModel(PricingCurrencyGroup currencyGroup) {
        if(Objects.nonNull(currencyGroup)) {
            return new PricingCcyGroup().id(currencyGroup.getId().toString())
                    .name(currencyGroup.getName())
                    .pricingCcySet(convertPricingCurrenciesToClientModel(currencyGroup.getPricingCurrencies()))
                    .tenors(convertPricingTenorsToClientModel(currencyGroup.getPricingTenorRanges()));
        }
        return null;
    }
    
    private PricingCurrencyGroup covertCcyGrpToServiceModel(PricingCcyGroup currencyGroup) {
    	if(Objects.nonNull(currencyGroup)) {
    		PricingCurrencyGroup group = new PricingCurrencyGroup();
    		group.setId(UUID.fromString(currencyGroup.getId()));
    		group.setName(currencyGroup.getName());
    		group.setPricingCurrencies(convertPricingCurrenciesToServiceModel(currencyGroup.getPricingCcySet()));
    		group.setPricingTenorRanges(convertPricingTenorsToServiceModel(currencyGroup.getTenors()));
    		return group;
    	}
    	return null;
    }

    private List<PricingCcySet> convertPricingCurrenciesToClientModel(List<PricingCurrencySet> currencySets) {
        if(CollectionUtils.isNotEmpty(currencySets)) {
            return currencySets.stream().map(ccy -> convertPricingCcyToClientModel(ccy)).collect(Collectors.toList());
        }
        return null;
    }
    
    private List<PricingCurrencySet> convertPricingCurrenciesToServiceModel(List<PricingCcySet> currencySets) {
    	if(CollectionUtils.isNotEmpty(currencySets)) {
    		return currencySets.stream().map(ccy -> convertPricingCcyToServiceModel(ccy)).collect(Collectors.toList());
    	}
    	return null;
    }

    private List<PricingTenor> convertPricingTenorsToClientModel(List<PricingTenorRange> pricingTenorRanges) {
        if(CollectionUtils.isNotEmpty(pricingTenorRanges)) {
             return pricingTenorRanges.stream().map(pricingTenorRange -> convertPricingTenorRangeToClientModel(pricingTenorRange)).collect(Collectors.toList());
        }
        return null;
    }
    
    private List<PricingTenorRange> convertPricingTenorsToServiceModel(List<PricingTenor> pricingTenorRanges) {
    	if(CollectionUtils.isNotEmpty(pricingTenorRanges)) {
    		return pricingTenorRanges.stream().map(pricingTenorRange -> convertPricingTenorRangeToServiceModel(pricingTenorRange)).collect(Collectors.toList());
    	}
    	return null;
    }

    private PricingCcySet convertPricingCcyToClientModel(PricingCurrencySet currencySet) {
        if(Objects.nonNull(currencySet)) {
            return new PricingCcySet().id(currencySet.getId().toString())
                    .ccyPair(currencySet.getCcyPair());
        }
        return null;
    }
    
    private PricingCurrencySet convertPricingCcyToServiceModel(PricingCcySet currencySet) {
    	if(Objects.nonNull(currencySet)) {
    		PricingCurrencySet ccySet = new PricingCurrencySet();
    		ccySet.setId(UUID.fromString(currencySet.getId()));
    		ccySet.setCcyPair(currencySet.getCcyPair());
    		return ccySet;
    	}
    	return null;
    }

    private PricingTenor convertPricingTenorRangeToClientModel(PricingTenorRange pricingTenorRange) {
        if(Objects.nonNull(pricingTenorRange)) {
            return new PricingTenor().id(pricingTenorRange.getId().toString())
                    .rangeFrom(pricingTenorRange.getRangeFrom()).rangeTo(pricingTenorRange.getRangeTo())
                    .pricingAmount(convertPricingAmountToClientModel(pricingTenorRange.getPricingAmount()));
        }
        return null;
    }
    
    private PricingTenorRange convertPricingTenorRangeToServiceModel(PricingTenor pricngTenorRange) {
    	if(Objects.nonNull(pricngTenorRange)) {
    		PricingTenorRange pricingTenorRange = new PricingTenorRange();
    		pricingTenorRange.setId(UUID.fromString(pricngTenorRange.getId()));
    		pricingTenorRange.setRangeFrom(pricngTenorRange.getRangeFrom());
    		pricingTenorRange.setRangeTo(pricngTenorRange.getRangeTo());
    		if(Objects.nonNull(pricngTenorRange.getPricingAmount())) {
        		pricingTenorRange.setPricingAmount(convertPricingAmountToServiceModel(pricngTenorRange.getPricingAmount()));
    		}
    		return pricingTenorRange;
    	}
    	return null;
    }

    private PricingAmount convertPricingAmountToClientModel(com.swapstech.galaxy.fxtrader.model.PricingAmount pricingAmount) {
        if(Objects.nonNull(pricingAmount)) {
            return new PricingAmount().id(pricingAmount.getId().toString())
                    .amountRanges(convertPricingAmountRangesToClientModel(pricingAmount.getPricingAmountRanges()));
        }
        return null;
    }
    
    private com.swapstech.galaxy.fxtrader.model.PricingAmount convertPricingAmountToServiceModel(PricingAmount pricingAmount) {
    	if(Objects.nonNull(pricingAmount)) {
    		com.swapstech.galaxy.fxtrader.model.PricingAmount pricingAmt = new com.swapstech.galaxy.fxtrader.model.PricingAmount();
    		pricingAmt.setId(UUID.fromString(pricingAmount.getId()));
    		if(Objects.nonNull(pricingAmount.getAmountRanges())) {
        		pricingAmt.setPricingAmountRanges(convertPricingAmountRangesToServiceModel(pricingAmount.getAmountRanges()));
    		}
    		return pricingAmt;
    	}
    	return null;
    }

    private List<PricingAmountTierRange> convertPricingAmountRangesToClientModel(List<PricingAmountRange> pricingAmountRanges) {
        if(CollectionUtils.isNotEmpty(pricingAmountRanges)) {
            return pricingAmountRanges.stream().map(amtRange -> convertPricingAmountRangeToClientModel(amtRange)).collect(Collectors.toList());
        }
        return null;
    }
    
    private List<PricingAmountRange> convertPricingAmountRangesToServiceModel(List<PricingAmountTierRange> pricingAmountRanges) {
    	if(CollectionUtils.isNotEmpty(pricingAmountRanges)) {
    		return pricingAmountRanges.stream().map(amtRange -> convertPricingAmountRangeToServiceModel(amtRange)).collect(Collectors.toList());
    	}
    	return null;
    }

    private PricingAmountTierRange convertPricingAmountRangeToClientModel(PricingAmountRange pricingAmountRange) {
        if(Objects.nonNull(pricingAmountRange)) {
            return new PricingAmountTierRange().id(pricingAmountRange.getId().toString())
                    .amountFrom(pricingAmountRange.getAmountFrom())
                    .amountTo(pricingAmountRange.getAmountTo())
                    .bankBuys(pricingAmountRange.getBankBuys())
                    .bankSells(pricingAmountRange.getBankSells())
                    .spreadUnit(pricingAmountRange.getSpreadUnit().name());
        }
        return null;
    }
    
    private PricingAmountRange convertPricingAmountRangeToServiceModel(PricingAmountTierRange pricingAmountRange) {
    	if(Objects.nonNull(pricingAmountRange)) {
    		PricingAmountRange pricngAmtRange = new PricingAmountRange();
    		pricngAmtRange.setId(UUID.fromString(pricingAmountRange.getId()));
    		pricngAmtRange.setAmountFrom(pricingAmountRange.getAmountFrom());
    		pricngAmtRange.setAmountTo(pricingAmountRange.getAmountTo());
    		pricngAmtRange.setBankBuys(pricingAmountRange.getBankBuys());
    		pricngAmtRange.setBankSells(pricingAmountRange.getBankSells());
    		int spreadUnitValue = Integer.parseInt(pricingAmountRange.getSpreadUnit());
    		SpreadUnit spreadUnit = SpreadUnit.fromValue(spreadUnitValue);
    		pricngAmtRange.setSpreadUnit(spreadUnit);
    		return pricngAmtRange;
    	}
    	return null;
    }

}
