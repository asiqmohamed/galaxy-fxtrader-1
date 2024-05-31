package com.swapstech.galaxy.fxtrader.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapstech.galaxy.common.api.model.APIResponse;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTier;
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
 * The Interface TradingTierApi.
 */
@RestController
@Tag(name = "TradingPricingAPI-controller", description = "The Trading Pricing API")
public interface TradingTierApi {

    /** The logger. */
    Logger LOGGER = LoggerFactory.getLogger(TradingTierApi.class);

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
     * Create a new Trading tier configuration.
     * @param body {@link PricingTier}
     * @return PricingTier
     */
    @PostMapping(value = "/tradingtier", produces = "application/json")
    default ResponseEntity<APIResponse> createTradingTier(@Valid @RequestBody PricingTier body) {
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
     * Updates the Trading tier configuration
     * @param body {@link PricingTier}
     * @param tierId
     * @return {@link PricingTier}
     */
    @PutMapping(value = "/tradingtier/{tier-id}", produces = "application/json")
    default ResponseEntity<APIResponse> updateTradingTier(@Valid @RequestBody PricingTier body, @Valid @PathVariable("tier-id") String tierId) {
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
     * Delete the Trading tier configuration.
     * @param tierId
     * @return {@link PricingTier}
     */
    @DeleteMapping(value ="/tradingtier", produces = "application/json")
    default ResponseEntity<APIResponse> deleteTradingTier(@Valid @RequestParam("tier-id") String tierId, @Valid @RequestParam("tier-item-id") String tierItemId) {
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
     * Get all available Trading tier configurations
     * @return {@link List <PricingTier>}
     */
    @GetMapping(value = "/tradingtier", produces = "application/json")
    default APIResponse getAllTradingTiers(@Valid @RequestParam("isParent") Boolean isParent) {
        if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new APIResponse(HttpStatus.NOT_IMPLEMENTED.name(), HttpStatus.NOT_IMPLEMENTED.value(), null);
                } catch (Exception e) {
                    LOGGER.error("Couldn't serialize response for content type application/json", e);
                    return new APIResponse(HttpStatus.EXPECTATION_FAILED.name(), HttpStatus.EXPECTATION_FAILED.value(), e.getMessage());
                }
            }
        } else {
            LOGGER.warn(
                    "ObjectMapper or HttpServletRequest not configured in default TradeApi interface so no example is generated");
        }
        return new APIResponse(HttpStatus.NOT_IMPLEMENTED.name(), HttpStatus.NOT_IMPLEMENTED.value(), null);
    }
    
    @GetMapping(value = "/tradingtier/{id}", produces = "application/json")
    default ResponseEntity<APIResponse> getTradingTierById(@Valid @PathVariable("id") String tierId) {
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
