package com.pifrans.modules.finance.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.finance.models.Currency;

@RestController
@RequestMapping(value = "/finance/currencies")
public class CurrencyResource extends GenericResource<Currency> {
	//private static final Logger LOG = Logger.getLogger(CityResource.class.getName());

}
