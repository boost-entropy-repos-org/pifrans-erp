package com.pifrans.modules.place.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.place.models.State;

@RestController
@RequestMapping(value = "/place/states")
public class StateResource extends GenericResource<State> {
	//private static final Logger LOG = Logger.getLogger(CityResource.class.getName());

}
