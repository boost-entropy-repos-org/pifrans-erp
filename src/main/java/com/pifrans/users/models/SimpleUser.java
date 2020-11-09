package com.pifrans.users.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "simple_user")
public class SimpleUser extends User{
	private static final long serialVersionUID = 1L;

}
