package br.com.insight.hourapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * @author Marcos Vinicius
 * @apiNote Entidade que representa os horários de trabalho 
 */
@Entity
@Table(name = "worker_schedule", 
	   indexes = @Index(name="idx_worker_schedule", columnList = "id") 
	   )
public class WorkSchedule implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "worker_schedule_id")
	@SequenceGenerator(name = "worker_schedule_id", sequenceName = "worker_schedule_id", allocationSize = 1)
	private long id;
	
	@Column(name = "description", length = 128)
	private String description;
	
	@Column(name = "entry_hour", length = 5)
	private String entryHour;
	
	@Column(name = "departure_hour", length = 5)
	private String departureTime;
	
	@OneToMany( mappedBy = "workerSchedule", fetch = FetchType.EAGER )
	private Set<HourMarker> hourMarkers = new HashSet<>();

	public WorkSchedule() {
		
	}
	
	public WorkSchedule(long id, String description, String entryHour, String departureTime) {
		this.id = id;
		this.description = description;
		this.entryHour = entryHour;
		this.departureTime = departureTime;
	}
	
	public long getId() {
		return id;
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
	public int hashCode() {
		return Objects.hash(id);
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
		return id == other.id;
	}
	
}
