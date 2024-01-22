package br.com.insight.hourapp.web.entities.dto;

import br.com.insight.hourapp.web.entities.HourMarker;

public class HourMarkerDTO {

	private long markerId;
	private String entryHour;
	private String departureTime;
	
	public HourMarkerDTO(HourMarker marker) {
		this.entryHour = marker.getEntryHour();
		this.departureTime = marker.getDepartureTime();
		this.markerId = marker.getMarkerId();
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
	

	public HourMarker fromDTO() {
		HourMarker hm = new HourMarker();
		
		hm.setMarkerId(markerId);
		hm.setEntryHour(entryHour);
		hm.setDepartureTime(departureTime);
		
		return hm;
	}	
	
}
