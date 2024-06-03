package com.swapstech.galaxy.fxtrader.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapstech.galaxy.common.api.model.APIResponse;
import com.swapstech.galaxy.fxtrader.api.TradingTierApi;
import com.swapstech.galaxy.fxtrader.service.TradingTierService;
import com.swapstech.galxy.fxtrader.client.pricing.model.FXTraderException;
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
@Tag(name = "TradingPricingAPI-controller", description = "The Trading Pricing API")
public class TradingTierApiController implements TradingTierApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(TradingTierApiController.class);

	@Autowired
	ObjectMapper objectMapper;

	private final HttpServletRequest request;
	
	@Autowired
	TradingTierService tradingTierService;

	@Autowired
	public TradingTierApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public Optional<HttpServletRequest> getRequest() {
		return Optional.ofNullable(request);
	}
	
	@Override
	public APIResponse getAllTradingTiers(Boolean isParent) {
		List<PricingTier> pricingTierList = null;
		try {
			pricingTierList = tradingTierService.getAllTradingTiers(isParent);
			if(CollectionUtils.isNotEmpty(pricingTierList)) {
				return new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
						pricingTierList);
			}
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while fetching Trading tiers.", ex);
			return new APIResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage()
					);
		} catch (Exception e) {
            LOGGER.error("Exception while fetching Trading tiers", e);
            return new APIResponse(HttpStatus.EXPECTATION_FAILED.name(), HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
        }
		return null;
	}
	
	@Override
	public ResponseEntity<APIResponse> getTradingTierById(String tierId) {
		PricingTier pricingTier = null;
		try {
			pricingTier = tradingTierService.getTradingTier(tierId);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
						pricingTier), HttpStatus.OK);
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while fetching Trading tiers.", ex);
			return new ResponseEntity<>(new APIResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage()
					), HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
            LOGGER.error("Exception while fetching Trading tiers", e);
            return new ResponseEntity<APIResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@Override
	public ResponseEntity<APIResponse> getTradingTierByName(String name) {
		PricingTier pricingTier = null;
		try {
			pricingTier = tradingTierService.getTradingTierByName(name);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
					pricingTier), HttpStatus.OK);
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while fetching Trading tiers.", ex);
			return new ResponseEntity<>(new APIResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage()
					), HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
			LOGGER.error("Exception while fetching Trading tiers", e);
			return new ResponseEntity<APIResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<APIResponse> createTradingTier(PricingTier pricingTier) {
		PricingTier savedPricingTier = null;
		try {
			savedPricingTier = tradingTierService.createTradingTier(pricingTier);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
					savedPricingTier), HttpStatus.OK);
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while creating Trading tiers.", ex);
			return new ResponseEntity<>(new APIResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage()
					), HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
            LOGGER.error("Exception while fetching Trading tiers", e);
            return new ResponseEntity<APIResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@Override
	public ResponseEntity<APIResponse> updateTradingTier(PricingTier pricingTier, String tierId) {
		PricingTier savedPricingTier = null;
		try {
			savedPricingTier = tradingTierService.updateTradingTier(pricingTier);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
					savedPricingTier), HttpStatus.OK);
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while updating Trading tiers.", ex);
			return new ResponseEntity<>(new APIResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage()
					), HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
            LOGGER.error("Exception while fetching Trading tiers", e);
            return new ResponseEntity<APIResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	@Override
	public ResponseEntity<APIResponse> deleteTradingTier(String tierId, String tierItemId) {
		String deleteStatus = null;
		try {
			deleteStatus = tradingTierService.deleteTradingTier(tierId, tierItemId);
			return new ResponseEntity<>(new APIResponse(HttpStatus.OK.name(), HttpStatus.OK.value(),
					deleteStatus), HttpStatus.OK);
		} catch (FXTraderException ex) {
			LOGGER.error("Exception while deleting Trading tiers.", ex);
			return new ResponseEntity<>(new APIResponse(ex.getStatus(), ex.getErrorCode(), ex.getMessage()
					), HttpStatus.EXPECTATION_FAILED);
		} catch (Exception e) {
            LOGGER.error("Exception while fetching Trading tiers", e);
            return new ResponseEntity<APIResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}


}
