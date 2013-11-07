/**
 * 
 */
package com.finnsoft.identity.web.controller.organization;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.finnsoft.identity.core.exception.EntityNotFoundException;
import com.finnsoft.identity.core.model.Organization;
import com.finnsoft.identity.core.model.UserOrganization;
import com.finnsoft.identity.core.service.Organizations;
import com.finnsoft.identity.core.service.UserOrganizations;
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

	private Organization organization;

	private List<UserOrganization> users;
	
	private UserOrganization selected;

	private static final String organizationParameterName = "id";

	@PostConstruct
	public void init() {
		try {
			Long organizationId = getRequestParameterAsLong(organizationParameterName);
			if (organizationId != null) {
				organization = organizations.findById(organizationId);
				users = userOrganizations.findByOrganization(organizationId);
			}
		} catch (EntityNotFoundException | WrongParameterTypeException e) {
			FacesMessageHelper.addError(e.getMessage());
		}
	}

	public String updateGrants() {
		return null;
	}

	public Organization getOrganization() {
		return organization;
	}

	public List<UserOrganization> getUsers() {
		return users;
	}

	public UserOrganization getSelected() {
		return selected;
	}

	public void setSelected(UserOrganization selected) {
		this.selected = selected;
	}

}
