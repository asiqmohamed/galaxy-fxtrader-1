package com.swapstech.galaxy.fxtrader.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swapstech.galaxy.fxtrader.model.PricingAmountRange;
import com.swapstech.galaxy.fxtrader.model.PricingCurrencySet;
import com.swapstech.galaxy.fxtrader.model.PricingTenorRange;
import com.swapstech.galaxy.fxtrader.model.SpreadUnit;
import com.swapstech.galaxy.fxtrader.model.TierType;
import com.swapstech.galaxy.fxtrader.repository.PricingAmountRepository;
import com.swapstech.galaxy.fxtrader.repository.PricingTierRepository;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingAmount;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingAmountTierRange;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingCcySet;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTenor;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTier;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTierItem;

@Component
public class PricingUtilService {

    private static Logger LOGGER = LoggerFactory.getLogger(PricingUtilService.class);

    @Autowired
    PricingTierRepository pricingTierRepository;
    
    @Autowired
    PricingAmountRepository pricingAmountRepository;


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
    
	public String updateTierStatus(String tierType, String tierId, Boolean status) {
		if (org.apache.commons.lang.StringUtils.equalsIgnoreCase(tierType, TierType.SALES.name())
				|| org.apache.commons.lang.StringUtils.equalsIgnoreCase(tierType, TierType.TRADING.name())) {
			java.util.Optional<com.swapstech.galaxy.fxtrader.model.PricingTier> existingPricingTier = pricingTierRepository
					.findById(UUID.fromString(tierId));

			if (existingPricingTier.isPresent()) {
				com.swapstech.galaxy.fxtrader.model.PricingTier pricingTier = existingPricingTier.get();
				pricingTier.setEnabled(status);
				pricingTierRepository.save(pricingTier);
				if (org.apache.commons.lang.StringUtils.equalsIgnoreCase(tierType, TierType.SALES.name())) {
					return "Sales tier with ID: " + tierId + " has been updated successfully";
				} else {
					return "Trading tier with ID: " + tierId + " has been updated successfully";
				}
			} else {
				throw new RuntimeException(
						org.apache.commons.lang.StringUtils.equalsIgnoreCase(tierType, TierType.SALES.name())
								? "Sales tier not found"
								: "Trading tier not found");
			}
		} else if (org.apache.commons.lang.StringUtils.equalsIgnoreCase(tierType, "AMOUNT")) {
			java.util.Optional<com.swapstech.galaxy.fxtrader.model.PricingAmount> existingPricingAmt = pricingAmountRepository
					.findById(UUID.fromString(tierId));

			if (existingPricingAmt.isPresent()) {
				com.swapstech.galaxy.fxtrader.model.PricingAmount pricingAmount = existingPricingAmt.get();
				pricingAmount.setIsEnabled(status);
				pricingAmountRepository.save(pricingAmount);
				return "Pricing Amount with ID: " + tierId + " has been updated successfully";
			} else {
				throw new RuntimeException("PricingAmount not found");
			}
		}
		return null;
	}
    
    public PricingAmount savePricingAmount(PricingAmount pricingAmt) {
    	com.swapstech.galaxy.fxtrader.model.PricingAmount convertedServiceObj = null;
    	if(Objects.nonNull(pricingAmt)) {
        	convertedServiceObj = convertPricingAmountToServiceModel(pricingAmt);
        	pricingAmountRepository.save(convertedServiceObj);
        	return pricingAmt;
        }
        return null;
    }
    
    public List<PricingTier> getAllTiers(TierType tierType, Boolean isParent) {
		List<com.swapstech.galaxy.fxtrader.model.PricingTier> pricingTiers = new ArrayList<>();
		List<Object[]> allPricingTiers = new ArrayList<Object[]>();
		allPricingTiers = pricingTierRepository.getAllTiers(tierType.getValue());
    	if (!allPricingTiers.isEmpty()) {
    		com.swapstech.galaxy.fxtrader.model.PricingTier pricingTier = null;
			for (Object[] pt : allPricingTiers) {
				pricingTier = new com.swapstech.galaxy.fxtrader.model.PricingTier();
				pricingTier.setId(UUID.fromString(pt[0].toString()));
				pricingTier.setName(pt[1].toString());
				pricingTiers.add(pricingTier);
			}
		}
    	if(CollectionUtils.isNotEmpty(pricingTiers)) {
            return pricingTiers.stream().map(pricingTier -> convertToClientModel(pricingTier)).collect(Collectors.toList());
        }
        return null;
    }
    
    public List<PricingAmount> getAllPricingAmount() {
		List<com.swapstech.galaxy.fxtrader.model.PricingAmount> allPricingAmt = new ArrayList<>();
		allPricingAmt = pricingAmountRepository.findAll();
    	if(CollectionUtils.isNotEmpty(allPricingAmt)) {
    		return allPricingAmt.stream()
                    .map(pricingAmt -> convertPricingAmountToClientModel(pricingAmt))
                    .collect(Collectors.toList());
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
        pricingTier.setName(pricingTierEntity.getName());
		if (Objects.nonNull(pricingTierEntity.getTierType())) {
			pricingTier.setTierType(pricingTierEntity.getTierType().name());
		}
		if(Objects.nonNull(pricingTierEntity.getPricingItem())) {
			pricingTier.setPricingItem(convertPricingItemToClientModel(pricingTierEntity.getPricingItem()));
		}


        return pricingTier;
    }
    
    private List<PricingTierItem> convertPricingItemToClientModel(List<com.swapstech.galaxy.fxtrader.model.PricingTierItem> pricingTierItemService) {
    	if(CollectionUtils.isNotEmpty(pricingTierItemService)) {
            return pricingTierItemService.stream().map(ccy -> convertPricingItemToClientModel(ccy)).collect(Collectors.toList());
        }
        return null;
    }
    
    private List<com.swapstech.galaxy.fxtrader.model.PricingTierItem> convertPricingItemToServiceModel(List<PricingTierItem> pricingTierItemService, com.swapstech.galaxy.fxtrader.model.PricingTier pricingTier) {
    	if(CollectionUtils.isNotEmpty(pricingTierItemService)) {
            return pricingTierItemService.stream().map(ccy -> convertPricingItemToServiceModel(ccy,pricingTier)).collect(Collectors.toList());
        }
        return null;
    }
    
    private PricingTierItem convertPricingItemToClientModel(com.swapstech.galaxy.fxtrader.model.PricingTierItem pricingTierItemService){
    	PricingTierItem pricingTierItem = new PricingTierItem();
    	pricingTierItem.setAllDay(pricingTierItemService.isAllDay());
    	pricingTierItem.setChannels(pricingTierItemService.getChannels());
    	pricingTierItem.setDefault(Boolean.valueOf(pricingTierItemService.isDefault()));
    	pricingTierItem.setFromTime(pricingTierItemService.getFromTime());
        pricingTierItem.setId(String.valueOf(pricingTierItemService.getId()));
    	pricingTierItem.setNoQuoteMsg(pricingTierItemService.getNoQuoteMsg());
    	pricingTierItem.setRateSource(pricingTierItemService.getRateSource());
    	pricingTierItem.setToTime(pricingTierItemService.getToTime());
    	pricingTierItem.setCreatedBy(pricingTierItemService.getCreatedBy());
    	pricingTierItem.setCreationTime(pricingTierItemService.getCreationTime());
    	pricingTierItem.setLastUpdatedBy(pricingTierItemService.getLastUpdatedBy());
    	pricingTierItem.setLastUpdatedTime(pricingTierItemService.getLastUpdatedTime());
    	if(Objects.nonNull(pricingTierItemService.getPricingCurrencies())) {
        	pricingTierItem.setPricingCcySet(convertPricingCurrenciesToClientModel(pricingTierItemService.getPricingCurrencies()));
    	}
    	if(Objects.nonNull(pricingTierItemService.getPricingTenorRanges())) {
        	pricingTierItem.setTenors(convertPricingTenorsToClientModel(pricingTierItemService.getPricingTenorRanges()));
    	}
    	return pricingTierItem;
    }
    
    private com.swapstech.galaxy.fxtrader.model.PricingTierItem convertPricingItemToServiceModel(PricingTierItem pricingTierItemService, com.swapstech.galaxy.fxtrader.model.PricingTier pricingTier){
    	com.swapstech.galaxy.fxtrader.model.PricingTierItem pricingTierItem = new com.swapstech.galaxy.fxtrader.model.PricingTierItem();
    	pricingTierItem.setAllDay(pricingTierItemService.isAllDay());
    	pricingTierItem.setChannels(pricingTierItemService.getChannels());
    	pricingTierItem.setDefault(Boolean.valueOf(pricingTierItemService.isDefault()));
    	pricingTierItem.setFromTime(pricingTierItemService.getFromTime());
    	if(org.apache.commons.lang.StringUtils.isNotBlank(pricingTierItemService.getId())){
        	pricingTierItem.setId(UUID.fromString(pricingTierItemService.getId()));
    	}
    	pricingTierItem.setNoQuoteMsg(pricingTierItemService.getNoQuoteMsg());
    	pricingTierItem.setRateSource(pricingTierItemService.getRateSource());
    	pricingTierItem.setToTime(pricingTierItemService.getToTime());
    	pricingTierItem.setCreatedBy(pricingTierItemService.getCreatedBy());
    	pricingTierItem.setCreationTime(pricingTierItemService.getCreationTime());
    	pricingTierItem.setLastUpdatedBy(pricingTierItemService.getLastUpdatedBy());
    	pricingTierItem.setLastUpdatedTime(pricingTierItemService.getLastUpdatedTime());
    	if(Objects.nonNull(pricingTierItemService.getPricingCcySet())) {
        	pricingTierItem.setPricingCurrencies(convertPricingCurrenciesToServiceModel(pricingTierItemService.getPricingCcySet(), pricingTierItem));
    	}
    	if(Objects.nonNull(pricingTierItemService.getTenors())) {
        	pricingTierItem.setPricingTenorRanges(convertPricingTenorsToServiceModel(pricingTierItemService.getTenors(), pricingTierItem));
    	}
    	if(Objects.nonNull(pricingTier)) {
        	pricingTierItem.setPricingTier(pricingTier);
    	}
    	return pricingTierItem;
    }
    
    private com.swapstech.galaxy.fxtrader.model.PricingTier convertToServiceModel(PricingTier pricingTierEntity) {
    	com.swapstech.galaxy.fxtrader.model.PricingTier pricingTier = new com.swapstech.galaxy.fxtrader.model.PricingTier();
    	pricingTier.setId(Objects.nonNull(pricingTierEntity.getId()) ? UUID.fromString(pricingTierEntity.getId()):null);
    	pricingTier.setName(pricingTierEntity.getName());
    	if (Objects.nonNull(pricingTierEntity.getTierType())) {
			pricingTier.setTierType(TierType.fromValue(Integer.valueOf(pricingTierEntity.getTierType())));
		}
    	if(Objects.nonNull(pricingTierEntity.getPricingItem())) {
        	pricingTier.setPricingItem(convertPricingItemToServiceModel(pricingTierEntity.getPricingItem(),pricingTier));
    	}
    	
    	
    	return pricingTier;
    }

    private List<PricingCcySet> convertPricingCurrenciesToClientModel(List<PricingCurrencySet> currencySets) {
        if(CollectionUtils.isNotEmpty(currencySets)) {
            return currencySets.stream().map(ccy -> convertPricingCcyToClientModel(ccy)).collect(Collectors.toList());
        }
        return null;
    }
    
    private List<PricingCurrencySet> convertPricingCurrenciesToServiceModel(List<PricingCcySet> currencySets, com.swapstech.galaxy.fxtrader.model.PricingTierItem pricingTierItem) {
    	if(CollectionUtils.isNotEmpty(currencySets)) {
    		return currencySets.stream().map(ccy -> convertPricingCcyToServiceModel(ccy, pricingTierItem)).collect(Collectors.toList());
    	}
    	return null;
    }

    private List<PricingTenor> convertPricingTenorsToClientModel(List<PricingTenorRange> pricingTenorRanges) {
        if(CollectionUtils.isNotEmpty(pricingTenorRanges)) {
             return pricingTenorRanges.stream().map(pricingTenorRange -> convertPricingTenorRangeToClientModel(pricingTenorRange)).collect(Collectors.toList());
        }
        return null;
    }
    
    private List<PricingTenorRange> convertPricingTenorsToServiceModel(List<PricingTenor> pricingTenorRanges, com.swapstech.galaxy.fxtrader.model.PricingTierItem pricingTierItem) {
    	if(CollectionUtils.isNotEmpty(pricingTenorRanges)) {
    		return pricingTenorRanges.stream().map(pricingTenorRange -> convertPricingTenorRangeToServiceModel(pricingTenorRange, pricingTierItem)).collect(Collectors.toList());
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
    
    private PricingCurrencySet convertPricingCcyToServiceModel(PricingCcySet currencySet, com.swapstech.galaxy.fxtrader.model.PricingTierItem pricingTierItem) {
    	if(Objects.nonNull(currencySet)) {
    		PricingCurrencySet ccySet = new PricingCurrencySet();
    		ccySet.setId(Objects.nonNull(currencySet.getId()) ? UUID.fromString(currencySet.getId()):null);
    		ccySet.setCcyPair(currencySet.getCcyPair());
    		ccySet.setPricingTierItem(pricingTierItem);
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
    
    private PricingTenorRange convertPricingTenorRangeToServiceModel(PricingTenor pricngTenorRange, com.swapstech.galaxy.fxtrader.model.PricingTierItem pricingTierItem) {
    	if(Objects.nonNull(pricngTenorRange)) {
    		PricingTenorRange pricingTenorRange = new PricingTenorRange();
    		pricingTenorRange.setId(Objects.nonNull(pricngTenorRange.getId()) ? UUID.fromString(pricngTenorRange.getId()):null);
    		pricingTenorRange.setRangeFrom(pricngTenorRange.getRangeFrom());
    		pricingTenorRange.setRangeTo(pricngTenorRange.getRangeTo());
//    		if(Objects.nonNull(pricngTenorRange.getPricingAmount())) {
//        		pricingTenorRange.setPricingAmount(convertPricingAmountToServiceModel(pricngTenorRange.getPricingAmount()));
//    		}
    		pricingTenorRange.setPricingTierItem(pricingTierItem);
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
    		pricingAmt.setId(Objects.nonNull(pricingAmount.getId()) ? UUID.fromString(pricingAmount.getId()):null);
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
    		pricngAmtRange.setId(Objects.nonNull(pricingAmountRange.getId()) ? UUID.fromString(pricingAmountRange.getId()):null);
    		pricngAmtRange.setAmountFrom(pricingAmountRange.getAmountFrom());
    		pricngAmtRange.setAmountTo(pricingAmountRange.getAmountTo());
    		pricngAmtRange.setBankBuys(pricingAmountRange.getBankBuys());
    		pricngAmtRange.setBankSells(pricingAmountRange.getBankSells());
    		if(org.apache.commons.lang.StringUtils.isNotBlank(pricingAmountRange.getSpreadUnit())) {
    			int spreadUnitValue = Integer.parseInt(pricingAmountRange.getSpreadUnit());
        		SpreadUnit spreadUnit = SpreadUnit.fromValue(spreadUnitValue);
        		pricngAmtRange.setSpreadUnit(spreadUnit);
    		}
    		return pricngAmtRange;
    	}
    	return null;
    }

}
