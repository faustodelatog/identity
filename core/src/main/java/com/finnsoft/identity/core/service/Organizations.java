/**
 * 
 */
package com.finnsoft.identity.core.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.finnsoft.identity.core.model.Organization;

/**
 * @author faustodelatog
 * 
 */
@Stateless
public class Organizations {

	@PersistenceContext
	protected EntityManager em;

	public void create(Organization o) {
		em.persist(o);
	}
}
