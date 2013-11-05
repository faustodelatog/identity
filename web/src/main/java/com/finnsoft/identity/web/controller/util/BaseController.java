/**
 * 
 */
package com.finnsoft.identity.web.controller.util;

import javax.faces.context.FacesContext;

/**
 * @author faustodelatog
 * 
 */
public abstract class BaseController {

	protected String getRequestParameter(String parameterName) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(parameterName);
	}
}
