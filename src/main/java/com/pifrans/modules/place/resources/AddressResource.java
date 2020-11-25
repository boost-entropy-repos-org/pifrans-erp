package com.pifrans.modules.place.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.place.models.Address;

@RestController
@RequestMapping(value = "/addresses")
public class AddressResource extends GenericResource<Address> {
	//private static final Logger LOG = Logger.getLogger(CityResource.class.getName());

}
