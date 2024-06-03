package com.swapstech.galaxy.fxtrader.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.swapstech.galaxy.fxtrader.model.PricingTier;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class PricingSpecification {

	public static Specification<PricingTier> getPricingTier(List<Integer> tierTypes, String id) {
		return new Specification<PricingTier>() {

			public Predicate toPredicate(Root<PricingTier> pricingTier, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (CollectionUtils.isNotEmpty(tierTypes)) {
					predicate = cb.and(cb.in(pricingTier.get("tierType")).value(tierTypes), predicate);
				}
				if (StringUtils.isNotEmpty(id)) {
					predicate = cb.and(cb.equal((pricingTier.get("id")), UUID.fromString(id)), predicate);
				}
				return predicate;
			}
		};
	}

}