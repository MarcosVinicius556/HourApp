package br.com.insight.hourapp.web.entities.dto;

import br.com.insight.hourapp.web.entities.SummaryHour;
import br.com.insight.hourapp.web.entities.enums.HourType;

public class SummaryHourDTO {

	private long summaryId;
	private String totalHours;
	private int hourTypeCod;
	private String hourType;
	
	public SummaryHourDTO(SummaryHour sh) {
		this.totalHours = sh.getTotalHours();
		this.hourTypeCod = sh.getHourType();
		this.hourType = HourType.getDescriptionByCod(sh.getHourType()).getDescription();
		this.summaryId = sh.getSummaryId();
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
		SummaryHour sh = new SummaryHour();
		sh.setTotalHours(totalHours);
		sh.setHourType(hourTypeCod);
		
		return sh;
	}
	
}
