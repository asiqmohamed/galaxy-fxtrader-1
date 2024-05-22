package com.swapstech.galaxy.fxtrader.service;

import com.swapstech.galaxy.fxtrader.model.PricingTierItem;
import com.swapstech.galaxy.fxtrader.model.PricingTier;
import com.swapstech.galaxy.fxtrader.model.TierType;
import jakarta.persistence.criteria.*;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class PricingSpecification {

	public static Specification<PricingTier> getPricingTiers(List<TierType> tierTypes, String tierName) {
		return new Specification<PricingTier>() {

			public Predicate toPredicate(Root<PricingTier> pricingTier, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				Join<PricingTier, PricingTierItem> pricingItem = pricingTier.join("pricingItem");
				if (CollectionUtils.isNotEmpty(tierTypes)) {
					predicate = cb.and(cb.in(pricingItem.get("tierType")).value(tierTypes), predicate);
				}
				if (StringUtils.isNotEmpty(tierName)) {
					predicate = cb.and(cb.equal((pricingTier.get("name")), tierName), predicate);
				}
				return predicate;
			}
		};
	}

}