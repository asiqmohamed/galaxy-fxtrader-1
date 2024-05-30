package com.swapstech.galaxy.fxtrader.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapstech.galaxy.common.api.model.APIResponse;
import com.swapstech.galaxy.fxtrader.api.CommonTraderApi;
import com.swapstech.galaxy.fxtrader.api.PricingAmountApi;
import com.swapstech.galaxy.fxtrader.service.PricingAmountService;
import com.swapstech.galaxy.fxtrader.service.PricingUtilService;
import com.swapstech.galxy.fxtrader.client.pricing.model.FXTraderException;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingAmount;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTier;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@Tag(name = "PricingAmountAPI-controller", description = "The Pricing Amount API")
public class CommonApiController implements CommonTraderApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonApiController.class);

	@Autowired
	ObjectMapper objectMapper;

	private final HttpServletRequest request;
	
	@Autowired
    private PricingUtilService pricingUtilService;

	@Autowired
	public CommonApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public Optional<HttpServletRequest> getRequest() {
		return Optional.ofNullable(request);
	}
	
	@Override
	public APIResponse updateTierStatus(String tierType, String tierId, Boolean status) {
		String pricingTier = null;
		try {
			pricingTier = pricingUtilService.updateTierStatus(tierType, tierId, status);
			if (Objects.nonNull(pricingTier)) {
				return new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), pricingTier);
			}
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while updating tiers.", ex);
			return new APIResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage()
					);
		}
		return null;
	}


}
