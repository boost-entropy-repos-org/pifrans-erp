package com.pifrans.modules.finance.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.finance.models.Movement;

@RestController
@RequestMapping(value = "/movements")
public class MovementResource extends GenericResource<Movement> {
	//private static final Logger LOG = Logger.getLogger(CityResource.class.getName());

}
