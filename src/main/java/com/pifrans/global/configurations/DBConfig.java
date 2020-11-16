package com.pifrans.global.configurations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pifrans.global.enums.Profile;
import com.pifrans.global.repositories.UserRepository;
import com.pifrans.modules.places.models.Address;
import com.pifrans.modules.places.models.City;
import com.pifrans.modules.places.models.Country;
import com.pifrans.modules.places.models.State;
import com.pifrans.modules.places.repositories.AddressRepository;
import com.pifrans.modules.places.repositories.CityRepository;
import com.pifrans.modules.places.repositories.CountryRepository;
import com.pifrans.modules.places.repositories.StateRepository;
import com.pifrans.modules.users.models.Business;
import com.pifrans.modules.users.models.Person;

@Configuration
public class DBConfig {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instantiateTestDatabase() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Person np1 = new Person(null, "Tibío", "pipiluco@gmail.com", encoder.encode("111"), null, null,
				"Pipiluço", "Capetei", "Masculino", dateFormat.parse("25-05-2010"), "02283279038");
		Person np2 = new Person(null, "Tuba", "tubaína@gmail.com", encoder.encode("111"), null, null,
				"Tubaína", "Capetei", "Feminino", dateFormat.parse("15-12-2013"), "70506913058");
		np2.addProfile(Profile.ADMIN);

		Business le1 = new Business(null, null, "senai@sc.senai.br", encoder.encode("@dmBnu"), null, null,
				"Serviço Nacional de Aprendizagem Industrial", "SENAI Indaial", "03774688003170",
				dateFormat.parse("18-02-1935"));
		Person np3 = new Person(null, null, "sakukay@gmail.com", encoder.encode("222"), null, null,
				"Sakukay", "Doroki", "Masculino", dateFormat.parse("03-07-1980"), "49532382062");

		Country co1 = new Country(null, "Brasil", "BR");
		Country co2 = new Country(null, "Japão", "JP");
		countryRepository.saveAll(Arrays.asList(co1, co2));

		State st1 = new State(null, "Santa Catarina", "SC", co1);
		State st2 = new State(null, "Paraíba", "PB", co1);
		State st3 = new State(null, "Tóquio", "TQ", co2);
		stateRepository.saveAll(Arrays.asList(st1, st2, st3));

		City ci1 = new City(null, "Indaial", "IDL", st1);
		City ci2 = new City(null, "Tóquio", "TQ", st3);
		City ci3 = new City(null, "João Pessoa", "JPA", st2);
		cityRepository.saveAll(Arrays.asList(ci1, ci2, ci3));

		Address ad1 = new Address(null, "89081122", "Bezerra Menezes", 34, "Warnow", ci1);
		Address ad2 = new Address(null, "58123451", "Rua Florestal", 288, "Bairro das Industrías", ci3);
		Address ad3 = new Address(null, "1430012", "Mihara ST", 2342, "Ota", ci2);
		addressRepository.saveAll(Arrays.asList(ad1, ad2, ad3));

		np1.setAddresses(Arrays.asList(ad1, ad2));
		np2.setAddresses(Arrays.asList(ad1));
		np3.setAddresses(Arrays.asList(ad3));
		userRepository.saveAll(Arrays.asList(np1, np2, le1, np3));
	}
}
