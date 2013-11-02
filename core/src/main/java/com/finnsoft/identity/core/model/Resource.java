/**
 * 
 */
package com.finnsoft.identity.core.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * @author faustodelatog
 * 
 */
@Entity
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

	@OneToMany(mappedBy = "parent")
	private List<Resource> children;

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

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

}
