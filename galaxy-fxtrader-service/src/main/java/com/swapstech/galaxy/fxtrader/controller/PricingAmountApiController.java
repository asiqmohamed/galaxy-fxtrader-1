package com.swapstech.galaxy.fxtrader.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapstech.galaxy.common.api.model.APIResponse;
import com.swapstech.galaxy.fxtrader.api.PricingAmountApi;
import com.swapstech.galaxy.fxtrader.service.PricingAmountService;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingAmount;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@Tag(name = "PricingAmountAPI-controller", description = "The Pricing Amount API")
public class PricingAmountApiController implements PricingAmountApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(PricingAmountApiController.class);

	@Autowired
	ObjectMapper objectMapper;

	private final HttpServletRequest request;
	
	@Autowired
	PricingAmountService pricingAmountService;

	@Autowired
	public PricingAmountApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public Optional<HttpServletRequest> getRequest() {
		return Optional.ofNullable(request);
	}
	
	@Override
	public ResponseEntity<APIResponse> createPricingAmount(PricingAmount pricingAmount) {
		PricingAmount savedPricingAmount = null;
		try {
			savedPricingAmount = pricingAmountService.createPricingAmount(pricingAmount);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
					savedPricingAmount), HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Exception while fetching Pricing Amount.", ex);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.EXPECTATION_FAILED.value(),
					null), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@Override
	public ResponseEntity<APIResponse> updatePricingAmount(PricingAmount pricingAmount, String pricingAmountId) {
		PricingAmount updatedPricingTier = null;
		try {
			updatedPricingTier = pricingAmountService.updatePricingAmount(pricingAmount);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
					updatedPricingTier), HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Exception while fetching Pricing Amount.", ex);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.EXPECTATION_FAILED.value(),
					null), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@Override
	public ResponseEntity<APIResponse> deletePricingAmount(String pricingAmountId) {
		String deleteStatus = null;
		try {
			deleteStatus = pricingAmountService.deletePricingAmount(pricingAmountId);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
					deleteStatus), HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Exception while deleting PricingAmount.", ex);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.EXPECTATION_FAILED.value(),
					null), HttpStatus.EXPECTATION_FAILED);
		}
	}


}
