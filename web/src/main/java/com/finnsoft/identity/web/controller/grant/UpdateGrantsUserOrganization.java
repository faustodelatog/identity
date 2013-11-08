/**
 * 
 */
package com.finnsoft.identity.web.controller.grant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import com.finnsoft.identity.core.exception.EntityNotFoundException;
import com.finnsoft.identity.core.model.Grant;
import com.finnsoft.identity.core.model.Role;
import com.finnsoft.identity.core.model.UserOrganization;
import com.finnsoft.identity.core.service.Roles;
import com.finnsoft.identity.core.service.UserOrganizations;
import com.finnsoft.identity.web.controller.exception.WrongParameterTypeException;
import com.finnsoft.identity.web.controller.util.BaseController;
import com.finnsoft.identity.web.controller.util.FacesMessageHelper;
import com.finnsoft.viewscope.ViewScoped;

/**
 * @author faustodelatog
 * 
 */
@ViewScoped
@Named
public class UpdateGrantsUserOrganization extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private static final String userIdNameParameter = "userId";
	private static final String organizationIdNameParameter = "organizationId";

	@Inject
	private UserOrganizations userOrganizations;
	@Inject
	private Roles roles;

	private UserOrganization userOrganization;

	private List<Role> roleList;

	@PostConstruct
	public void init() {
		try {
			Long userId = getRequestParameterAsLong(userIdNameParameter);
			Long organizationId = getRequestParameterAsLong(organizationIdNameParameter);
			userOrganization = userOrganizations.findByUserAndOrganization(
					userId, organizationId);
			roleList = roles.findAll();
			initSelectedRoles();
		} catch (WrongParameterTypeException e) {
			FacesMessageHelper.addError(e.getMessage());
		}
	}

	private void initSelectedRoles() {
		for (Role r : roleList) {
			r.setSelected(isRoleGranted(r));
		}
	}

	private boolean isRoleGranted(Role r) {
		for (Grant g : userOrganization.getGrantList()) {
			if (g.getRoleId().equals(r.getId())) {
				return true;
			}
		}
		return false;
	}

	public String save() {
		try {
			userOrganizations.updateGrants(userOrganization.getUserId(),
					userOrganization.getOrganizationId(), getRoleIds());
			FacesMessageHelper.addInfo(String.format(
					"Permisos otorgados al usuario %s en la organización %s",
					userOrganization.getUser().getUsername(), userOrganization
							.getOrganization().getName()));
			return String.format(
					"/pages/organization/user/list?id=%s&faces-redirect=true",
					userOrganization.getOrganizationId());
		} catch (EntityNotFoundException e) {
			FacesMessageHelper.addError(e.getMessage());
		}
		return null;
	}

	public String cancel() {
		if (userOrganization.getOrganizationId() != null) {
			return String.format(
					"/pages/organization/user/list?id=%s&faces-redirect=true",
					userOrganization.getOrganizationId());
		}
		return "/pages/organization/list?faces-redirect=true";
	}

	private List<Long> getRoleIds() {
		List<Long> list = new ArrayList<>();
		for (Role r : roleList) {
			if (r.isSelected()) {
				list.add(r.getId());
			}
		}
		return list;
	}

	public UserOrganization getUserOrganization() {
		return userOrganization;
	}

	public List<Role> getRoleList() {
		return roleList;
	}
}
