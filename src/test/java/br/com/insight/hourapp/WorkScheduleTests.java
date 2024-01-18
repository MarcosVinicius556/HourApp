package br.com.insight.hourapp;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import br.com.insight.hourapp.entities.HourMarker;
import br.com.insight.hourapp.entities.WorkSchedule;
import br.com.insight.hourapp.services.factory.ServiceFactory;
import br.com.insight.hourapp.services.interfaces.HourMarkerService;
import br.com.insight.hourapp.services.interfaces.WorkScheduleService;
import junit.framework.TestCase;

public class WorkScheduleTests extends TestCase {

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
	
	@Test
	public void test() {
		
		List<WorkSchedule> lstSchedule = null;
		lstSchedule = scheduleService.findAll(new WorkSchedule());
		lstSchedule.forEach(System.out::println);
		
		assertNotNull("Não foi possível carregar a lista de horários de trabalho", lstSchedule);
	}

}
