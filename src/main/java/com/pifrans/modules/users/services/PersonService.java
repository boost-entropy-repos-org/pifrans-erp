package com.pifrans.modules.users.services;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pifrans.global.exceptions.errors.DataIntegrityException;
import com.pifrans.modules.users.models.Person;
import com.pifrans.modules.users.repositories.PersonRepository;

@Service
public class PersonService implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(PersonService.class.getName());

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Person find(Long id) {
		LOG.info("find()");
		Optional<Person> object = null;
		try {
			object = personRepository.findById(id);
			return object.get();
		} catch (NoSuchElementException e) {
			LOG.warning("NoSuchElementException(): " + e.getMessage());
			return object.orElseThrow(() -> new NoSuchElementException(
					"Objeto não encontrado! ID: " + id + ", Tipo: " + Person.class.getName()));
		}
	}

	public Person save(Person object) {
		LOG.info("save()");
		try {
			object.setId(null);
			object.setPassword(passwordEncoder.encode(object.getPassword()));
			return personRepository.save(object);
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new DataIntegrityException("Falha ao salvar objeto! " + e.getRootCause());
		}
	}

	public Person update(Person object) {
		LOG.info("update()");
		try {
			find(object.getId());
			return personRepository.save(object);
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new DataIntegrityException("Falha ao atualizar objeto! " + e.getRootCause());
		}
	}

	public void delete(Long id) {
		LOG.info("delete()");
		find(id);
		try {
			personRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new DataIntegrityException("Falha ao excluir objeto! " + e.getRootCause());
		} catch (Exception e) {
			LOG.severe("Exception(): " + e.getCause());
		}
	}

	public List<Person> findAll() {
		LOG.info("findAll()");
		try {
			return personRepository.findAll();
		} catch (Exception e) {
			LOG.severe("Exception(): " + e.getCause());
			return null;
		}
	}
}
