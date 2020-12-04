package com.pifrans.modules.ecommerce.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.ecommerce.models.PaymentCard;

@RestController
@RequestMapping(value = "/ecommerce/cards")
public class PaymentCardResource extends GenericResource<PaymentCard> {
	// private static final Logger LOG =
	// Logger.getLogger(CityResource.class.getName());

}
