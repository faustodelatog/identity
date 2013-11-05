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
		@NamedQuery(name = "Resource.findByParent", query = "select r from Resource r where r.parent.id = ?1"),
		@NamedQuery(name = "Resource.findParents", query = "select r from Resource r where r.parent is null") })
public class Resource implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column(nullable = false)
	@NotNull(message = "El nombre del recurso es requerido")
	@Basic(optional = false)
	private String name;

	@Column(nullable = false)
	@NotNull(message = "La url del recurso es requerida")
	@Basic(optional = false)
	private String url;

	@Column
	private Boolean visible;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Resource parent;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return String.format("[ID: %s, Padre: %s, Nombre: %s]", id,
				getParentsString(), name);
	}

	public String getParentsString() {
		return builParentsString(parent).substring(2);

	}

	private String builParentsString(Resource r) {
		if (r == null) {
			return " > ";
		}
		return builParentsString(r.getParent()) + " > " + r.getName();
	}

}
