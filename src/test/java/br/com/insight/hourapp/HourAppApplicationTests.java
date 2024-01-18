package br.com.insight.hourapp;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import br.com.insight.hourapp.entities.HourMarker;
import br.com.insight.hourapp.entities.WorkSchedule;
import br.com.insight.hourapp.entities.interfaces.BaseEntity;
import br.com.insight.hourapp.services.factory.ServiceFactory;
import br.com.insight.hourapp.services.interfaces.HourMarkerService;
import br.com.insight.hourapp.services.interfaces.SummaryOfHoursWorkedService;
import br.com.insight.hourapp.services.interfaces.WorkScheduleService;
import junit.framework.TestCase;

public class HourAppApplicationTests extends TestCase {

	private HourMarkerService hourService = null;
	private WorkScheduleService scheduleService = null;
	private SummaryOfHoursWorkedService summaryService = null;
	
	@BeforeEach
	protected void setUp() throws Exception {
		hourService = ServiceFactory.createHourMarkerService();
		scheduleService = ServiceFactory.createWorkScheduleService();
		summaryService = ServiceFactory.createSummaryOfHoursWorkedService();
		
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
	
	@Test
	public void test() {
		
		List<WorkSchedule> lstSchedule = null;
		lstSchedule = scheduleService.findAll(new WorkSchedule());
		lstSchedule.forEach(System.out::println);
		
		List<HourMarker> lstHour = null;
		lstHour = hourService.findAll(new HourMarker());
		lstHour.forEach(System.out::println);
		
		assertNotNull("Não foi possível carregar a lista de horários de trabalho", lstSchedule);
		
		assertNotNull("Não foi possível carregar a lista de marcações", lstHour);
	}

}
