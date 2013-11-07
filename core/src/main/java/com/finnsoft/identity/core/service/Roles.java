/**
 * 
 */
package com.finnsoft.identity.core.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.finnsoft.identity.core.exception.EntityNotFoundException;
import com.finnsoft.identity.core.model.Permission;
import com.finnsoft.identity.core.model.Role;
import com.finnsoft.identity.core.model.eao.GenericEao;

/**
 * @author faustodelatog
 * 
 */
@Stateless
public class Roles {

	@EJB
	private GenericEao<Role> roleEao;

	public void save(Role role) {
		if (role.getId() == null) {
			roleEao.persist(role);
		} else {
			roleEao.merge(role);
		}
	}

	public void updatePermissions(Long id, List<Long> resourceList)
			throws EntityNotFoundException {
		Role role = findById(id);
		role.getPermissionList().clear();
		for (Long resourceId : resourceList) {
			role.getPermissionList().add(new Permission(resourceId));
		}
		roleEao.merge(role);
	}

	public List<Role> findAll() {
		return roleEao.getResultListFromNamedQuery(Role.class, "Role.findAll");
	}

	public Role findById(Long id) throws EntityNotFoundException {
		Role r = roleEao.findById(Role.class, id);
		if (r == null) {
			throw new EntityNotFoundException(String.format(
					"No se encontro un rol con id %s", id));
		}
		return r;
	}
}
