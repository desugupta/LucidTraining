package com.leave.employee.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "HolidaysList")
public class HolidaysList {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Integer holidayId;
	private Date holidayDate;
	private String reason;

	public Integer getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
	}

	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "HolidaysList [id=" + id + ", holidayDate=" + holidayDate + ", reason=" + reason + "]";
	}

}
