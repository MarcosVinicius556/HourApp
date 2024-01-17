package br.com.insight.hourapp.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * @author Marcos Vinicius
 * @apiNote Entidade que representa as marcações de horários de entrada e saída
 */
@Entity
@Table(name = "hour_marker", 
	   indexes = @Index(name="idx_hour_marker", columnList = "id") 
	   )
public class HourMarker implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hour_marker_id")
	@SequenceGenerator(name = "hour_marker_id", sequenceName = "hour_marker_id", allocationSize = 1)
	private long id;
	
	@Column(name = "description", length = 128)
	private String description;
	
	@Column(name = "entry_hour", length = 5)
	private String entryHour;
	
	@Column(name = "departure_hour", length = 5)
	private String departureTime;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn( name = "worker_schedule_id", nullable = false )
	private WorkSchedule workSchedule = new WorkSchedule();

	public HourMarker() {
		
	}
	
	public HourMarker(long id, String description, String entryHour, String departureTime, WorkSchedule workSchedule) {
		this.id = id;
		this.description = description;
		this.entryHour = entryHour;
		this.departureTime = departureTime;
		this.workSchedule = workSchedule;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public WorkSchedule getWorkSchedule() {
		return workSchedule;
	}

	public void setWorkSchedule(WorkSchedule workSchedule) {
		this.workSchedule = workSchedule;
	}

	public HourMarker(long id, String description, String entryHour, String departureTime) {
		this.id = id;
		this.description = description;
		this.entryHour = entryHour;
		this.departureTime = departureTime;
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
		HourMarker other = (HourMarker) obj;
		return id == other.id;
	}
	
}
