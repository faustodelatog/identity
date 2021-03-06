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
public class UpdateOrganizationController extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Organizations organizations;

	@Named
	@Produces
	private Organization updateOrganization;

	private static final String organizationParameterName = "id";

	@PostConstruct
	public void init() {

		try {
			Long organizationId = getRequestParameterAsLong(organizationParameterName);
			if (organizationId != null) {
				updateOrganization = organizations.findById(organizationId);
			}
		} catch (EntityNotFoundException | WrongParameterTypeException e) {
			FacesMessageHelper.addError(e.getMessage());
		}
	}

	public String save() {
		organizations.save(updateOrganization);
		FacesMessageHelper.addInfo(String.format("Organización modificada: %s",
				updateOrganization.getName()));
		return "/pages/organization/list?faces-redirect=true";
	}

	public Organization getUpdateOrganization() {
		return updateOrganization;
	}

	public void setUpdateOrganization(Organization updateOrganization) {
		this.updateOrganization = updateOrganization;
	}

}
