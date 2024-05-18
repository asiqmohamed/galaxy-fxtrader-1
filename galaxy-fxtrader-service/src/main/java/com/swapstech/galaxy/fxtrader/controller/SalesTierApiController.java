package com.swapstech.galaxy.fxtrader.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapstech.galaxy.common.api.model.APIResponse;
import com.swapstech.galaxy.fxtrader.api.SalesTierApi;
import com.swapstech.galaxy.fxtrader.service.SalesTierService;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTier;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@Tag(name = "SalesPricingAPI-controller", description = "The Sales Pricing API")
public class SalesTierApiController implements SalesTierApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(SalesTierApiController.class);

	@Autowired
	ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	SalesTierService salesTierService;

	@Autowired
	public SalesTierApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public Optional<HttpServletRequest> getRequest() {
		return Optional.ofNullable(request);
	}

	@Override
	public APIResponse getAllSalesTiers() {
		List<PricingTier> pricingTierList = null;
		try {
			pricingTierList = salesTierService.getAllSalesTiers();
			if(CollectionUtils.isNotEmpty(pricingTierList)) {
				return new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
						pricingTierList);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception while fetching Sales tiers.", ex);
			return new APIResponse(HttpStatus.EXPECTATION_FAILED.name(), HttpStatus.EXPECTATION_FAILED.value(),
					ex.getMessage());
		}
		return null;
	}

	public ResponseEntity<APIResponse> getDefaultSalesTierByName(String tierName) {
		List<PricingTier> pricingTierList = null;
		try {
			pricingTierList = salesTierService.getDefaultSalesTier(tierName);
			if(CollectionUtils.isNotEmpty(pricingTierList)) {
				return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
						pricingTierList), HttpStatus.OK);
			}
		} catch (Exception ex) {
			LOGGER.error("Exception while fetching Sales tiers.", ex);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.EXPECTATION_FAILED.value(),
					null), HttpStatus.EXPECTATION_FAILED);
		}
		return null;
	}


}
