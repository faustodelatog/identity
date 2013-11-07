/**
 * 
 */
package com.finnsoft.identity.core.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.finnsoft.identity.core.model.UserOrganization;
import com.finnsoft.identity.core.model.eao.GenericEao;

/**
 * @author faustodelatog
 * 
 */
@Stateless
public class UserOrganizations {

	@EJB
	private GenericEao<UserOrganization> userOrganizationEao;

	public List<UserOrganization> findByOrganization(Long organizationId) {
		return userOrganizationEao.getResultListFromNamedQuery(
				UserOrganization.class, "UserOrganization.findByOrganization",
				organizationId);
	}
}
