package br.com.insight.hourapp;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import br.com.insight.hourapp.entities.HourMarker;
import br.com.insight.hourapp.entities.SummaryOfHoursWorked;
import br.com.insight.hourapp.entities.WorkSchedule;
import br.com.insight.hourapp.services.factory.ServiceFactory;
import br.com.insight.hourapp.services.interfaces.HourMarkerService;
import br.com.insight.hourapp.services.interfaces.WorkScheduleService;
import junit.framework.TestCase;

public class HourMarkerServiceTests extends TestCase {

	private HourMarkerService hourService = null;
	private WorkScheduleService scheduleService = null;
	
	@BeforeEach
	protected void setUp() throws Exception {
		hourService = ServiceFactory.createHourMarkerService();
		scheduleService = ServiceFactory.createWorkScheduleService();
		
		/**
		 * Seeding do banco...
		 */
//		String description, String entryHour, String departureTime
		WorkSchedule ws1 = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		WorkSchedule ws2 = new WorkSchedule("Segundo Turno", "14:00", "18:00");
		WorkSchedule ws3 = new WorkSchedule("Segundo Turno", "18:00", "02:00");
		
//		String entryHour, String departureTime, WorkSchedule workSchedule
		HourMarker hm1 = new HourMarker("08:00", "12:00", ws1);
		HourMarker hm2 = new HourMarker("13:00", "17:00", ws2);
		HourMarker hm3 = new HourMarker("19:00", "05:00", ws3);
		
		scheduleService.insertAll(List.of(ws1, ws2, ws3));
		hourService.insertAll(List.of(hm1, hm2, hm3));
		
		
		
	}
	

	/**
	 * INSERTS
	 */
	public void insertWithNonException() {
		WorkSchedule wsTemp = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		HourMarker hmTemp = new HourMarker("08:00", "12:00", wsTemp);
		SummaryOfHoursWorked shw = new SummaryOfHoursWorked();
	}
	
	public void insertWithException() {
		
	}
	
	public void insertTwoOrMoreObjects() {
		
	}
	
	public void insertOrUpdateWithNonException() {
		
	}
	
	public void insertOrUpdateWithException() {
		
	}
	
	public void insertOrUpdateTwoOrMoreObjects() {
		
	}
	
	public void insertWithExceptionWhenHasMoreThanThree() {
		
	}
	
	/**
	 * DELETES
	 */
	
	public void deleteWithNonException() {
		
	}
	
	public void deleteWithException() {
		
	}
	
	public void deleteTwoOrMoreObjects() {
		
	}
	
	/**
	 * UPDATES
	 */
	
	public void updateWithNonException() {
		
	}
	
	public void updateWithException() {
		
	}
	
	public void updateTwoOrMoreObjects() {
		
	}
	
	/**
	 * SEARCH
	 */
	
	public void findAllWithNonException() {
		
	}
	
	public void findAllWithException() {
		
	}
	
	public void findByIdWithNonException() {
		
	}
	
	public void findByIdWithException() {
		
	}
	
	public void findAllLike() {
		
	}
	
	public void exists() {
		
	}
	
	public void nonExists() {
		
	}
}
