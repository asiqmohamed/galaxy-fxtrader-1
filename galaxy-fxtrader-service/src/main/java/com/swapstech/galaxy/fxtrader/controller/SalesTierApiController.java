package com.swapstech.galaxy.fxtrader.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapstech.galaxy.common.api.model.APIResponse;
import com.swapstech.galaxy.fxtrader.api.SalesTierApi;
import com.swapstech.galaxy.fxtrader.service.SalesTierService;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTier;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

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
	public APIResponse getAllSalesTiers(Boolean isParent) {
		List<PricingTier> pricingTierList = null;
		try {
			pricingTierList = salesTierService.getAllSalesTiers(isParent);
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
	
	@Override
	public ResponseEntity<APIResponse> getSalesTierByName(String tierName) {
		PricingTier pricingTier = null;
		try {
			pricingTier = salesTierService.getSalesTier(tierName);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
						pricingTier), HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Exception while fetching Sales tiers.", ex);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.EXPECTATION_FAILED.value(),
					null), HttpStatus.EXPECTATION_FAILED);
		}
	}

	@Override
	public ResponseEntity<APIResponse> getDefaultSalesTierByName(String tierName) {
		PricingTier pricingTier = null;
		try {
			pricingTier = salesTierService.getDefaultSalesTier(tierName);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
						pricingTier), HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Exception while fetching Sales tiers.", ex);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.EXPECTATION_FAILED.value(),
					null), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@Override
	public ResponseEntity<APIResponse> createSalesTier(PricingTier pricingTier) {
		PricingTier savedPricingTier = null;
		try {
			savedPricingTier = salesTierService.createSalesTier(pricingTier);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
					savedPricingTier), HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Exception while fetching Sales tiers.", ex);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.EXPECTATION_FAILED.value(),
					null), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@Override
	public ResponseEntity<APIResponse> updateSalesTier(PricingTier pricingTier, String tierId) {
		PricingTier savedPricingTier = null;
		try {
			savedPricingTier = salesTierService.updateSalesTier(pricingTier);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
					savedPricingTier), HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Exception while fetching Sales tiers.", ex);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.EXPECTATION_FAILED.value(),
					null), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@Override
	public ResponseEntity<APIResponse> deleteSalesTier(String tierId) {
		String deleteStatus = null;
		try {
			deleteStatus = salesTierService.deleteSalesTier(tierId);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
					deleteStatus), HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Exception while deleting Sales tiers.", ex);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.EXPECTATION_FAILED.value(),
					null), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	


}
