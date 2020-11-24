package com.pifrans.global.resources;

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
import com.pifrans.global.models.User;
import com.pifrans.global.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	private static final Logger LOG = Logger.getLogger(UserResource.class.getName());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> find(@PathVariable Long id) {
		LOG.info("find");
		User object;
		try {
			object = userService.find(id);
			return ResponseEntity.ok().body(object);
		} catch (NoSuchElementException e) {
			throw new ErrorNoSuchElementException(
					("Objeto não encontrado! ID: " + id + ", Tipo: " + User.class.getName()).toString());
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException(e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody User object) {
		LOG.info("save");
		try {
			object = userService.save(object);
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
	public ResponseEntity<Void> update(@Valid @RequestBody User object, @PathVariable Long id) {
		LOG.info("update");
		try {
			object.setId(id);
			object = userService.update(object, object.getId());
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
			userService.delete(id);
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
	public ResponseEntity<List<User>> findAll() {
		LOG.info("findAll");
		List<User> list;
		try {
			list = userService.findAll();
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException(e.getMessage());
		}
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<User>> findByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		LOG.info("findByPage");
		Page<User> list;
		try {
			list = userService.findByPage(page, linesPerPage, orderBy, direction);
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			LOG.severe(e.getMessage());
			throw new ErrorException("Erro: " + e.getMessage());
		}
	}
}
