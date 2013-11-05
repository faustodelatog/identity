/**
 * 
 */
package com.finnsoft.identity.web.controller.resource;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import com.finnsoft.identity.core.exception.EntityNotFoundException;
import com.finnsoft.identity.core.model.Resource;
import com.finnsoft.identity.core.service.Resources;
import com.finnsoft.identity.web.controller.util.BaseController;
import com.finnsoft.identity.web.controller.util.FacesMessageHelper;
import com.finnsoft.viewscope.ViewScoped;

/**
 * @author faustodelatog
 * 
 */
@Named
@ViewScoped
public class UpdateResourceController extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Resources resources;

	@Named
	@Produces
	private Resource updateResource;

	private String resourceParameterName = "id";

	@PostConstruct
	public void init() {
		String resourceIdString = getRequestParameter(resourceParameterName);

		try {
			Long resourceId = Long.valueOf(resourceIdString);
			if (resourceId != null) {
				updateResource = resources.findById(resourceId);
			}
		} catch (NumberFormatException e) {
			FacesMessageHelper.addError(String.format(
					"Se esperaba el parametro %s como número",
					resourceParameterName), e.getMessage());
		} catch (EntityNotFoundException e) {
			FacesMessageHelper.addError(e.getMessage());
		}
	}

	public String save() {
		resources.save(updateResource);
		FacesMessageHelper.addInfo(String.format("Recurso modificado: %s",
				updateResource.getName()));
		return "/pages/resource/list?faces-redirect=true";
	}

	public Resource getUpdateResource() {
		return updateResource;
	}

	public void setUpdateResource(Resource updateResource) {
		this.updateResource = updateResource;
	}

}
