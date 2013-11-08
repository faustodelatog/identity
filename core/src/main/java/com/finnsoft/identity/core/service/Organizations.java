/**
 * 
 */
package com.finnsoft.identity.core.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.finnsoft.identity.core.exception.EntityNotFoundException;
import com.finnsoft.identity.core.model.Organization;
import com.finnsoft.identity.core.model.eao.GenericEao;

/**
 * @author faustodelatog
 * 
 */
@Stateless
public class Organizations {

	@EJB
	private GenericEao<Organization> organizationEao;

	public Organization findById(Long id) throws EntityNotFoundException {
		Organization o = organizationEao.findById(Organization.class, id);
		if (o == null) {
			throw new EntityNotFoundException(String.format(
					"No se encontro una organización con id %s", id));
		}
		return o;
	}

	public void save(Organization o) {
		if (o.getId() == null) {
			organizationEao.persist(o);
		} else {
			organizationEao.merge(o);
		}
	}

	public List<Organization> findByParent(Long parentId) {
		if (parentId == null) {
			return organizationEao.getResultListFromNamedQuery(
					Organization.class, "Organization.findParents");
		}
		return organizationEao.getResultListFromNamedQuery(Organization.class,
				"Organization.findByParent", parentId);
	}

}
