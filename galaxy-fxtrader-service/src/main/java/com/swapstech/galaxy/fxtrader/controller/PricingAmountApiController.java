package com.swapstech.galaxy.fxtrader.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapstech.galaxy.fxtrader.api.PricingAmountApi;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@Tag(name = "PricingAmountAPI-controller", description = "The Pricing Amount API")
public class PricingAmountApiController implements PricingAmountApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(PricingAmountApiController.class);

	@Autowired
	ObjectMapper objectMapper;

	private final HttpServletRequest request;

	@Autowired
	public PricingAmountApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	@Override
	public Optional<HttpServletRequest> getRequest() {
		return Optional.ofNullable(request);
	}


}
