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

/**
 * @author faustodelatog
 * 
 */
@Entity
public class UserOrganization implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@JoinColumn(name = "user_id", nullable = false)
	@ManyToOne(optional = false)
	@Basic(optional = false)
	private User user;

	@ManyToOne(optional = false)
	@Basic(optional = false)
	@JoinColumn(name = "organization_id")
	private Organization organization;

	@OneToMany(mappedBy = "userOrganization")
	private List<Grant> grantList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<Grant> getGrantList() {
		return grantList;
	}

	public void setGrantList(List<Grant> grantList) {
		this.grantList = grantList;
	}

}
