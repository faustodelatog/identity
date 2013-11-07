/**
 * 
 */
package com.finnsoft.identity.web.controller.user;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.finnsoft.identity.core.model.LdapUser;
import com.finnsoft.identity.core.model.User;
import com.finnsoft.viewscope.ViewScoped;

/**
 * @author faustodelatog
 * 
 */
@Named
@ViewScoped
public class NewLdapUserController extends NewUserController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Named
	@Produces
	private LdapUser newLdapUser = new LdapUser();

	@Override
	protected User getNewUser() {
		return newLdapUser;
	}

	public LdapUser getNewLdapUser() {
		return newLdapUser;
	}

	public void setNewLdapUser(LdapUser newLdapUser) {
		this.newLdapUser = newLdapUser;
	}

}
