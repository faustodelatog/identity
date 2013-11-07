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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @author faustodelatog
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "UserOrganization.findByOrganization", query = "select uo from UserOrganization uo where uo.organizationId = ?1") })
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

	@ManyToOne
	@JoinColumn(name = "organization_id", updatable = false, insertable = false)
	private Organization organization;

	@Basic(optional = false)
	@Column(name = "organization_id")
	private Long organizationId;

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_organization_id", nullable = false)
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

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

}
