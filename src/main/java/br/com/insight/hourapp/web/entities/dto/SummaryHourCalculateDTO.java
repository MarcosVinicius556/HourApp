package br.com.insight.hourapp.web.entities.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.insight.hourapp.web.entities.HourMarker;

public class SummaryHourCalculateDTO {

	private Set<String> scheduleIds = new HashSet<>();
	private HourMarker marker = null;
	
	public SummaryHourCalculateDTO(Set<String> scheduleIds, HourMarker marker) {
		this.scheduleIds = scheduleIds;
		this.marker = marker;
	}

	public Set<String> getScheduleIds() {
		return scheduleIds;
	}

	public void setScheduleIds(Set<String> scheduleIds) {
		this.scheduleIds = scheduleIds;
	}

	public HourMarker getMarker() {
		return marker;
	}

	public void setMarker(HourMarker marker) {
		this.marker = marker;
	}
	
}
