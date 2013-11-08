/**
 * 
 */
package com.finnsoft.identity.web.controller.organization;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.finnsoft.identity.core.exception.AddUserOrganizationException;
import com.finnsoft.identity.core.exception.EntityNotFoundException;
import com.finnsoft.identity.core.model.Organization;
import com.finnsoft.identity.core.model.User;
import com.finnsoft.identity.core.model.UserOrganization;
import com.finnsoft.identity.core.service.Organizations;
import com.finnsoft.identity.core.service.UserOrganizations;
import com.finnsoft.identity.core.service.Users;
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
public class UserListOrganizationController extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Organizations organizations;

	@Inject
	private UserOrganizations userOrganizations;

	@Inject
	private Users users;

	private Organization organization;

	private List<UserOrganization> organizationUsers;

	private List<User> availableUsers;

	private UserOrganization selected;

	private User userToAdd;

	private static final String organizationParameterName = "id";

	@PostConstruct
	public void init() {
		try {
			Long organizationId = getRequestParameterAsLong(organizationParameterName);
			if (organizationId != null) {
				organization = organizations.findById(organizationId);
				organizationUsers = userOrganizations
						.findByOrganization(organizationId);
				availableUsers = users
						.findUsersNotInOrganization(organizationId);
			}
		} catch (EntityNotFoundException | WrongParameterTypeException e) {
			FacesMessageHelper.addError(e.getMessage());
		}
	}

	public String updateGrants() {
		return String
				.format("/pages/grant/list?userId=%s&organizationId=%s&faces-redirect=true",
						selected.getUserId(), organization.getId());
	}

	public String addUserToOrganization() {
		try {
			userOrganizations.addUserOrganization(userToAdd.getId(),
					organization.getId());
			return String.format(
					"/pages/organization/user/list?id=%s&faces-redirect=true",
					organization.getId());
		} catch (AddUserOrganizationException e) {
			FacesMessageHelper.addError(e.getMessage());
		}
		return null;
	}

	public Organization getOrganization() {
		return organization;
	}

	public UserOrganization getSelected() {
		return selected;
	}

	public void setSelected(UserOrganization selected) {
		this.selected = selected;
	}

	public List<User> getAvailableUsers() {
		return availableUsers;
	}

	public List<UserOrganization> getOrganizationUsers() {
		return organizationUsers;
	}

	public User getUserToAdd() {
		return userToAdd;
	}

	public void setUserToAdd(User userToAdd) {
		this.userToAdd = userToAdd;
	}

}
