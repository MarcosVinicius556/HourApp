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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.insight.hourapp.entities.interfaces.BaseEntity;

/**
 * @author Marcos Vinicius
 * @apiNote Entidade que representa as marcações de horários de entrada e saída
 */
@Entity
@Table(name = "hour_marker", 
	   indexes = @Index(name="idx_hour_marker", columnList = "marker_id") 
	   )
public class HourMarker implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="marker_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hour_marker_id")
	@SequenceGenerator(name = "hour_marker_id", sequenceName = "hour_marker_id", allocationSize = 1)
	private long markerId;
	
	@Column(name = "entry_hour", length = 5)
	private String entryHour;
	
	@Column(name = "departure_hour", length = 5)
	private String departureTime;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn( name = "worker_schedule_id", nullable = false )
	private WorkSchedule workSchedule = new WorkSchedule();
	
	@OneToMany(mappedBy = "hourMarker", fetch = FetchType.EAGER)
	private Set<SummaryHour> summaryHours = new HashSet<>();

	public HourMarker() {
		
	}
	
	public HourMarker(String entryHour, String departureTime, WorkSchedule workSchedule) {
		this.entryHour = entryHour;
		this.departureTime = departureTime;
		this.workSchedule = workSchedule;
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

	public WorkSchedule getWorkSchedule() {
		return workSchedule;
	}

	public void setWorkSchedule(WorkSchedule workSchedule) {
		this.workSchedule = workSchedule;
	}
	
	public void addSummaryHour(SummaryHour summaryHour) {
		this.summaryHours.add(summaryHour);
	}
	
	public void removeSummaryHour(SummaryHour summaryHour) {
		this.summaryHours.remove(summaryHour);
	}
	
	@Override
	public Object getId() {
		return markerId;
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
