package com.pifrans.modules.place.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.place.models.Country;

@RestController
@RequestMapping(value = "/place/countries")
public class CountryResource extends GenericResource<Country> {
	//private static final Logger LOG = Logger.getLogger(CountryResource.class.getName());

}
