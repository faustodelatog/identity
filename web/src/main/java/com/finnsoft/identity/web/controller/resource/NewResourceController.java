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
public class NewResourceController extends BaseController implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Resources resources;

	@Named
	@Produces
	private Resource newResource = new Resource();

	private String parentParameterName = "parentId";

	@PostConstruct
	public void init() {
		String parentIdString = getRequestParameter(parentParameterName);

		if (parentIdString != null) {
			try {
				Long parentId = Long.valueOf(parentIdString);
				newResource.setParent(resources.findById(parentId));
			} catch (NumberFormatException e) {
				FacesMessageHelper.addError(String.format(
						"Se esperaba el parametro %s como número",
						parentParameterName), e.getMessage());
			} catch (EntityNotFoundException e) {
				FacesMessageHelper.addError(e.getMessage());
			}
		}
	}

	public String save() {
		resources.save(newResource);
		FacesMessageHelper.addInfo(String.format("Nuevo recurso creado [%s]",
				newResource));
		return "/pages/resource/list?faces-redirect=true";
	}

	public Resource getNewResource() {
		return newResource;
	}

	public void setNewResource(Resource newResource) {
		this.newResource = newResource;
	}

}
