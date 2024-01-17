package br.com.insight.hourapp.entities;

import java.io.Serializable;
import java.util.GregorianCalendar;
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
 * @apiNote Entidade que representa o resultado das horas trabalhadas
 * 			Como elas podendo ser do tipo EXTRA ou ATRASO... 
 */
@Entity
@Table( name = "summary_of_hours_worked", 
		indexes = @Index(name = "idx_summary_hours_worked", columnList = "id")
	   )
public class SummaryOfHoursWorked implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "summary_hours_worker_id")
	@SequenceGenerator(name = "summary_hours_worker_id", sequenceName = "summary_hours_worker_id", allocationSize = 1)
	@Column(name = "id")
	private long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "work_schedule_id", nullable = false)
	private WorkSchedule workSchedule = new WorkSchedule();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hour_marker_id", nullable = false)
	private HourMarker hourMarker = new HourMarker();

	@Column(name = "create", columnDefinition = "CALENDAR")
	private GregorianCalendar created;
	
	@Column(name = "total_hours", nullable = false, length = 5)
	private String totalHours;

	public SummaryOfHoursWorked() {
		
	}
	
	public SummaryOfHoursWorked(long id, WorkSchedule workSchedule, HourMarker hourMarker, GregorianCalendar created,
			String totalHours) {
		super();
		this.id = id;
		this.workSchedule = workSchedule;
		this.hourMarker = hourMarker;
		this.created = created;
		this.totalHours = totalHours;
	}

	public WorkSchedule getWorkSchedule() {
		return workSchedule;
	}

	public void setWorkSchedule(WorkSchedule workSchedule) {
		this.workSchedule = workSchedule;
	}

	public HourMarker getHourMarker() {
		return hourMarker;
	}

	public void setHourMarker(HourMarker hourMarker) {
		this.hourMarker = hourMarker;
	}

	public GregorianCalendar getCreated() {
		return created;
	}

	public void setCreated(GregorianCalendar created) {
		this.created = created;
	}

	public String getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}

	public long getId() {
		return id;
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
		SummaryOfHoursWorked other = (SummaryOfHoursWorked) obj;
		return id == other.id;
	}

}
