package com.pifrans.global.resources;

import java.lang.reflect.Method;
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
import com.pifrans.global.services.GenericService;

@RestController
public abstract class GenericResource<T> {
	private static final Logger LOG = Logger.getLogger(GenericResource.class.getName());

	@Autowired
	private GenericService<T> service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<T> find(@PathVariable Long id) {
		LOG.info("find");
		T object = null;
		try {
			object = service.find(id);
			return ResponseEntity.ok().body(object);
		} catch (NoSuchElementException e) {
			throw new ErrorNoSuchElementException(
					("Objeto não encontrado! ID: " + id + ", Tipo: " + object.getClass().getName()).toString());
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException(e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody T object) {
		LOG.info("save");
		try {
			object = service.save(object);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(object).toUri();
			return ResponseEntity.created(uri).build();
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new ErrorDataIntegrityException("Falha ao salvar objeto! " + e.getRootCause());
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException(e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody T object) {
		LOG.info("update");
		try {
			Method method = object.getClass().getSuperclass().getMethod("getId");
			Long id = (Long) method.invoke(object);
			object = service.update(object, id);
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
			service.delete(id);
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
	public ResponseEntity<List<T>> findAll() {
		LOG.info("findAll");
		List<T> list;
		try {
			list = service.findAll();
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException(e.getMessage());
		}
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<T>> findByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		LOG.info("findByPage");
		Page<T> list;
		try {
			list = service.findByPage(page, linesPerPage, orderBy, direction);
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException("Erro: " + e.getMessage());
		}
	}
}
