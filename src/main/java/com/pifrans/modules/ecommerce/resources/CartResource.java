package com.pifrans.modules.ecommerce.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.ecommerce.models.Cart;

@RestController
@RequestMapping(value = "/ecommerce/carts")
public class CartResource extends GenericResource<Cart> {
	// private static final Logger LOG =
	// Logger.getLogger(CityResource.class.getName());

}
