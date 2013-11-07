/**
 * 
 */
package com.finnsoft.identity.core.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author faustodelatog
 * 
 */
@Entity
@DiscriminatorValue("L")
public class LdapUser extends User {

	private static final long serialVersionUID = 1L;

	@Override
	public String getType() {
		return "LDAP";
	}

}
