package com.pifrans.modules.finances.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.finances.models.Currency;

@RestController
@RequestMapping(value = "/currencies")
public class CurrencyResource extends GenericResource<Currency> {
	//private static final Logger LOG = Logger.getLogger(CityResource.class.getName());

}
