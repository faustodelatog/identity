/**
 * 
 */
package com.finnsoft.identity.core.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author faustodelatog
 * 
 */
@Entity
public class Grant implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "role_id")
	private Role role;

	@ManyToOne(optional = false)
	@JoinColumn(name = "role_id")
	private UserOrganization userOrganization;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserOrganization getUserOrganization() {
		return userOrganization;
	}

	public void setUserOrganization(UserOrganization userOrganization) {
		this.userOrganization = userOrganization;
	}
}
