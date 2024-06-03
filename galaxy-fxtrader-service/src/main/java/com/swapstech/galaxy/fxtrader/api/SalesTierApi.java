package com.swapstech.galaxy.fxtrader.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapstech.galaxy.common.api.model.APIResponse;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTier;
import com.swapstech.galxy.fxtrader.client.pricing.model.PricingTierItem;
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
@RequestMapping("/salestier")
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
    @PostMapping(produces = "application/json", consumes = "application/json")
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
     * Create a new Sales tier item configuration.
     * @param body {@link PricingTierItem}
     * @return PricingTierItem
     */
    @PostMapping(value = "/salestieritem/{tier-id}", produces = "application/json")
    default ResponseEntity<APIResponse> createSalesTierItem(@Valid @RequestBody PricingTierItem body, @Valid @PathVariable("tier-id") String tierId) {
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
     * Updates the Sales tier item configuration
     * @param body {@link PricingTierItem}
     * @param tierId
     * @return {@link PricingTierItem}
     */
    @PutMapping(value = "/salestieritem/{tier-id}", produces = "application/json")
    default ResponseEntity<APIResponse> updateSalesTierItem(@Valid @RequestBody PricingTierItem body, @Valid @PathVariable("tier-id") String tierId) {
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
    @DeleteMapping(value = "/{id}", produces = "application/json")
    default ResponseEntity<APIResponse> deleteSalesTier(@Valid @PathVariable("id") String tierId, @Valid @RequestParam("tier-item-id") String tierItemId) {
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
    @GetMapping(produces = "application/json")
    default APIResponse getAllSalesTiers(@Valid @RequestParam("is-parent") Boolean isParent) {
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

    @GetMapping(value = "/{id}", produces = "application/json")
    default ResponseEntity<APIResponse> getSalesTierById(@Valid @PathVariable("id") String id) {
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
    
    @GetMapping(value = "/name/{name}", produces = "application/json")
    default ResponseEntity<APIResponse> getSalesTierByName(@Valid @PathVariable("name") String name) {
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
