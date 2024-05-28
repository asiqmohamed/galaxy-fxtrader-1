package com.swapstech.galaxy.fxtrader.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapstech.galaxy.common.api.model.APIResponse;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingAmount;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
	@GetMapping(value = "/enable-disable", produces = "application/json")
	default APIResponse updateTierStatus(@Valid @RequestParam("tierType") String tierType,
			@Valid @RequestParam("tierId") String tierId, @Valid @RequestParam("status") Boolean status) {
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
