/**
 * 
 */
package com.finnsoft.identity.web.controller.user;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.finnsoft.identity.core.model.DBUser;
import com.finnsoft.identity.core.model.User;
import com.finnsoft.viewscope.ViewScoped;

/**
 * @author faustodelatog
 * 
 */
@Named
@ViewScoped
public class NewDBUserController extends NewUserController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Named
	@Produces
	private DBUser newDBUser = new DBUser();

	@Override
	protected User getNewUser() {
		return newDBUser;
	}

	public DBUser getNewDBUser() {
		return newDBUser;
	}

	public void setNewDBUser(DBUser newDBUser) {
		this.newDBUser = newDBUser;
	}

}
