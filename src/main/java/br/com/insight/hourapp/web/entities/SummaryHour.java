package br.com.insight.hourapp.web.entities;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;

import br.com.insight.hourapp.web.entities.interfaces.BaseEntity;

/**
 * @author Marcos Vinicius
 * @apiNote Entidade que representa o resultado das horas trabalhadas
 * 			Como elas podendo ser do tipo EXTRA ou ATRASO... 
 */
public class SummaryHour implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	private long summaryId;
	private String totalHours;
	private int hourType;

	public SummaryHour() {
		
	}
	
	public SummaryHour(long summaryId, GregorianCalendar created,
			String totalHours, int hourType) {
		this.summaryId = summaryId;
		this.totalHours = totalHours;
		this.hourType = hourType;
	}

	public String getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}

	public long getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(long summaryId) {
		this.summaryId = summaryId;
	}
	
	public int getHourType() {
		return hourType;
	}

	public void setHourType(int hourType) {
		this.hourType = hourType;
	}
	


	@Override
	public Long getId() {
		return summaryId;
	}
	
	@Override
	public void setId(long newId) {
		this.summaryId = newId;
	}

	@Override
	public Class<? extends BaseEntity> ClassType() {
		return this.getClass();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(summaryId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SummaryHour other = (SummaryHour) obj;
		return summaryId == other.summaryId;
	}

	@Override
	public String toString() {
		return "SummaryHour [summaryId=" + summaryId + ", totalHours=" + totalHours + ", hourType=" + hourType + "]";
	}

	
	
}
