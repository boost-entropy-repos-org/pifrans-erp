package com.pifrans.modules.ecommerce.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.ecommerce.models.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource extends GenericResource<Category> {
	// private static final Logger LOG =
	// Logger.getLogger(CityResource.class.getName());

}
