/**
 * 
 */
package com.finnsoft.identity.core.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * @author faustodelatog
 * 
 */
@Entity
public class Permission implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull(message = "El modo del permiso es requerido")
	@Basic(optional = false)
	private PermissionMode mode;

	@ManyToOne
	@JoinColumn(name = "resource_id", nullable = false)
	@NotNull(message = "El recurso del permiso es requerido")
	@Basic(optional = false)
	private Resource resource;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	@NotNull(message = "El rol del permiso es requerido")
	@Basic(optional = false)
	private Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PermissionMode getMode() {
		return mode;
	}

	public void setMode(PermissionMode mode) {
		this.mode = mode;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
