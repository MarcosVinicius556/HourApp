package br.com.insight.hourapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.insight.hourapp.entities.interfaces.BaseEntity;

/**
 * @author Marcos Vinicius
 * @apiNote Entidade que representa os horários de trabalho 
 */
@Entity
@Table(name = "worker_schedule", 
	   indexes = @Index(name="idx_worker_schedule", columnList = "schedule_id") 
	   )
public class WorkSchedule implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="schedule_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "worker_schedule_id")
	@SequenceGenerator(name = "worker_schedule_id", sequenceName = "worker_schedule_id", allocationSize = 1)
	private long scheduleId;
	
	@Column(name = "description", length = 128)
	private String description;
	
	@Column(name = "entry_hour", length = 5)
	private String entryHour;
	
	@Column(name = "departure_hour", length = 5)
	private String departureTime;
	
	@OneToMany( mappedBy = "workSchedule", fetch = FetchType.EAGER )
	private Set<HourMarker> hourMarkers = new HashSet<>();

	public WorkSchedule() {
		
	}
	
	public WorkSchedule(String description, String entryHour, String departureTime) {
		this.description = description;
		this.entryHour = entryHour;
		this.departureTime = departureTime;
	}

	public long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void addHourMaker(HourMarker hourMarker) {
		this.hourMarkers.add(hourMarker);
	}
	
	public void removeHourMaker(HourMarker hourMarker) {
		this.hourMarkers.remove(hourMarker);
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
		return "WorkSchedule [scheduleId=" + scheduleId + ", description=" + description + ", entryHour=" + entryHour
				+ ", departureTime=" + departureTime + "]";
	}

	
}
