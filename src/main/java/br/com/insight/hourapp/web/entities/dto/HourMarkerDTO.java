package br.com.insight.hourapp.web.entities.dto;

import br.com.insight.hourapp.web.entities.HourMarker;
import br.com.insight.hourapp.web.entities.WorkSchedule;

public class HourMarkerDTO {

	private long markerId;
	private long scheduleId;
	private String scheduleDescription;
	private String entryHour;
	private String departureTime;
	
	public HourMarkerDTO(HourMarker marker) {
		this.scheduleId = marker.getWorkSchedule().getScheduleId();
		this.scheduleDescription = marker.getWorkSchedule().getDescription();
		this.entryHour = marker.getEntryHour();
		this.departureTime = marker.getDepartureTime();
		this.markerId = marker.getMarkerId();
	}

	public long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
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
	
	public long getMarkerId() {
		return markerId;
	}

	public void setMarkerId(long markerId) {
		this.markerId = markerId;
	}
	
	public String getScheduleDescription() {
		return scheduleDescription;
	}

	public void setScheduleDescription(String scheduleDescription) {
		this.scheduleDescription = scheduleDescription;
	}

	public HourMarker fromDTO() {
		HourMarker hm = new HourMarker();
		WorkSchedule ws = new WorkSchedule();
		
		hm.setMarkerId(markerId);
		ws.setScheduleId(scheduleId);
		hm.setEntryHour(entryHour);
		hm.setDepartureTime(departureTime);
		hm.setWorkSchedule(ws);
		
		return hm;
	}	
	
}
