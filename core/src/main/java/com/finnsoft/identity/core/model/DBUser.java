/**
 * 
 */
package com.finnsoft.identity.core.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * @author faustodelatog
 * 
 */
@Entity
@DiscriminatorValue("B")
public class DBUser extends User {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	@NotNull(message = "El password para el usuario es requerido")
	@Basic(optional = false)
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getType() {
		return "BDD";
	}
}
