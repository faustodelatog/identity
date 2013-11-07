/**
 * 
 */
package com.finnsoft.identity.web.controller.user;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.finnsoft.identity.core.model.DBUser;
import com.finnsoft.identity.core.model.User;
import com.finnsoft.identity.web.controller.util.FacesMessageHelper;
import com.finnsoft.viewscope.ViewScoped;

/**
 * @author faustodelatog
 * 
 */
@ViewScoped
@Named
public class UpdateDBUserController extends UpdateUserController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Named
	@Produces
	private DBUser updateDBUser;

	@PostConstruct
	public void init() {
		try {
			updateDBUser = (DBUser) findUser();
		} catch (ClassCastException e) {
			FacesMessageHelper
					.addError("El usuario no es de tipo base de datos");
		}
	}

	public DBUser getUpdateDBUser() {
		return updateDBUser;
	}

	public void setUpdateDBUser(DBUser updateDBUser) {
		this.updateDBUser = updateDBUser;
	}

	@Override
	public User getUser() {
		return updateDBUser;
	}

}
