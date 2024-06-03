package com.swapstech.galaxy.fxtrader.api;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapstech.galaxy.common.api.model.APIResponse;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingAmount;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * The Interface PricingAmountApi.
 */
@RestController
@RequestMapping("/pricingamount")
@Tag(name = "PricingAmountAPI-controller", description = "The Pricing Amount API")
public interface PricingAmountApi {

	/** The logger. */
	Logger LOGGER = LoggerFactory.getLogger(PricingAmountApi.class);

	/**
	 * Gets the object mapper.
	 *
	 * @return the object mapper
	 */
	default Optional<ObjectMapper> getObjectMapper() {
		return Optional.empty();
	}

	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	default Optional<HttpServletRequest> getRequest() {
		return Optional.empty();
	}

	/**
	 * Gets the accept header.
	 *
	 * @return the accept header
	 */
	default Optional<String> getAcceptHeader() {
		return getRequest().map(r -> r.getHeader("Accept"));
	}

	/**
	 * Create a new Pricing amount configuration.
	 * @param body {@link PricingAmount}
	 * @return PricingAmountTier
	 */
	@PostMapping(produces = "application/json", consumes = "application/json")
	default ResponseEntity<APIResponse> createPricingAmount(@Valid @RequestBody PricingAmount body) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {
				try {
					return new ResponseEntity<APIResponse>(HttpStatus.NOT_IMPLEMENTED);
				} catch (Exception e) {
					LOGGER.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<APIResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} else {
			LOGGER.warn(
					"ObjectMapper or HttpServletRequest not configured in default TradeApi interface so no example is generated");
		}
		return new ResponseEntity<APIResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Updates the Pricing Amount configuration
	 * @param body {@link PricingAmount}
	 * @param pricingAmountId
	 * @return {@link PricingAmount}
	 */
	@PutMapping(value = "/{pricing-amount-id}", produces = "application/json", consumes = "application/json")
	default ResponseEntity<APIResponse> updatePricingAmount(@Valid @RequestBody PricingAmount body, @Valid @PathVariable("pricing-amount-id") String pricingAmountId) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {
				try {
					return new ResponseEntity<APIResponse>(HttpStatus.NOT_IMPLEMENTED);
				} catch (Exception e) {
					LOGGER.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<APIResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} else {
			LOGGER.warn(
					"ObjectMapper or HttpServletRequest not configured in default TradeApi interface so no example is generated");
		}
		return new ResponseEntity<APIResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Delete the Pricing Amount configuration.
	 * @param pricingAmountId
	 * @return {@link PricingAmount}
	 */
	@DeleteMapping(value = "/{pricing-amount-id}", produces = "application/json", consumes = "application/json")
	default ResponseEntity<APIResponse> deletePricingAmount(@Valid @PathVariable("pricing-amount-id") String pricingAmountId) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {
				try {
					return new ResponseEntity<APIResponse>(HttpStatus.NOT_IMPLEMENTED);
				} catch (Exception e) {
					LOGGER.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<APIResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} else {
			LOGGER.warn(
					"ObjectMapper or HttpServletRequest not configured in default TradeApi interface so no example is generated");
		}
		return new ResponseEntity<APIResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Get all available Pricing amount configurations
	 * @return {@link List< PricingAmount >}
	 */
	@GetMapping(produces = "application/json", consumes = "application/json")
	default ResponseEntity<APIResponse> getAllPricingAmounts() {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {
				try {
					return new ResponseEntity<APIResponse>(HttpStatus.NOT_IMPLEMENTED);
				} catch (Exception e) {
					LOGGER.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<APIResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} else {
			LOGGER.warn(
					"ObjectMapper or HttpServletRequest not configured in default TradeApi interface so no example is generated");
		}
		return new ResponseEntity<APIResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

	/**
	 * Get Pricing Amount configuration.
	 * @param pricingAmountId
	 * @return {@link PricingAmount}
	 */
	@GetMapping(value = "/{pricing-amount-id}", produces = "application/json", consumes = "application/json")
	default ResponseEntity<APIResponse> getPricingAmount(@Valid @PathVariable("pricing-amount-id") String pricingAmountId) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {
				try {
					return new ResponseEntity<APIResponse>(HttpStatus.NOT_IMPLEMENTED);
				} catch (Exception e) {
					LOGGER.error("Couldn't serialize response for content type application/json", e);
					return new ResponseEntity<APIResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		} else {
			LOGGER.warn(
					"ObjectMapper or HttpServletRequest not configured in default TradeApi interface so no example is generated");
		}
		return new ResponseEntity<APIResponse>(HttpStatus.NOT_IMPLEMENTED);
	}

}
