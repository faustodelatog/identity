/**
 * 
 */
package com.finnsoft.identity.core.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.finnsoft.identity.core.exception.AddUserOrganizationException;
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

	public void addUserOrganization(Long userId, Long organizationId)
			throws AddUserOrganizationException {
		List<UserOrganization> list = userOrganizationEao
				.getResultListFromNamedQuery(UserOrganization.class,
						"UserOrganization.findByUserOrganization", userId,
						organizationId);
		if (!list.isEmpty()) {
			throw new AddUserOrganizationException(
					String.format(
							"El usuario %s ya se encuentra registrado en la organización %s",
							userId, organizationId));
		}
		UserOrganization uo = new UserOrganization(userId, organizationId);
		userOrganizationEao.persist(uo);
	}
}
