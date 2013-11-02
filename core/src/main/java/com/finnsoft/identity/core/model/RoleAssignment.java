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
public class RoleAssignment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "role_id")
	private Role role;

	@ManyToOne(optional = false)
	@JoinColumn(name = "role_to_assign_id")
	private Role roleToAssign;

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

	public Role getRoleToAssign() {
		return roleToAssign;
	}

	public void setRoleToAssign(Role roleToAssign) {
		this.roleToAssign = roleToAssign;
	}
}
