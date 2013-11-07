/**
 * 
 */
package com.finnsoft.identity.web.controller.user;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import com.finnsoft.identity.core.model.LdapUser;
import com.finnsoft.identity.core.model.User;
import com.finnsoft.identity.web.controller.util.FacesMessageHelper;
import com.finnsoft.viewscope.ViewScoped;

/**
 * @author faustodelatog
 * 
 */
@ViewScoped
@Named
public class UpdateLdapUserController extends UpdateUserController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Named
	@Produces
	private LdapUser updateLdapUser;

	@PostConstruct
	public void init() {
		try {
			updateLdapUser = (LdapUser) findUser();
		} catch (ClassCastException e) {
			FacesMessageHelper.addError("El usuario no es de tipo ldap");
		}
	}

	public LdapUser getUpdateLdapUser() {
		return updateLdapUser;
	}

	public void setUpdateLdapUser(LdapUser updateLdapUser) {
		this.updateLdapUser = updateLdapUser;
	}

	@Override
	public User getUser() {
		return updateLdapUser;
	}

}
