package com.pifrans.modules.user.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pifrans.global.models.User;

@Entity
@Table(name = "person")
public class Person extends User {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Campo obrigatório!")
	private String name;
	
	@NotEmpty(message = "Campo obrigatório!")
	private String lastName;
	private String gender;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date dateOfBirth;

	@CPF(message = "CPF inválido!")
	@NotEmpty(message = "Campo obrigatório!")
	@Column(unique = true)
	private String cpf;

	public Person() {
	}

	public Person(Long id, String nickname, String email, String password, Date currentAccess, Date lastAccess,
			String name, String lastName, String gender, Date dateOfBirth, String cpf) {
		super(id, nickname, email, password, currentAccess, lastAccess);
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
