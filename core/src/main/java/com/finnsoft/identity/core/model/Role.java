/**
 * 
 */
package com.finnsoft.identity.core.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * @author faustodelatog
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Role.findAll", query = "select r from Role r") })
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(nullable = false)
	@NotNull(message = "El nombre del rol es requerido")
	@Basic(optional = false)
	private String name;

	@Column(nullable = false)
	@NotNull(message = "La descripcion del rol es requerida")
	@Basic(optional = false)
	private String description;

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id", nullable = false)
	private List<Permission> permissionList;

	@OneToMany(mappedBy = "role")
	private List<RoleAssignment> roleAssignmentList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public List<RoleAssignment> getRoleAssignmentList() {
		return roleAssignmentList;
	}

	public void setRoleAssignmentList(List<RoleAssignment> roleAssignmentList) {
		this.roleAssignmentList = roleAssignmentList;
	}
}
