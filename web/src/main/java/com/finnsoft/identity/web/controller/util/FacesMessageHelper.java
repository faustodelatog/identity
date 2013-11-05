/**
 * 
 */
package com.finnsoft.identity.web.controller.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * 
 * @author faustodelatog
 * 
 */
public class FacesMessageHelper {

	public static void addError(final String summary, String detail) {
		addFacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
	}

	public static void addError(final String summary) {
		addFacesMessage(FacesMessage.SEVERITY_ERROR, summary, "");
	}

	public static void addInfo(final String summary, String detail) {
		addFacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	}

	public static void addInfo(final String summary) {
		addFacesMessage(FacesMessage.SEVERITY_INFO, summary, "");
	}

	private static void addFacesMessage(final Severity severity,
			final String summary, final String detail) {
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final FacesMessage message = new FacesMessage(severity, summary, detail);
		facesContext.addMessage(null, message);
		facesContext.getExternalContext().getFlash().setKeepMessages(true);
	}

}
