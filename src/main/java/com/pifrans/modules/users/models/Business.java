package com.pifrans.modules.users.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pifrans.global.models.User;

@Entity
@Table(name = "business")
public class Business extends User {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Campo obrigatório!")
	private String officialName;
	private String fancyName;

	@CNPJ(message = "CNPJ inválido!")
	@NotEmpty(message = "Campo obrigatório!")
	private String cnpj;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date creationDate;

	public Business() {
	}

	public Business(Long id, String nickname, String email, String password, Date currentAccess, Date lastAccess,
			String officialName, String fancyName, String cnpj, Date creationDate) {
		super(id, nickname, email, password, currentAccess, lastAccess);
		this.officialName = officialName;
		this.fancyName = fancyName;
		this.cnpj = cnpj;
		this.creationDate = creationDate;
	}

	public String getOfficialName() {
		return officialName;
	}

	public void setOfficialName(String officialName) {
		this.officialName = officialName;
	}

	public String getFancyName() {
		return fancyName;
	}

	public void setFancyName(String fancyName) {
		this.fancyName = fancyName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
