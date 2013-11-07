/**
 * 
 */
package com.finnsoft.identity.web.controller.role;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.finnsoft.identity.core.model.Role;
import com.finnsoft.identity.core.service.Roles;
import com.finnsoft.identity.web.controller.util.BaseController;
import com.finnsoft.viewscope.ViewScoped;

/**
 * @author faustodelatog
 * 
 */
@Named
@ViewScoped
public class ListRoleController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Roles roles;

	private List<Role> list;

	private Role selected;

	@PostConstruct
	public void init() {
		list = roles.findAll();
	}

	public String newRole() {
		return "/pages/role/new?faces-redirect=true";
	}

	public String updateRole() {
		return String.format("/pages/role/update?id=%sfaces-redirect=true",
				selected.getId());
	}

	public String updateRolePermissions() {
		return String.format(
				"/pages/role/permissionList?id=%sfaces-redirect=true",
				selected.getId());
	}

	public List<Role> getList() {
		return list;
	}

	public Role getSelected() {
		return selected;
	}

	public void setSelected(Role selected) {
		this.selected = selected;
	}

}
