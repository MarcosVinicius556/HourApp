package br.com.insight.hourapp.web.entities;

import java.io.Serializable;
import java.util.Objects;

import br.com.insight.hourapp.web.entities.interfaces.BaseEntity;

/**
 * @author Marcos Vinicius
 * @apiNote Entidade que representa as marcações de horários de entrada e saída
 */
public class HourMarker implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;
	
	private long markerId;
	private String entryHour;
	private String departureTime;

	public HourMarker() {
		
	}
	
	public HourMarker(String entryHour, String departureTime) {
		this.entryHour = entryHour;
		this.departureTime = departureTime;
	}

	public long getMarkerId() {
		return markerId;
	}

	public void setMarkerId(long markerId) {
		this.markerId = markerId;
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
	public Long getId() {
		return markerId;
	}
	
	@Override
	public void setId(long newId) {
		this.markerId = newId;
	}
	
	@Override
	public Class<? extends BaseEntity> ClassType() {
		return this.getClass();
	}

	@Override
	public int hashCode() {
		return Objects.hash(markerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HourMarker other = (HourMarker) obj;
		return markerId == other.markerId;
	}

	@Override
	public String toString() {
		return "HourMarker [markerId=" + markerId + ", entryHour=" + entryHour + ", departureTime=" + departureTime
				+ "]";
	}

}
