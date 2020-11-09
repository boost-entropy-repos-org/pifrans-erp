package com.pifrans.users.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "legal_entity")
public class LegalEntity extends User {
	private static final long serialVersionUID = 1L;

	private String officialName;
	private String fancyName;
	private String cnpj;
	private Date creationDate;

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
