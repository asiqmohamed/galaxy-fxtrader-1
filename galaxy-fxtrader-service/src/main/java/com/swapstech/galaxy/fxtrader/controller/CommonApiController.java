package com.swapstech.galaxy.fxtrader.controller;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapstech.galaxy.common.api.model.APIResponse;
import com.swapstech.galaxy.fxtrader.api.CommonTraderApi;
import com.swapstech.galaxy.fxtrader.service.PricingUtilService;
import com.swapstech.galxy.fxtrader.client.pricing.model.FXTraderException;

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
	public APIResponse enableTier(String tierType, String tierId) {
		String pricingTier = null;
		try {
			pricingTier = pricingUtilService.updateTierStatus(tierType, tierId, true);
			if (Objects.nonNull(pricingTier)) {
				return new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), pricingTier);
			}
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while updating tiers.", ex);
			return new APIResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage()
					);
		} catch (Exception e) {
            LOGGER.error("Exception while updating tiers.", e);
            return new APIResponse(HttpStatus.EXPECTATION_FAILED.name(), HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
        }
		return null;
	}
	
	@Override
	public APIResponse disableTier(String tierType, String tierId) {
		String pricingTier = null;
		try {
			pricingTier = pricingUtilService.updateTierStatus(tierType, tierId, false);
			if (Objects.nonNull(pricingTier)) {
				return new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(), pricingTier);
			}
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while updating tiers.", ex);
			return new APIResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage()
					);
		} catch (Exception e) {
            LOGGER.error("Exception while updating tiers.", e);
            return new APIResponse(HttpStatus.EXPECTATION_FAILED.name(), HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
        }
		return null;
	}


}
