package com.pifrans.modules.users.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pifrans.modules.users.models.Business;
import com.pifrans.modules.users.services.BusinessService;

@RestController
@RequestMapping(value = "/business")
public class BusinessResource {

	@Autowired
	private BusinessService businessService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Business> find(@PathVariable Long id) {
		Business object = businessService.find(id);
		return ResponseEntity.ok().body(object);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Business object) {
		object = businessService.save(object);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Business object, @PathVariable Long id) {
		object.setId(id);
		object = businessService.update(object);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		businessService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Business>> findAll() {
		List<Business> list = businessService.findAll();
		return ResponseEntity.ok().body(list);
	}
}
