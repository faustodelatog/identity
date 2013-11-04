/**
 * 
 */
package com.finnsoft.identity.core.model.eao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author faustodelatog
 * 
 */
@Stateless
public class GenericEao<T> {

	@PersistenceContext
	protected EntityManager em;

	public void persist(T o) {
		em.persist(o);
	}

	public T merge(T o) {
		return em.merge(o);
	}

	public List<T> getResultListFromNamedQuery(Class<T> type,
			final String namedQuery, final Object... parameters) {
		TypedQuery<T> q = em.createNamedQuery(namedQuery, type);
		if (parameters != null && parameters.length > 0) {
			int i = 1;
			for (Object parameter : parameters) {
				q.setParameter(i++, parameter);
			}
		}
		return q.getResultList();
	}
}
