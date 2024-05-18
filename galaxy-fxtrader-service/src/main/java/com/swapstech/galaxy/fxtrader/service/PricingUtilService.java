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


    public List<PricingTier> getAllTiers(TierType tierType, String tierName) {
        return fetchTierDetails(tierType, tierName);
    }

    private List<PricingTier> fetchTierDetails(TierType tierType, String tierName) {
        List<com.swapstech.galaxy.fxtrader.model.PricingTier> pricingTiers =
                pricingTierRepository.findAll(PricingSpecification.getPricingTiers(tierType, tierName));
        if(CollectionUtils.isNotEmpty(pricingTiers)) {
            return pricingTiers.stream().map(pricingTier -> convertToClientModel(pricingTier)).collect(Collectors.toList());
        }
        return null;
    }

    private PricingTier buildPricingTier() {
        PricingTier pricingTier = new PricingTier().tierName("Gold");
        return pricingTier;
    }

    private PricingTier convertToClientModel(com.swapstech.galaxy.fxtrader.model.PricingTier pricingTierEntity) {
        PricingTier pricingTier = new PricingTier();
        pricingTier.setId(pricingTierEntity.getId().toString());
        pricingTier.setTierName(pricingTierEntity.getName());

        pricingTier.setTierType(pricingTierEntity.getPricingItem().getTierType().name());

        pricingTier.setDefaultTier(pricingTierEntity.getPricingItem().getId().toString());

        pricingTier.setCcyGroups(covertCcyGrpToClientModel(pricingTierEntity.getPricingCcyGroups()));
        pricingTier.setChannels(pricingTierEntity.getPricingItem().getChannels());

        return pricingTier;
    }

    private List<PricingCcyGroup> covertCcyGrpToClientModel(List<PricingCurrencyGroup> currencyGroups) {
        List<PricingCcyGroup> ccyGroups = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(currencyGroups)) {
            return currencyGroups.stream().map(ccyGrp -> covertCcyGrpToClientModel(ccyGrp)).collect(Collectors.toList());
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

    private List<PricingCcySet> convertPricingCurrenciesToClientModel(List<PricingCurrencySet> currencySets) {
        if(CollectionUtils.isNotEmpty(currencySets)) {
            return currencySets.stream().map(ccy -> convertPricingCcyToClientModel(ccy)).collect(Collectors.toList());
        }
        return null;
    }

    private List<PricingTenor> convertPricingTenorsToClientModel(List<PricingTenorRange> pricingTenorRanges) {
        if(CollectionUtils.isNotEmpty(pricingTenorRanges)) {
             return pricingTenorRanges.stream().map(pricingTenorRange -> convertPricingTenorRangeToClientModel(pricingTenorRange)).collect(Collectors.toList());
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

    private PricingTenor convertPricingTenorRangeToClientModel(PricingTenorRange pricingTenorRange) {
        if(Objects.nonNull(pricingTenorRange)) {
            return new PricingTenor().id(pricingTenorRange.getId().toString())
                    .rangeFrom(pricingTenorRange.getRangeFrom()).rangeTo(pricingTenorRange.getRangeTo())
                    .pricingAmount(convertPricingAmountToClientModel(pricingTenorRange.getPricingAmount()));
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

    private List<PricingAmountTierRange> convertPricingAmountRangesToClientModel(List<PricingAmountRange> pricingAmountRanges) {
        if(CollectionUtils.isNotEmpty(pricingAmountRanges)) {
            return pricingAmountRanges.stream().map(amtRange -> convertPricingAmountRangeToClientModel(amtRange)).collect(Collectors.toList());
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

}
