package com.pifrans.modules.user.resources;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pifrans.global.exceptions.errors.ErrorDataIntegrityException;
import com.pifrans.global.exceptions.errors.ErrorException;
import com.pifrans.global.exceptions.errors.ErrorNoSuchElementException;
import com.pifrans.modules.user.models.Person;
import com.pifrans.modules.user.services.PersonService;

@RestController
@RequestMapping(value = "/user/people")
public class PersonResource {
	private static final Logger LOG = Logger.getLogger(PersonResource.class.getName());

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> find(@PathVariable Long id) {
		LOG.info("find");
		Person object;
		try {
			object = personService.find(id);
			return ResponseEntity.ok().body(object);
		} catch (NoSuchElementException e) {
			throw new ErrorNoSuchElementException(
					("Objeto não encontrado! ID: " + id + ", Tipo: " + Person.class.getName()).toString());
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException(e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody Person object) {
		LOG.info("save");
		try {
			object = personService.save(object);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object.getId())
					.toUri();
			return ResponseEntity.created(uri).build();
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new ErrorDataIntegrityException("Falha ao salvar objeto! " + e.getRootCause());
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException(e.getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Person object, @PathVariable Long id) {
		LOG.info("update");
		try {
			object.setId(id);
			object = personService.update(object, object.getId());
			return ResponseEntity.noContent().build();
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new ErrorDataIntegrityException("Falha ao atualizar objeto! " + e.getRootCause());
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException(e.getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		LOG.info("delete");
		try {
			personService.delete(id);
			return ResponseEntity.noContent().build();
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new ErrorDataIntegrityException("Falha ao excluir objeto! " + e.getRootCause());
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException(e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Person>> findAll() {
		LOG.info("findAll");
		List<Person> list;
		try {
			list = personService.findAll();
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException(e.getMessage());
		}
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<Person>> findByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		LOG.info("findByPage");
		Page<Person> list;
		try {
			list = personService.findByPage(page, linesPerPage, orderBy, direction);
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException("Erro: " + e.getMessage());
		}
	}
}
