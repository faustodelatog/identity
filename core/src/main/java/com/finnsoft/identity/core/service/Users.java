/**
 * 
 */
package com.finnsoft.identity.core.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.finnsoft.identity.core.exception.EntityNotFoundException;
import com.finnsoft.identity.core.model.User;
import com.finnsoft.identity.core.model.eao.GenericEao;

/**
 * @author faustodelatog
 * 
 */
@Stateless
public class Users {

	@EJB
	private GenericEao<User> userEao;

	public List<User> findAll() {
		return userEao.getResultListFromNamedQuery(User.class, "User.findAll");
	}

	public void save(User user) {
		if (user.getId() == null) {
			userEao.persist(user);
		} else {
			userEao.merge(user);
		}
	}

	public User findById(Long id) throws EntityNotFoundException {
		User u = userEao.findById(User.class, id);
		if (u == null) {
			throw new EntityNotFoundException(String.format(
					"No se encontro un usuario con id %s", id));
		}
		return u;
	}
}
