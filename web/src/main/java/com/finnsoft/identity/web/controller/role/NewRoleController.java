/**
 * 
 */
package com.finnsoft.identity.web.controller.role;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.finnsoft.identity.core.model.Role;
import com.finnsoft.identity.core.service.Roles;
import com.finnsoft.identity.web.controller.util.BaseController;
import com.finnsoft.identity.web.controller.util.FacesMessageHelper;
import com.finnsoft.viewscope.ViewScoped;

/**
 * @author faustodelatog
 * 
 */
@Named
@ViewScoped
public class NewRoleController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Roles roles;

	@Named
	@Produces
	private Role newRole = new Role();

	public String save() {
		roles.save(newRole);
		FacesMessageHelper.addInfo(String.format("Nuevo rol creado: %s",
				newRole.getName()));
		return "/pages/role/list?faces-redirect=true";
	}

	public Role getNewRole() {
		return newRole;
	}

	public void setNewRole(Role newRole) {
		this.newRole = newRole;
	}

}
