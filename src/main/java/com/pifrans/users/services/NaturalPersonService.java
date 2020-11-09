package com.pifrans.users.services;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.pifrans.users.models.NaturalPerson;
import com.pifrans.users.repositories.NaturalPersonRepository;

@Service
public class NaturalPersonService implements Serializable{
	private static final long serialVersionUID = 1L;

	@Autowired
	private NaturalPersonRepository naturalPersonRepository;
	
	/*
	 * @Bean public void save() { NaturalPerson person = new NaturalPerson();
	 * person.setNickname("Tibío"); person.setEmail("pipiluco@gmail.com");
	 * person.setPassword("111"); person.setActive(true);
	 * person.setName("Pipiluço"); person.setLastName("Capetei");
	 * person.setGender("Macho"); person.setCpf("134.317.150-44");
	 * naturalPersonRepository.save(person); }
	 */
	
	/*
	 * @Bean public void find() { Optional<NaturalPerson> person =
	 * naturalPersonRepository.findById(1L);
	 * System.out.println(person.get().getId());
	 * System.out.println(person.get().getNickname());
	 * System.out.println(person.get().getEmail());
	 * System.out.println(person.get().isActive());
	 * System.out.println(person.get().getPassword());
	 * System.out.println(person.get().getName());
	 * System.out.println(person.get().getLastName());
	 * System.out.println(person.get().getGender());
	 * System.out.println(person.get().getCpf()); }
	 */
}
