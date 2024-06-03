package com.swapstech.galaxy.fxtrader.api;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapstech.galaxy.common.api.model.APIResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

/**
 * The Interface PricingAmountApi.
 */
@RestController
@Tag(name = "CommonTraderAPI-controller", description = "Common Trader API")
public interface CommonTraderApi {

	/** The logger. */
	Logger LOGGER = LoggerFactory.getLogger(CommonTraderApi.class);

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
	 * Updates the Tier Status
	 * @param tierType, tierId
	 * @return {@link Tier Status}
	 */
	@PutMapping(value = "/enable", produces = "application/json")
	default APIResponse enableTier(@Valid @RequestParam("tierType") String tierType,
			@Valid @RequestParam("tierId") String tierId) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {
				try {
					return new APIResponse(HttpStatus.NOT_IMPLEMENTED.name(), HttpStatus.NOT_IMPLEMENTED.value(), null);
				} catch (Exception e) {
					LOGGER.error("Couldn't serialize response for content type application/json", e);
					return new APIResponse(HttpStatus.EXPECTATION_FAILED.name(), HttpStatus.EXPECTATION_FAILED.value(),
							e.getMessage());
				}
			}
		} else {
			LOGGER.warn(
					"ObjectMapper or HttpServletRequest not configured in default TradeApi interface so no example is generated");
		}
		return new APIResponse(HttpStatus.NOT_IMPLEMENTED.name(), HttpStatus.NOT_IMPLEMENTED.value(), null);
	}
	
	
	/**
	 * Updates the Tier Status
	 * @param tierType, tierId
	 * @return {@link Tier Status}
	 */
	@PutMapping(value = "/disable", produces = "application/json")
	default APIResponse disableTier(@Valid @RequestParam("tierType") String tierType,
			@Valid @RequestParam("tierId") String tierId) {
		if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
			if (getAcceptHeader().get().contains("application/json")) {
				try {
					return new APIResponse(HttpStatus.NOT_IMPLEMENTED.name(), HttpStatus.NOT_IMPLEMENTED.value(), null);
				} catch (Exception e) {
					LOGGER.error("Couldn't serialize response for content type application/json", e);
					return new APIResponse(HttpStatus.EXPECTATION_FAILED.name(), HttpStatus.EXPECTATION_FAILED.value(),
							e.getMessage());
				}
			}
		} else {
			LOGGER.warn(
					"ObjectMapper or HttpServletRequest not configured in default TradeApi interface so no example is generated");
		}
		return new APIResponse(HttpStatus.NOT_IMPLEMENTED.name(), HttpStatus.NOT_IMPLEMENTED.value(), null);
	}

}
