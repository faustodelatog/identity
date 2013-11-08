/**
 * 
 */
package com.finnsoft.identity.web.controller.user;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.finnsoft.identity.core.model.DBUser;
import com.finnsoft.identity.core.model.LdapUser;
import com.finnsoft.identity.core.model.User;
import com.finnsoft.identity.core.service.Users;
import com.finnsoft.identity.web.controller.util.BaseController;
import com.finnsoft.viewscope.ViewScoped;

/**
 * @author faustodelatog
 * 
 */
@Named
@ViewScoped
public class ListUserController extends BaseController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Users users;

	private List<User> list;

	private User selected;

	@PostConstruct
	public void init() {
		list = users.findAll();
	}

	public String newUser(String type) {
		return String.format("/pages/user/%s/new?faces-redirect=true", type);
	}

	public String updateUser() {
		String type = selected instanceof DBUser ? "db"
				: selected instanceof LdapUser ? "ldap" : null;
		return String.format("/pages/user/%s/update?id=%s&faces-redirect=true",
				type, selected.getId());
	}

	public List<User> getList() {
		return list;
	}

	public User getSelected() {
		return selected;
	}

	public void setSelected(User selected) {
		this.selected = selected;
	}

}
