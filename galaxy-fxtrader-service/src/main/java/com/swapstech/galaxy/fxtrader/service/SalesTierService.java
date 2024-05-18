package com.swapstech.galaxy.fxtrader.service;

import com.swapstech.galaxy.fxtrader.model.TierType;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class SalesTierService {

    private static Logger LOGGER = LoggerFactory.getLogger(SalesTierService.class);

    @Autowired
    private PricingUtilService pricingUtilService;

    public List<PricingTier> getAllSalesTiers() {
        List<PricingTier> allSalesTiers = pricingUtilService.getAllTiers(TierType.SALES, null);
        return allSalesTiers;
    }

    public List<PricingTier> getDefaultSalesTier(String name) {
        List<PricingTier> allSalesTiers = pricingUtilService.getAllTiers(TierType.DEFAULT_SALES, name);
        return allSalesTiers;
    }



}
