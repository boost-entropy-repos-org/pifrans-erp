package com.pifrans.modules.finance.resources;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pifrans.global.resources.GenericResource;
import com.pifrans.modules.finance.models.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource extends GenericResource<Category> {
	// private static final Logger LOG =
	// Logger.getLogger(CityResource.class.getName());

	@Override
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Category object) {
		return super.save(object);
	}
}
