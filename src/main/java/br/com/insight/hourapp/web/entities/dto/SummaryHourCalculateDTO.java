package br.com.insight.hourapp.web.entities.dto;

import java.util.HashSet;
import java.util.Set;

public class SummaryHourCalculateDTO {

	private Set<String> scheduleIds = new HashSet<>();
	private Set<String> markerIds = new HashSet<>();
	
	public SummaryHourCalculateDTO(Set<String> scheduleIds, Set<String> markerIds) {
		this.scheduleIds = scheduleIds;
		this.markerIds = markerIds;
	}

	public Set<String> getScheduleIds() {
		return scheduleIds;
	}

	public void setScheduleIds(Set<String> scheduleIds) {
		this.scheduleIds = scheduleIds;
	}

	public Set<String> getMarkerIds() {
		return markerIds;
	}

	public void setMarkerIds(Set<String> markerIds) {
		this.markerIds = markerIds;
	}
	
}
