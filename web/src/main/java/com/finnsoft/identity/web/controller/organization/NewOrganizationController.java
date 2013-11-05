/**
 * 
 */
package com.finnsoft.identity.web.controller.organization;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.finnsoft.identity.core.exception.EntityNotFoundException;
import com.finnsoft.identity.core.model.Organization;
import com.finnsoft.identity.core.service.Organizations;
import com.finnsoft.identity.web.controller.util.BaseController;
import com.finnsoft.identity.web.controller.util.FacesMessageHelper;
import com.finnsoft.viewscope.ViewScoped;

/**
 * @author faustodelatog
 * 
 */
@Named
@ViewScoped
public class NewOrganizationController extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Organizations organizations;

	@Named
	@Produces
	private Organization newOrganization = new Organization();

	private String parentParameterName = "parentId";

	@PostConstruct
	public void init() {
		String parentIdString = getRequestParameter(parentParameterName);

		if (parentIdString != null) {
			try {
				Long parentId = Long.valueOf(parentIdString);
				newOrganization.setParent(organizations.findById(parentId));
			} catch (NumberFormatException e) {
				FacesMessageHelper.addError(String.format(
						"Se esperaba el parametro %s como número",
						parentParameterName), e.getMessage());
				newOrganization = null;
			} catch (EntityNotFoundException e) {
				FacesMessageHelper.addError(e.getMessage());
				newOrganization = null;
			}
		}
	}

	public String save() {
		organizations.save(newOrganization);
		FacesMessageHelper.addInfo(String.format(
				"Nueva organización creada: %s", newOrganization.getName()));
		return "/pages/organization/list?faces-redirect=true";
	}

	public Organization getNewOrganization() {
		return newOrganization;
	}

	public void setNewOrganization(Organization newOrganization) {
		this.newOrganization = newOrganization;
	}

}
