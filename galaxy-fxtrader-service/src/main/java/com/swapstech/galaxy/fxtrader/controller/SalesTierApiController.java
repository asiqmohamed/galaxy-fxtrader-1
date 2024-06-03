package com.swapstech.galaxy.fxtrader.controller;

import java.util.List;
import java.util.Optional;

import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTierItem;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapstech.galaxy.common.api.model.APIResponse;
import com.swapstech.galaxy.fxtrader.api.SalesTierApi;
import com.swapstech.galaxy.fxtrader.service.SalesTierService;
import com.swapstech.galxy.fxtrader.client.pricing.model.FXTraderException;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTier;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

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
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while fetching Sales tiers.", ex);
			return new APIResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage());
		} catch (Exception e) {
            LOGGER.error("Exception while fetching Sales tiers", e);
            return new APIResponse(HttpStatus.EXPECTATION_FAILED.name(), HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
        }
		return null;
	}
	
	@Override
	public ResponseEntity<APIResponse> getSalesTierById(String tierId) {
		PricingTier pricingTier = null;
		try {
			pricingTier = salesTierService.getSalesTier(tierId);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
						pricingTier), HttpStatus.OK);
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while fetching Sales tiers.", ex);
			return new ResponseEntity<>(new APIResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage()
					), HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
            LOGGER.error("Exception while fetching Sales tiers", e);
            return new ResponseEntity<APIResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@Override
	public ResponseEntity<APIResponse> createSalesTier(PricingTier pricingTier) {
		PricingTier savedPricingTier = null;
		try {
			savedPricingTier = salesTierService.createSalesTier(pricingTier);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
					savedPricingTier), HttpStatus.OK);
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while creating Sales tiers.", ex);
			return new ResponseEntity<>(new APIResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage()
					), HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
            LOGGER.error("Exception while fetching Sales tiers", e);
            return new ResponseEntity<APIResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}

	@Override
	public ResponseEntity<APIResponse> createSalesTierItem(PricingTierItem pricingTierItem, String tierId) {
		PricingTierItem savedPricingTierItem = null;
		try {
			savedPricingTierItem = salesTierService.createSalesTierItem(tierId, pricingTierItem);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
					savedPricingTierItem), HttpStatus.OK);
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while fetching Sales tiers.", ex);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.EXPECTATION_FAILED.value(),
					null), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@Override
	public ResponseEntity<APIResponse> updateSalesTierItem(PricingTierItem pricingTierItem, String tierId) {
		PricingTierItem savedPricingTierItem = null;
		try {
			savedPricingTierItem = salesTierService.updateSalesTierItem(pricingTierItem, tierId);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
					savedPricingTierItem), HttpStatus.OK);
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while updating Sales tiers.", ex);
			return new ResponseEntity<>(new APIResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage()
					), HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
            LOGGER.error("Exception while fetching Sales tiers", e);
            return new ResponseEntity<APIResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@Override
	public ResponseEntity<APIResponse> deleteSalesTier(String tierId, String tireItemId) {
		String deleteStatus = null;
		try {
			deleteStatus = salesTierService.deleteSalesTier(tierId, tireItemId);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
					deleteStatus), HttpStatus.OK);
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while deleting Sales tiers.", ex);
			return new ResponseEntity<>(new APIResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage()
					), HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
            LOGGER.error("Exception while fetching Sales tiers", e);
            return new ResponseEntity<APIResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	


}
