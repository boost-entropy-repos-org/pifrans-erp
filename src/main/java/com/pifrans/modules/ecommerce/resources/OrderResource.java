package com.pifrans.modules.ecommerce.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.ecommerce.models.Order;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource extends GenericResource<Order> {
	// private static final Logger LOG =
	// Logger.getLogger(CityResource.class.getName());

}
