package com.pifrans.modules.ecommerce.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.ecommerce.models.PaymentSlip;

@RestController
@RequestMapping(value = "/ecommerce/slips")
public class PaymentSlipResource extends GenericResource<PaymentSlip> {
	// private static final Logger LOG =
	// Logger.getLogger(CityResource.class.getName());

}
