package br.com.insight.hourapp;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.insight.hourapp.entities.HourMarker;
import br.com.insight.hourapp.entities.SummaryHour;
import br.com.insight.hourapp.entities.WorkSchedule;
import br.com.insight.hourapp.services.factory.ServiceFactory;
import br.com.insight.hourapp.services.interfaces.HourMarkerService;
import br.com.insight.hourapp.services.interfaces.SummaryOfHoursWorkedService;
import br.com.insight.hourapp.services.interfaces.WorkScheduleService;
import junit.framework.TestCase;
/**
 * Classe com testes unitários e de integração referentes a classe SummaryOfHoursWorked"
 */
public class HourAppApplicationAllTests extends TestCase {

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
	public void insertAndFindWithNonExceptionAndRemoveAll() {
		WorkSchedule wsTemp = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		HourMarker hmTemp= new HourMarker("08:00", "12:00", wsTemp);
		SummaryHour shw = new SummaryHour();
		SummaryHour find = null;
		
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
		
		summaryService.remove(shw);
		hourService.remove(hmTemp);
		scheduleService.remove(wsTemp);
	}
	
	@Test
	public void insertWithException() {
		boolean error = false;
		WorkSchedule wsTemp = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		HourMarker hmTemp= new HourMarker("08:00", "12:00", wsTemp);
		SummaryHour obj = new SummaryHour();
		SummaryHour find = null;
		
		obj.setWorkSchedule(wsTemp);
		obj.setHourMarker(hmTemp);
		obj.setCreated(new GregorianCalendar());
		obj.setTotalHours("01:01");

		try {
			hourService.insert(hmTemp);			
			summaryService.insert(obj);
		} catch (Exception e) {
			error = true;
		}
		
		assertTrue(error);
		
		find = summaryService.findById(obj);
		
		assertNull("Não foi encontrado nada!", find);
	}
	
	@Test
	public void insertTwoOrMoreObjectsAndRemoveAll() {
		WorkSchedule wsTemp_1 = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		WorkSchedule wsTemp_2 = new WorkSchedule("Segundo Turno", "12:00", "18:00");
		HourMarker hmTemp_1 = new HourMarker("08:00", "12:00", wsTemp_1);
		HourMarker hmTemp_2 = new HourMarker("12:00", "18:00", wsTemp_2);
		
		SummaryHour obj_1 = new SummaryHour();
		SummaryHour obj_2 = new SummaryHour();
		
		SummaryHour find_1 = null;
		SummaryHour find_2 = null;
		
		scheduleService.insertAll(Arrays.asList(wsTemp_1, wsTemp_2));
		hourService.insertAll(Arrays.asList(hmTemp_1, hmTemp_2));
		
		obj_1.setWorkSchedule(wsTemp_1);
		obj_1.setHourMarker(hmTemp_1);
		obj_1.setCreated(new GregorianCalendar());
		obj_1.setTotalHours("01:01");
		
		obj_2.setWorkSchedule(wsTemp_2);
		obj_2.setHourMarker(hmTemp_2);
		obj_2.setCreated(new GregorianCalendar());
		obj_2.setTotalHours("02:02");
		
		summaryService.insertAll(Arrays.asList(obj_1, obj_2));
		
		find_1 = summaryService.findById(obj_1);
		
		find_2 = summaryService.findById(obj_2);
		
		assertNotNull("Não foi encontrado nada!", find_1);
		assertNotNull("Não foi encontrado nada!", find_2);
		
		assertEquals("Objetos não são iguais", obj_1, find_1);
		assertEquals("Objetos não são iguais", obj_2, find_2);
		
		summaryService.removeAll(Arrays.asList(obj_1, obj_2));
		hourService.removeAll(Arrays.asList(hmTemp_1, hmTemp_2));
		scheduleService.removeAll(Arrays.asList(wsTemp_1, wsTemp_2));
	}
	
	@Test
	public void insertOrUpdateWithNonExceptionAndRemoveAll() {
		WorkSchedule wsTemp = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		HourMarker hmTemp= new HourMarker("08:00", "12:00", wsTemp);
		SummaryHour shw = new SummaryHour();
		SummaryHour find = null;
		
		scheduleService.insert(wsTemp);
		hourService.insert(hmTemp);
		
		shw.setWorkSchedule(wsTemp);
		shw.setHourMarker(hmTemp);
		shw.setCreated(new GregorianCalendar());
		shw.setTotalHours("01:01");
		
		summaryService.insertOrUpdate(shw);
		find = summaryService.findById(shw);
		
		assertNotNull("Não foi encontrado nada!", find);
		assertEquals("Objetos não são iguais", shw, find);
		
		shw.setTotalHours("20:00");
		summaryService.insertOrUpdate(shw);
		
		find = summaryService.findById(shw);
		
		assertEquals("Objeto não foi atualizado corretamente", find, shw);
		
		summaryService.remove(shw);
		hourService.remove(hmTemp);
		scheduleService.remove(wsTemp);
	}
	
	@Test
	public void updateWithNonExceptionAndRemoveAll() {
		WorkSchedule wsTemp = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		HourMarker hmTemp= new HourMarker("08:00", "12:00", wsTemp);
		SummaryHour shw = new SummaryHour();
		SummaryHour find = null;
		
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
		
		shw.setTotalHours("20:00");
		summaryService.update(shw);
		
		find = summaryService.findById(shw);
		
		assertEquals("Objeto não foi atualizado corretamente", find, shw);
		
		summaryService.remove(shw);
		hourService.remove(hmTemp);
		scheduleService.remove(wsTemp);
	}
	
	@Test
	public void insertOrUpdateWithException() {
		boolean error = false;
		WorkSchedule wsTemp = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		HourMarker hmTemp= new HourMarker("08:00", "12:00", wsTemp);
		SummaryHour obj = new SummaryHour();
		SummaryHour find = null;
		
		obj.setWorkSchedule(wsTemp);
		obj.setHourMarker(hmTemp);
		obj.setCreated(new GregorianCalendar());
		obj.setTotalHours("01:01");

		try {
			hourService.insertOrUpdate(hmTemp);			
			summaryService.insertOrUpdate(obj);
		} catch (Exception e) {
			error = true;
		}
		
		assertTrue(error);
		
		find = summaryService.findById(obj);
		
		assertNull("Não foi encontrado nada!", find);
	}
	
	@Test
	public void insertOrUpdateTwoOrMoreObjectsAndRemoveAll() {
		WorkSchedule wsTemp_1 = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		WorkSchedule wsTemp_2 = new WorkSchedule("Segundo Turno", "12:00", "18:00");
		HourMarker hmTemp_1 = new HourMarker("08:00", "12:00", wsTemp_1);
		HourMarker hmTemp_2 = new HourMarker("12:00", "18:00", wsTemp_2);
		
		SummaryHour obj_1 = new SummaryHour();
		SummaryHour obj_2 = new SummaryHour();
		
		SummaryHour find_1 = null;
		SummaryHour find_2 = null;
		
		scheduleService.insertOrUpdateAll(Arrays.asList(wsTemp_1, wsTemp_2));
		hourService.insertOrUpdateAll(Arrays.asList(hmTemp_1, hmTemp_2));
		
		obj_1.setWorkSchedule(wsTemp_1);
		obj_1.setHourMarker(hmTemp_1);
		obj_1.setCreated(new GregorianCalendar());
		obj_1.setTotalHours("01:01");
		
		obj_2.setWorkSchedule(wsTemp_2);
		obj_2.setHourMarker(hmTemp_2);
		obj_2.setCreated(new GregorianCalendar());
		obj_2.setTotalHours("02:02");
		
		summaryService.insertOrUpdateAll(Arrays.asList(obj_1, obj_2));
		
		find_1 = summaryService.findById(obj_1);
		
		find_2 = summaryService.findById(obj_2);
		
		assertNotNull("Não foi encontrado nada!", find_1);
		assertNotNull("Não foi encontrado nada!", find_2);
		
		assertEquals("Objetos não são iguais", obj_1, find_1);
		assertEquals("Objetos não são iguais", obj_2, find_2);
		
		summaryService.removeAll(Arrays.asList(obj_1, obj_2));
		hourService.removeAll(Arrays.asList(hmTemp_1, hmTemp_2));
		scheduleService.removeAll(Arrays.asList(wsTemp_1, wsTemp_2));
	}
	
	
	@Test
	public void updateTwoOrMoreObjectsAndRemoveAll() {
		WorkSchedule wsTemp_1 = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		WorkSchedule wsTemp_2 = new WorkSchedule("Segundo Turno", "12:00", "18:00");
		HourMarker hmTemp_1 = new HourMarker("08:00", "12:00", wsTemp_1);
		HourMarker hmTemp_2 = new HourMarker("12:00", "18:00", wsTemp_2);
		
		SummaryHour obj_1 = new SummaryHour();
		SummaryHour obj_2 = new SummaryHour();
		
		SummaryHour find_1 = null;
		SummaryHour find_2 = null;
		
		scheduleService.insertOrUpdateAll(Arrays.asList(wsTemp_1, wsTemp_2));
		hourService.insertOrUpdateAll(Arrays.asList(hmTemp_1, hmTemp_2));
		
		obj_1.setWorkSchedule(wsTemp_1);
		obj_1.setHourMarker(hmTemp_1);
		obj_1.setCreated(new GregorianCalendar());
		obj_1.setTotalHours("01:01");
		
		obj_2.setWorkSchedule(wsTemp_2);
		obj_2.setHourMarker(hmTemp_2);
		obj_2.setCreated(new GregorianCalendar());
		obj_2.setTotalHours("02:02");
		
		summaryService.insertAll(Arrays.asList(obj_1, obj_2));
		
		find_1 = summaryService.findById(obj_1);
		find_2 = summaryService.findById(obj_2);
		
		obj_1.setTotalHours("02:02");
		obj_2.setTotalHours("03:03");
		
		summaryService.updateAll(Arrays.asList(find_1, find_2));
		
		find_1 = summaryService.findById(obj_1);
		find_2 = summaryService.findById(obj_2);
		
		assertNotNull("Não foi encontrado nada!", find_1);
		assertNotNull("Não foi encontrado nada!", find_2);
		
		assertEquals("Objetos não são iguais", obj_1, find_1);
		assertEquals("Objetos não são iguais", obj_2, find_2);
		
		summaryService.removeAll(Arrays.asList(obj_1, obj_2));
		hourService.removeAll(Arrays.asList(hmTemp_1, hmTemp_2));
		scheduleService.removeAll(Arrays.asList(wsTemp_1, wsTemp_2));
	}
	
	@Test
	public void deleteWithException() {
		SummaryHour obj_1 = new SummaryHour();
		boolean error = false;
		try {
			summaryService.remove(obj_1);
		} catch (Exception e) {
			error = true;
		}
		
		assertTrue(error);
	}
	/**
	 * SEARCH
	 */
	
	@Test
	public void findAllWithNonExceptionAndRemoveAll() {
		WorkSchedule wsTemp_1 = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		WorkSchedule wsTemp_2 = new WorkSchedule("Segundo Turno", "12:00", "18:00");
		HourMarker hmTemp_1 = new HourMarker("08:00", "12:00", wsTemp_1);
		HourMarker hmTemp_2 = new HourMarker("12:00", "18:00", wsTemp_2);
		
		SummaryHour obj_1 = new SummaryHour();
		SummaryHour obj_2 = new SummaryHour();
		
		List<SummaryHour> lst = null;
		
		scheduleService.insertAll(Arrays.asList(wsTemp_1, wsTemp_2));
		hourService.insertAll(Arrays.asList(hmTemp_1, hmTemp_2));
		
		obj_1.setWorkSchedule(wsTemp_1);
		obj_1.setHourMarker(hmTemp_1);
		obj_1.setCreated(new GregorianCalendar());
		obj_1.setTotalHours("01:01");
		
		obj_2.setWorkSchedule(wsTemp_2);
		obj_2.setHourMarker(hmTemp_2);
		obj_2.setCreated(new GregorianCalendar());
		obj_2.setTotalHours("02:02");
		
		summaryService.insertAll(Arrays.asList(obj_1, obj_2));
		
		lst = summaryService.findAll(new SummaryHour());
		
		assertNotNull(lst);
		assertTrue(lst.size() > 0);
		
		summaryService.removeAll(Arrays.asList(obj_1, obj_2));
		hourService.removeAll(Arrays.asList(hmTemp_1, hmTemp_2));
		scheduleService.removeAll(Arrays.asList(wsTemp_1, wsTemp_2));
	}
	
	@Test
	public void findAllLikeAndRemoveAll() {
		WorkSchedule wsTemp_1 = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		WorkSchedule wsTemp_2 = new WorkSchedule("Segundo Turno", "12:00", "18:00");
		HourMarker hmTemp_1 = new HourMarker("08:00", "12:00", wsTemp_1);
		HourMarker hmTemp_2 = new HourMarker("12:00", "18:00", wsTemp_2);
		
		SummaryHour obj_1 = new SummaryHour();
		SummaryHour obj_2 = new SummaryHour();
		
		List<SummaryHour> lst = null;
		
		scheduleService.insertAll(Arrays.asList(wsTemp_1, wsTemp_2));
		hourService.insertAll(Arrays.asList(hmTemp_1, hmTemp_2));
		
		obj_1.setWorkSchedule(wsTemp_1);
		obj_1.setHourMarker(hmTemp_1);
		obj_1.setCreated(new GregorianCalendar());
		obj_1.setTotalHours("01:01");
		
		obj_2.setWorkSchedule(wsTemp_2);
		obj_2.setHourMarker(hmTemp_2);
		obj_2.setCreated(new GregorianCalendar());
		obj_2.setTotalHours("02:02");
		
		summaryService.insertAll(Arrays.asList(obj_1, obj_2));
		
		lst = summaryService.findAllLike(new SummaryHour(), "totalHours", "01:01");
		
		assertNotNull(lst);
		assertTrue(lst.size() > 0);
		assertEquals(lst.get(0), obj_1);
		
		summaryService.removeAll(Arrays.asList(obj_1, obj_2));
		hourService.removeAll(Arrays.asList(hmTemp_1, hmTemp_2));
		scheduleService.removeAll(Arrays.asList(wsTemp_1, wsTemp_2));
	}
	
	@Test
	public void exists() {
		WorkSchedule wsTemp_1 = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		HourMarker hmTemp_1 = new HourMarker("08:00", "12:00", wsTemp_1);
		
		SummaryHour obj_1 = new SummaryHour();
		
		
		scheduleService.insert(wsTemp_1);
		hourService.insert(hmTemp_1);
		
		obj_1.setWorkSchedule(wsTemp_1);
		obj_1.setHourMarker(hmTemp_1);
		obj_1.setCreated(new GregorianCalendar());
		obj_1.setTotalHours("01:01");
		
		summaryService.insert(obj_1);
		
		assertTrue(summaryService.exists(obj_1));
		
		summaryService.remove(obj_1);
		hourService.remove(hmTemp_1);
		scheduleService.remove(wsTemp_1);
	}
	
	@Test
	public void nonExists() {
		WorkSchedule wsTemp_1 = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		HourMarker hmTemp_1 = new HourMarker("08:00", "12:00", wsTemp_1);
		SummaryHour obj_1 = new SummaryHour();
		
		obj_1.setWorkSchedule(wsTemp_1);
		obj_1.setHourMarker(hmTemp_1);
		obj_1.setCreated(new GregorianCalendar());
		obj_1.setTotalHours("01:01");
		
		
		assertFalse(summaryService.exists(obj_1));
	}
	
	@Test
	public void dropAllFromHourDataBaseWithHQLQuery() {
		String hqlQuery = "DELETE FROM HourMarker hm";
		hourService.executeQuery(hqlQuery);
	}

	@Test
	public void dropAllFromHourDataBaseWithNativeQuery() {
		String hqlQuery = "DELETE FROM hour_marker hm";
		hourService.executeNativeQuery(hqlQuery);
	}
	
	@Test
	public void testLoadByHQLQuery() {
		WorkSchedule wsTemp_1 = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		WorkSchedule wsTemp_2 = new WorkSchedule("Segundo Turno", "12:00", "18:00");
		
		scheduleService.insertAll(Arrays.asList(wsTemp_1, wsTemp_2));
		
		List<Object> workSchedules = scheduleService.loadByQuery("SELECT c FROM WorkSchedule c");
		
		assertNotNull(workSchedules);
		assertTrue(workSchedules.size() > 0);
	}
	
	@Test
	public void testLoadByNativeQuery() {
		WorkSchedule wsTemp_1 = new WorkSchedule("Primeiro Turno", "08:00", "12:00");
		WorkSchedule wsTemp_2 = new WorkSchedule("Segundo Turno", "12:00", "18:00");
		
		scheduleService.insertAll(Arrays.asList(wsTemp_1, wsTemp_2));
		
		List<Object> workSchedules = scheduleService.loadByNativeQuery("SELECT c.* FROM work_schedule c");
		
		assertNotNull(workSchedules);
		assertTrue(workSchedules.size() > 0);
	}
}
