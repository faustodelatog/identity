/**
 * 
 */
package com.finnsoft.identity.web.controller.user;

import java.io.Serializable;

import javax.inject.Inject;

import com.finnsoft.identity.core.model.User;
import com.finnsoft.identity.core.service.Users;
import com.finnsoft.identity.web.controller.util.BaseController;
import com.finnsoft.identity.web.controller.util.FacesMessageHelper;

/**
 * @author faustodelatog
 * 
 */
public abstract class NewUserController extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Users users;

	public String save() {
		users.save(getNewUser());
		FacesMessageHelper.addInfo(String.format("Nuevo usuario creado: %s",
				getNewUser().getUsername()));
		return "/pages/user/list?faces-redirect=true";
	}

	protected abstract User getNewUser();

}
