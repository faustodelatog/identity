/**
 * 
 */
package com.finnsoft.identity.core.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author faustodelatog
 * 
 */
@Entity
@Table(name = "T_Grant")
public class Grant implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false)
	private Role role;

	@Column(name = "role_id")
	@NotNull(message = "El rol del grant es requerido")
	@Basic(optional = false)
	private Long roleId;

	public Grant() {
	}

	public Grant(Long roleId) {
		super();
		this.roleId = roleId;
	}

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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
