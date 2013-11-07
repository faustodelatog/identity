/**
 * 
 */
package com.finnsoft.identity.web.controller.user;

import java.io.Serializable;

import javax.inject.Inject;

import com.finnsoft.identity.core.exception.EntityNotFoundException;
import com.finnsoft.identity.core.model.User;
import com.finnsoft.identity.core.service.Users;
import com.finnsoft.identity.web.controller.exception.WrongParameterTypeException;
import com.finnsoft.identity.web.controller.util.BaseController;
import com.finnsoft.identity.web.controller.util.FacesMessageHelper;

/**
 * @author faustodelatog
 * 
 */
public abstract class UpdateUserController extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	protected Users users;

	protected static final String userParameterName = "id";

	protected User findUser() {
		try {
			Long userId = getRequestParameterAsLong(userParameterName);
			if (userId != null) {
				return users.findById(userId);
			}
		} catch (WrongParameterTypeException | EntityNotFoundException e) {
			FacesMessageHelper.addError(e.getMessage());
		}
		return null;
	}

	public String save() {
		users.save(getUser());
		FacesMessageHelper.addInfo(String.format("Rol actualizado: %s",
				getUser().getUsername()));
		return "/pages/user/list?faces-redirect=true";
	}

	public abstract User getUser();

}
