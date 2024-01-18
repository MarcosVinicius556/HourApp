package br.com.insight.hourapp;

import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.insight.hourapp.entities.HourMarker;
import br.com.insight.hourapp.entities.SummaryHours;
import br.com.insight.hourapp.entities.WorkSchedule;
import br.com.insight.hourapp.services.factory.ServiceFactory;
import br.com.insight.hourapp.services.interfaces.HourMarkerService;
import br.com.insight.hourapp.services.interfaces.SummaryOfHoursWorkedService;
import br.com.insight.hourapp.services.interfaces.WorkScheduleService;
import junit.framework.TestCase;
/**
 * Classe com testes unitários e de integração referentes a classe SummaryOfHoursWorked"
 */
public class SummaryOfHoursWorkedTests extends TestCase {

	private HourMarkerService hourService = null;
	private WorkScheduleService scheduleService = null;
	private SummaryOfHoursWorkedService summaryService = null;
	
	@BeforeEach
	protected void setUp() throws Exception {
		summaryService = ServiceFactory.createSummaryOfHoursWorkedService();
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
	@Test
	public void insertAndFindWithNonException() {
		WorkSchedule wsTemp = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		HourMarker hmTemp= new HourMarker("08:00", "12:00", wsTemp);
		SummaryHours shw = new SummaryHours();
		SummaryHours find = null;
		
		scheduleService.insert(wsTemp);
		hourService.insert(hmTemp);
		
		shw.setWorkSchedule(wsTemp);
		shw.setHourMarker(hmTemp);
		shw.setCreated(new GregorianCalendar());
		shw.setTotalHours("01:01");
		
		summaryService.insert(shw);
		
		find = summaryService.findById(shw);
		
		assertNotNull("Não foi encontrado nada!", find);
		assertEquals("Objetos não são iguais", shw, find);
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
