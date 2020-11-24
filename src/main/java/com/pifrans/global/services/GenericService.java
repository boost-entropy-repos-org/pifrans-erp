package com.pifrans.global.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericService<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(GenericService.class.getName());

	@Autowired
	private JpaRepository<T, Long> repository;

	public T find(Long id) throws Exception {
		LOG.info("find()");
		Optional<T> object = repository.findById(id);
		return object.get();
	}

	public T save(T object) throws Exception {
		LOG.info("save()");
		return repository.save(object);
	}

	public List<T> saveAll(List<T> list) {
		LOG.info("saveAll()");
		return repository.saveAll(list);
	}

	public T update(T object, Long id) throws Exception {
		LOG.info("update()");
		find(id);
		return repository.save(object);
	}

	public void delete(Long id) throws Exception {
		LOG.info("delete()");
		find(id);
		repository.deleteById(id);
	}

	public List<T> findAll() throws Exception {
		LOG.info("findAll()");
		return repository.findAll();
	}

	public Page<T> findByPage(Integer page, Integer linesPerPage, String orderBy, String direction) throws Exception {
		LOG.info("findPage()");
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
}
