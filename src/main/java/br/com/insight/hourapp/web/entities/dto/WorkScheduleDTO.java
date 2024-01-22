package br.com.insight.hourapp.web.entities.dto;

import br.com.insight.hourapp.web.entities.WorkSchedule;

public class WorkScheduleDTO {

	private long scheduleId;
	private String entryHour;
	private String departureTime;
	
	public WorkScheduleDTO(WorkSchedule schedule) {
		this.entryHour = schedule.getEntryHour();
		this.departureTime = schedule.getDepartureTime();
		this.scheduleId = schedule.getScheduleId(); 
	}

	public String getEntryHour() {
		return entryHour;
	}

	public void setEntryHour(String entryHour) {
		this.entryHour = entryHour;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}
	
}
