package br.com.insight.hourapp.web.entities;

import java.io.Serializable;
import java.util.Objects;

import br.com.insight.hourapp.web.entities.interfaces.BaseEntity;

/**
 * @author Marcos Vinicius
 * @apiNote Entidade que representa os horários de trabalho 
 */
public class WorkSchedule implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;
	
	private long scheduleId;
	private String entryHour;
	private String departureTime;

	public WorkSchedule() {
		
	}
	
	public WorkSchedule(String entryHour, String departureTime) {
		this.entryHour = entryHour;
		this.departureTime = departureTime;
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

	@Override
	public Object getId() {
		return scheduleId;
	}

	@Override
	public Class<? extends BaseEntity> ClassType() {
		return this.getClass();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(scheduleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkSchedule other = (WorkSchedule) obj;
		return scheduleId == other.scheduleId;
	}

	@Override
	public String toString() {
		return "WorkSchedule [scheduleId=" + scheduleId + ", entryHour=" + entryHour
				+ ", departureTime=" + departureTime + "]";
	}

	
}
