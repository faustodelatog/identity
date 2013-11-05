/**
 * 
 */
package com.finnsoft.identity.core.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.finnsoft.identity.core.exception.EntityNotFoundException;
import com.finnsoft.identity.core.model.Resource;
import com.finnsoft.identity.core.model.eao.GenericEao;

/**
 * @author faustodelatog
 * 
 */
@Stateless
public class Resources {

	@EJB
	private GenericEao<Resource> resourceEao;

	public Resource findById(Long id) throws EntityNotFoundException {
		Resource r = resourceEao.findById(Resource.class, id);
		if (r == null) {
			throw new EntityNotFoundException(String.format(
					"No se encontro un recurso con id %s", id));
		}
		return r;
	}

	public void save(Resource o) {
		if (o.getId() == null) {
			resourceEao.persist(o);
		} else {
			resourceEao.merge(o);
		}
	}

	public List<Resource> findByParent(Long parentId) {
		if (parentId == null) {
			return resourceEao.getResultListFromNamedQuery(Resource.class,
					"Resource.findParents");
		}
		return resourceEao.getResultListFromNamedQuery(Resource.class,
				"Resource.findByParent", parentId);
	}
}
