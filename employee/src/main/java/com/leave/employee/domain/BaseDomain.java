package com.leave.employee.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

public abstract class BaseDomain implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean deleteFlag;
	private Date deletedOn;
	private boolean activeFlag;
	private Date activeFromDate;
	private Date activeTillDate;
	@CreatedDate
	private Date createdOn;
	@CreatedBy
	private String createdBy;
	@LastModifiedDate
	private Date lastUpdatedOn;
	@LastModifiedBy
	private String lastUpdatedBy;

	public boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getDeletedOn() {
		return deletedOn;
	}

	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}

	public Boolean isActiveFlag() {
		return activeFlag;
	}

	public Date getActiveFromDate() {
		return activeFromDate;
	}

	public void setActiveFromDate(Date activeFromDate) {
		this.activeFromDate = activeFromDate;
	}

	public Date getActiveTillDate() {
		return activeTillDate;
	}

	public void setActiveTillDate(Date activeTillDate) {
		this.activeTillDate = activeTillDate;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;

	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

}
