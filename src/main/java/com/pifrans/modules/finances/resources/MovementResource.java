package com.pifrans.modules.finances.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.finances.models.Movement;

@RestController
@RequestMapping(value = "/movements")
public class MovementResource extends GenericResource<Movement> {
	//private static final Logger LOG = Logger.getLogger(CityResource.class.getName());

}
