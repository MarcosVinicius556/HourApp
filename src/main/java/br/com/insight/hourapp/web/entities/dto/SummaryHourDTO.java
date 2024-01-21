package br.com.insight.hourapp.web.entities.dto;

import java.text.SimpleDateFormat;

import br.com.insight.hourapp.web.entities.HourMarker;
import br.com.insight.hourapp.web.entities.SummaryHour;
import br.com.insight.hourapp.web.entities.WorkSchedule;
import br.com.insight.hourapp.web.entities.enums.HourType;

public class SummaryHourDTO {

	private long summaryId;
	private long scheduleId;
	private long markerId;
	private String totalHours;
	private int hourTypeCod;
	private String hourType;
	private String created;
	
	public SummaryHourDTO(SummaryHour sh) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.scheduleId = sh.getWorkSchedule().getScheduleId();
		this.markerId = sh.getHourMarker().getMarkerId();
		this.totalHours = sh.getTotalHours();
		this.hourTypeCod = sh.getHourType();
		this.hourType = HourType.getDescriptionByCod(sh.getHourType()).getDescription();
		this.created = sdf.format(sh.getCreated().getTime());
		this.summaryId = sh.getSummaryId();
	}

	public long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public long getMarkerId() {
		return markerId;
	}

	public void setMarkerId(long markerId) {
		this.markerId = markerId;
	}

	public String getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}
	
	public String getHourType() {
		return hourType;
	}

	public void setHourType(String hourType) {
		this.hourType = hourType;
	}
	
	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public long getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(long summaryId) {
		this.summaryId = summaryId;
	}

	public int getHourTypeCod() {
		return hourTypeCod;
	}

	public void setHourTypeCod(int hourTypeCod) {
		this.hourTypeCod = hourTypeCod;
	}

	public SummaryHour fromDTO() {
		WorkSchedule ws = new WorkSchedule();
		ws.setScheduleId(scheduleId);

		HourMarker hm = new HourMarker();
		hm.setMarkerId(markerId);
		
		SummaryHour sh = new SummaryHour();
		sh.setWorkSchedule(ws);
		sh.setHourMarker(hm);
		sh.setTotalHours(totalHours);
		sh.setHourType(hourTypeCod);
		
		return sh;
	}
	
}
