package com.pifrans.modules.places.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.places.models.Country;

@RestController
@RequestMapping(value = "/countries")
public class CountryResource extends GenericResource<Country> {
	//private static final Logger LOG = Logger.getLogger(CountryResource.class.getName());

}
