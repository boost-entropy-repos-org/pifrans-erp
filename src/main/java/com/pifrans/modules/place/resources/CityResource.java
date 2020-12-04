package com.pifrans.modules.place.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.place.models.City;

@RestController
@RequestMapping(value = "/place/cities")
public class CityResource extends GenericResource<City> {
	//private static final Logger LOG = Logger.getLogger(CityResource.class.getName());

}
