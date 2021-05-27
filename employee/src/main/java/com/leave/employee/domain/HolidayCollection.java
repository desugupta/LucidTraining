package com.leave.employee.domain;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "HolidayCollection")
public class HolidayCollection {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private Integer holidayId;
	//private Date holidayDate;
	private LocalDate holiday;
	private String reason;
	private Integer holidayYear;

	
	public LocalDate getHoliday() {
		return holiday;
	}

	public void setHoliday(LocalDate holiday) {
		this.holiday = holiday;
	}
	public Integer getHolidayYear() {
		return holidayYear;
	}

	public void setHolidayYear(Integer holidayYear) {
		this.holidayYear = holidayYear;
	}

	public Integer getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Integer holidayId) {
		this.holidayId = holidayId;
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
		return "HolidayCollection [id=" + id + ", holidayId=" + holidayId + ", holiday=" + holiday + ", reason=" + reason + ", holidayYear=" + holidayYear + "]";
	}


}
