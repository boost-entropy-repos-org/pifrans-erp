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
import com.pifrans.modules.users.models.Business;
import com.pifrans.modules.users.repositories.BusinessRepository;

@Service
public class BusinessService implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(BusinessService.class.getName());

	@Autowired
	private BusinessRepository businessRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Business find(Long id) {
		LOG.info("find()");
		Optional<Business> object = null;
		try {
			object = businessRepository.findById(id);
			return object.get();
		} catch (NoSuchElementException e) {
			LOG.warning("NoSuchElementException(): " + e.getMessage());
			return object.orElseThrow(() -> new NoSuchElementException(
					"Objeto não encontrado! ID: " + id + ", Tipo: " + Business.class.getName()));
		}
	}

	public Business save(Business object) {
		LOG.info("save()");
		try {
			object.setId(null);
			object.setPassword(passwordEncoder.encode(object.getPassword()));
			return businessRepository.save(object);
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new DataIntegrityException("Falha ao salvar objeto! " + e.getRootCause());
		}
	}

	public Business update(Business object) {
		LOG.info("update()");
		try {
			find(object.getId());
			return businessRepository.save(object);
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new DataIntegrityException("Falha ao atualizar objeto! " + e.getRootCause());
		}
	}

	public void delete(Long id) {
		LOG.info("delete()");
		find(id);
		try {
			businessRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new DataIntegrityException("Falha ao excluir objeto! " + e.getRootCause());
		} catch (Exception e) {
			LOG.severe("Exception(): " + e.getCause());
		}
	}

	public List<Business> findAll() {
		LOG.info("findAll()");
		try {
			return businessRepository.findAll();
		} catch (Exception e) {
			LOG.severe("Exception(): " + e.getCause());
			return null;
		}
	}
}
