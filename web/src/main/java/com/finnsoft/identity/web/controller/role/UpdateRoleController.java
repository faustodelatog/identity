/**
 * 
 */
package com.finnsoft.identity.web.controller.role;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.finnsoft.identity.core.exception.EntityNotFoundException;
import com.finnsoft.identity.core.model.Role;
import com.finnsoft.identity.core.service.Roles;
import com.finnsoft.identity.web.controller.exception.WrongParameterTypeException;
import com.finnsoft.identity.web.controller.util.BaseController;
import com.finnsoft.identity.web.controller.util.FacesMessageHelper;
import com.finnsoft.viewscope.ViewScoped;

/**
 * @author faustodelatog
 * 
 */
@Named
@ViewScoped
public class UpdateRoleController extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Roles roles;

	@Named
	@Produces
	private Role updateRole;

	private String roleParameterName = "id";

	@PostConstruct
	public void init() {
		try {
			Long roleId = getRequestParameterAsLong(roleParameterName);
			if (roleId != null) {
				updateRole = roles.findById(roleId);
			}
		} catch (WrongParameterTypeException | EntityNotFoundException e) {
			FacesMessageHelper.addError(e.getMessage());
		}
	}

	public String save() {
		roles.save(updateRole);
		FacesMessageHelper.addInfo(String.format("Rol actualizado: %s",
				updateRole.getName()));
		return "/pages/role/list?faces-redirect=true";
	}

	public Role getUpdateRole() {
		return updateRole;
	}

	public void setUpdateRole(Role updateRole) {
		this.updateRole = updateRole;
	}

}
