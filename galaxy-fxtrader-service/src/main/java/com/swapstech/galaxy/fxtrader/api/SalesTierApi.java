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
 * The Interface SalesTierApi.
 */
@RestController
@Tag(name = "SalesPricingAPI-controller", description = "The Sales Pricing API")
public interface SalesTierApi {

    /** The logger. */
    Logger LOGGER = LoggerFactory.getLogger(SalesTierApi.class);

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
     * Create a new Sales tier configuration.
     * @param body {@link PricingTier}
     * @return PricingTier
     */
    @PostMapping(value = "/salestier", produces = "application/json")
    default ResponseEntity<APIResponse> createSalesTier(@Valid @RequestBody PricingTier body) {
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
     * Updates the Sales tier configuration
     * @param body {@link PricingTier}
     * @param tierId
     * @return {@link PricingTier}
     */
    @PutMapping(value = "/salestier/{tier-id}", produces = "application/json")
    default ResponseEntity<APIResponse> updateSalesTier(@Valid @RequestBody PricingTier body, @Valid @PathVariable("tier-id") String tierId) {
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
     * @param tierId
     * @return {@link PricingTier}
     */
    @DeleteMapping(value = "/salestier/{tier-id}", produces = "application/json")
    default ResponseEntity<APIResponse> deleteSalesTier(@Valid @PathVariable("tier-id") String tierId) {
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
     * Get all available Sales tier configurations
     * @return {@link List <PricingTier>}
     */
    @GetMapping(value = "/salestier", produces = "application/json")
    default APIResponse getAllSalesTiers(@Valid @RequestParam("isParent") Boolean isParent) {
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

    /**
     * Get Pricing Amount configuration.
     * @param pricingAmountId
     * @return {@link PricingTier}
     */
    @GetMapping(value = "/salestier/{tier-id}", produces = "application/json")
    default ResponseEntity<PricingTier> getSalesTier(@Valid @PathVariable("tier-id") String pricingAmountId) {
        if (getObjectMapper().isPresent() && getAcceptHeader().isPresent()) {
            if (getAcceptHeader().get().contains("application/json")) {
                try {
                    return new ResponseEntity<PricingTier>(HttpStatus.NOT_IMPLEMENTED);
                } catch (Exception e) {
                    LOGGER.error("Couldn't serialize response for content type application/json", e);
                    return new ResponseEntity<PricingTier>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            LOGGER.warn(
                    "ObjectMapper or HttpServletRequest not configured in default TradeApi interface so no example is generated");
        }
        return new ResponseEntity<PricingTier>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping(value = "/salestier/default/{name}", produces = "application/json")
    default ResponseEntity<APIResponse> getDefaultSalesTierByName(@Valid @PathVariable("name") String tierName) {
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

    @GetMapping(value = "/salestier/{name}", produces = "application/json")
    default ResponseEntity<APIResponse> getSalesTierByName(@Valid @PathVariable("name") String tierName) {
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
