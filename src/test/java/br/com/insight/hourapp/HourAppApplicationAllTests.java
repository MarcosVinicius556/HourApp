package br.com.insight.hourapp;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.insight.hourapp.web.entities.HourMarker;
import br.com.insight.hourapp.web.entities.SummaryHour;
import br.com.insight.hourapp.web.entities.WorkSchedule;
import br.com.insight.hourapp.web.services.factory.ServiceFactory;
import br.com.insight.hourapp.web.services.interfaces.HourMarkerService;
import br.com.insight.hourapp.web.services.interfaces.SummaryHoursService;
import br.com.insight.hourapp.web.services.interfaces.WorkScheduleService;
import junit.framework.TestCase;
/**
 * Classe com testes unitários e de integração referentes a classe SummaryOfHoursWorked"
 */
public class HourAppApplicationAllTests extends TestCase {

	private HourMarkerService markerService = null;
	private WorkScheduleService scheduleService = null;
	private SummaryHoursService summaryService = null;
	
	@BeforeEach
	protected void setUp() throws Exception {
		summaryService = ServiceFactory.createSummaryHoursService();
		markerService = ServiceFactory.createHourMarkerService();
		scheduleService = ServiceFactory.createWorkScheduleService();
		
	}
	
	/**
	 * INSERTS
	 */
	@Test
	public void insertAndFindWithNonExceptionAndRemoveAll() {
		WorkSchedule wsTemp = new WorkSchedule("08:00", "12:00");
		HourMarker hmTemp= new HourMarker("08:00", "12:00");
		SummaryHour shw = new SummaryHour();
		SummaryHour find = null;
		
		scheduleService.insert(wsTemp);
		markerService.insert(hmTemp);
		
		shw.setTotalHours("01:01");
		
		summaryService.insert(shw);
		
		find = summaryService.findById(shw);
		
		assertNotNull("Não foi encontrado nada!", find);
		assertEquals("Objetos não são iguais", shw, find);
		
		summaryService.remove(shw);
		markerService.remove(hmTemp);
		scheduleService.remove(wsTemp);
	}
	
	@Test
	public void updateWithNonExceptionAndRemoveAll() {
		WorkSchedule wsTemp = new WorkSchedule("08:00", "12:00");
		HourMarker hmTemp= new HourMarker("08:00", "12:00");
		SummaryHour shw = new SummaryHour();
		SummaryHour find = null;
		
		scheduleService.insert(wsTemp);
		markerService.insert(hmTemp);
		
		shw.setTotalHours("01:01");
		
		summaryService.insert(shw);
		find = summaryService.findById(shw);
		
		assertNotNull("Não foi encontrado nada!", find);
		assertEquals("Objetos não são iguais", shw, find);
		
		shw.setTotalHours("20:00");
		summaryService.update(shw);
		
		find = summaryService.findById(shw);
		
		assertEquals("Objeto não foi atualizado corretamente", find, shw);
		
		summaryService.remove(shw);
		markerService.remove(hmTemp);
		scheduleService.remove(wsTemp);
	}
	
	/**
	 * SEARCH
	 */
	
	@Test
	public void findAllWithNonExceptionAndRemoveAll() {
		WorkSchedule wsTemp_1 = new WorkSchedule("08:00", "12:00");
		WorkSchedule wsTemp_2 = new WorkSchedule("12:00", "18:00");
		HourMarker hmTemp_1 = new HourMarker("08:00", "12:00");
		HourMarker hmTemp_2 = new HourMarker("12:00", "18:00");
		
		SummaryHour obj_1 = new SummaryHour();
		SummaryHour obj_2 = new SummaryHour();
		
		obj_1.setTotalHours("01:01");
		obj_2.setTotalHours("02:02");
		
		List<WorkSchedule> schedules = null;
		List<HourMarker> markers = null;
		List<SummaryHour> summaries = null;
		
		scheduleService.insert(wsTemp_1);
		scheduleService.insert(wsTemp_2);
		
		markerService.insert(hmTemp_1);
		markerService.insert(hmTemp_2);
		
		summaryService.insert(obj_1);
		summaryService.insert(obj_2);
		
		schedules = scheduleService.findAll(new WorkSchedule());
		markers = markerService.findAll(new HourMarker());
		summaries = summaryService.findAll(new SummaryHour());
		
		assertNotNull(schedules);
		assertTrue(schedules.size() > 0);
		
		assertNotNull(markers);
		assertTrue(markers.size() > 0);
		
		assertNotNull(summaries);
		assertTrue(summaries.size() > 0);
		
		schedules.forEach(System.out::println);
		markers.forEach(System.out::println);
		summaries.forEach(System.out::println);
		
		scheduleService.remove(wsTemp_1);
		scheduleService.remove(wsTemp_2);
		
		markerService.remove(hmTemp_1);
		markerService.remove(hmTemp_2);
		
		summaryService.remove(obj_1);
		summaryService.remove(obj_2);
		
	}
	
	
	
}
