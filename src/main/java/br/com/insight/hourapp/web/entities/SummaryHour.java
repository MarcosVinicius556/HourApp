package br.com.insight.hourapp.web.entities;

import java.io.Serializable;
import java.util.Objects;

import br.com.insight.hourapp.web.entities.interfaces.BaseEntity;

/**
 * @author Marcos Vinicius
 * @apiNote Entidade que representa o resultado das horas trabalhadas
 * 			Como elas podendo ser do tipo EXTRA ou ATRASO... 
 */
public class SummaryHour implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	public static class Builder {
		private long summaryId;
		private String totalHours;
		private int hourType;
		
		public Builder setSummaryId(long summaryId) {
			this.summaryId = summaryId;
			return this;
		}
		
		public Builder setTotalHours(String totalHours) {
			this.totalHours = totalHours;
			return this;
		}
		
		public Builder setHourType(int hourType) {
			this.hourType = hourType;
			return this;
		}
		
		public SummaryHour build() {
			return new SummaryHour(this);
		}
	}
	
	private long summaryId;
	private String totalHours;
	private int hourType;

	public SummaryHour() {
		
	}
	
	public SummaryHour(Builder builder) {
		this.summaryId = builder.summaryId;
		this.totalHours = builder.totalHours;
		this.hourType = builder.hourType;
	}
	
	public SummaryHour(long summaryId, String totalHours, int hourType) {
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
