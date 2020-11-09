package com.pifrans.finances.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.pifrans.exceptions.errors.DataIntegrityException;
import com.pifrans.exceptions.errors.ObjectNotFoundException;
import com.pifrans.places.models.Country;
import com.pifrans.places.repositories.CountryRepository;

@Service
public class CountryService implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CountryService.class.getName());

	@Autowired
	private CountryRepository countryRepository;

	public Country find(Long id) {
		LOG.info("find()");
		try {
			Optional<Country> object = countryRepository.findById(id);
			return object.get();
		} catch (ObjectNotFoundException e) {
			LOG.warning("ObjectNotFoundException(): " + e.getCause());
			throw new ObjectNotFoundException(
					"Objeto não encontrado! ID: " + id + ", Tipo: " + Country.class.getName());
		}
	}

	public Country save(Country object) {
		LOG.info("save()");
		try {
			object.setId(null);
			return countryRepository.save(object);
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new DataIntegrityException("Falha ao salvar objeto! " + e.getRootCause());
		}
	}

	public Country update(Country object) {
		LOG.info("update()");
		try {
			find(object.getId());
			return countryRepository.save(object);
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new DataIntegrityException("Falha ao atualizar objeto! " + e.getRootCause());
		}
	}

	public void delete(Long id) {
		LOG.info("delete()");
		find(id);
		try {
			countryRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new DataIntegrityException("Falha ao excluir objeto! " + e.getRootCause());
		} catch (Exception e) {
			LOG.severe("Exception(): " + e.getCause());
		}
	}

	public List<Country> findAll() {
		LOG.info("findAll()");
		try {
			return countryRepository.findAll();
		} catch (Exception e) {
			LOG.severe("Exception(): " + e.getCause());
			return null;
		}
	}
}
