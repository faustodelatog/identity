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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 * @author faustodelatog
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Organization.findByParent", query = "select o from Organization o where o.parent.id = ?1"),
		@NamedQuery(name = "Organization.findParents", query = "select o from Organization o where o.parent is null") })
public class Organization implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	@NotNull(message = "El nombre de la organizacion es requerido y unico")
	@Basic(optional = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Organization parent;

	public Organization() {
	}

	public Organization(Organization parent) {
		super();
		this.parent = parent;
	}

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

	public Organization getParent() {
		return parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}

	public String getParentsString() {
		return builParentsString(parent).substring(2);

	}

	private String builParentsString(Organization o) {
		if (o == null) {
			return "";
		}
		return builParentsString(o.getParent()) + " > " + o.getName();
	}

}
