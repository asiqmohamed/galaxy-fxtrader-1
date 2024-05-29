package com.swapstech.galaxy.fxtrader.service;

import java.util.List;

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

	public static Specification<PricingTier> getPricingTiers(List<Integer> tierTypes, String tierName) {
		return new Specification<PricingTier>() {

			public Predicate toPredicate(Root<PricingTier> pricingTier, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
//				Join<PricingTier, PricingTierItem> pricingItem = pricingTier.join("pricingItem");
				if (CollectionUtils.isNotEmpty(tierTypes)) {
					predicate = cb.and(cb.in(pricingTier.get("tierType")).value(tierTypes), predicate);
				}
				if (StringUtils.isNotEmpty(tierName)) {
					predicate = cb.and(cb.equal((pricingTier.get("name")), tierName), predicate);
				}
				return predicate;
			}
		};
	}

}