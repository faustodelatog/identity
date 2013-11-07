/**
 * 
 */
package com.finnsoft.identity.web.controller.util;

import javax.faces.context.FacesContext;

import com.finnsoft.identity.web.controller.exception.WrongParameterTypeException;

/**
 * @author faustodelatog
 * 
 */
public abstract class BaseController {

	protected String getRequestParameter(String parameterName) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(parameterName);
	}

	protected Long getRequestParameterAsLong(String parameterName)
			throws WrongParameterTypeException {
		String roleIdString = getRequestParameter(parameterName);

		try {
			Long roleId = Long.valueOf(roleIdString);
			return roleId;
		} catch (NumberFormatException e) {
			throw new WrongParameterTypeException(String.format(
					"Se esperaba el parametro %s como número", parameterName));
		}
	}
}
