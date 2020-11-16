package com.pifrans.global.services;

import java.io.Serializable;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pifrans.global.exceptions.errors.DataIntegrityException;
import com.pifrans.global.models.User;
import com.pifrans.global.repositories.UserRepository;
import com.pifrans.global.securities.UserDetailSecurity;

@Service
public class UserService implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UserService.class.getName());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public static UserDetailSecurity authenticated() {
		try {
			return (UserDetailSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User find(Long id) {
		LOG.info("find()");
		Optional<User> object = null;
		try {
			object = userRepository.findById(id);
			return object.get();
		} catch (NoSuchElementException e) {
			LOG.warning("NoSuchElementException(): " + e.getMessage());
			return object.orElseThrow(() -> new NoSuchElementException(
					"Objeto não encontrado! ID: " + id + ", Tipo: " + User.class.getName()));
		}
	}

	public User save(User object) {
		LOG.info("save()");
		try {
			object.setId(null);
			object.setPassword(passwordEncoder.encode(object.getPassword()));
			return userRepository.save(object);
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new DataIntegrityException("Falha ao salvar objeto! " + e.getRootCause());
		}
	}

	public User update(User object) {
		LOG.info("update()");
		try {
			find(object.getId());
			return userRepository.save(object);
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new DataIntegrityException("Falha ao atualizar objeto! " + e.getRootCause());
		}
	}

	public void delete(Long id) {
		LOG.info("delete()");
		find(id);
		try {
			userRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			LOG.warning("DataIntegrityViolationException(): " + e.getRootCause());
			throw new DataIntegrityException("Falha ao excluir objeto! " + e.getRootCause());
		} catch (Exception e) {
			LOG.severe("Exception(): " + e.getCause());
		}
	}

	public List<User> findAll() {
		LOG.info("findAll()");
		try {
			return userRepository.findAll();
		} catch (Exception e) {
			LOG.severe("Exception(): " + e.getCause());
			return null;
		}
	}
}
