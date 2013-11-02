/**
 * 
 */
package com.finnsoft.identity.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author faustodelatog
 * 
 */
@Entity
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	@Basic(optional = false)
	private Date start;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date end;

	@Enumerated(EnumType.STRING)
	@Column
	private LogoutType logoutType;

	@JoinColumn(name = "user_id", nullable = false)
	@ManyToOne(optional = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public LogoutType getLogoutType() {
		return logoutType;
	}

	public void setLogoutType(LogoutType logoutType) {
		this.logoutType = logoutType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
