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
public class UpdateOrganizationController extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Organizations organization;

	@Named
	@Produces
	private Organization updateOrganization;

	private String organizationParameterName = "id";

	@PostConstruct
	public void init() {
		String organizationIdString = getRequestParameter(organizationParameterName);

		try {
			Long organizationId = Long.valueOf(organizationIdString);
			if (organizationId != null) {
				updateOrganization = organization.findById(organizationId);
			}
		} catch (NumberFormatException e) {
			FacesMessageHelper.addError(String.format(
					"Se esperaba el parametro %s como número",
					organizationParameterName), e.getMessage());
		} catch (EntityNotFoundException e) {
			FacesMessageHelper.addError(e.getMessage());
		}
	}

	public String save() {
		organization.save(updateOrganization);
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
