package br.com.insight.hourapp.entities;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.insight.hourapp.entities.interfaces.BaseEntity;

/**
 * @author Marcos Vinicius
 * @apiNote Entidade que representa o resultado das horas trabalhadas
 * 			Como elas podendo ser do tipo EXTRA ou ATRASO... 
 */
@Entity
@Table( name = "summary_hour", 
		indexes = @Index(name = "idx_summary_hour", columnList = "summary_id")
	   )
public class SummaryHour implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "summary_hour_id")
	@SequenceGenerator(name = "summary_hour_id", sequenceName = "summary_hour_id", allocationSize = 1)
	@Column(name = "summary_id")
	private long summaryId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "work_schedule_id", nullable = false)
	private WorkSchedule workSchedule = new WorkSchedule();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hour_marker_id", nullable = false)
	private HourMarker hourMarker = new HourMarker();

	@Column(name = "created", columnDefinition = "TIMESTAMP")
	private GregorianCalendar created;
	
	@Column(name = "total_hours", nullable = false, length = 5)
	private String totalHours;

	public SummaryHour() {
		
	}
	
	public SummaryHour(long summaryId, WorkSchedule workSchedule, HourMarker hourMarker, GregorianCalendar created,
			String totalHours) {
		this.summaryId = summaryId;
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

	public long getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(long summaryId) {
		this.summaryId = summaryId;
	}

	@Override
	public Object getId() {
		return summaryId;
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

}
