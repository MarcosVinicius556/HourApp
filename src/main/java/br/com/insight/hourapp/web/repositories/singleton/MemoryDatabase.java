package br.com.insight.hourapp.web.repositories.singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.insight.hourapp.web.entities.HourMarker;
import br.com.insight.hourapp.web.entities.SummaryHour;
import br.com.insight.hourapp.web.entities.WorkSchedule;
import br.com.insight.hourapp.web.entities.enums.HourType;

public class MemoryDatabase {

	/**
	 * Armazena todos os dados da aplicação no modelo Chave | Valor
	 * Sendo a chave o nome da tabela (Fornecido utilizando .getClassName()) e valor os registro contidos 
	 * na memória...
	 */
	private static Map<String, List<Object>> MEMMORY_DATABASE;
	
	public MemoryDatabase() {
		if(MEMMORY_DATABASE == null) {
			MEMMORY_DATABASE = new HashMap<>();
		}
	}
	
	public void createNewTable(String className) {
		List<Object> newList = new ArrayList<>();
		MEMMORY_DATABASE.put(className, newList);
	}
	
	public void insertIntoMemory(String className, Object data) {
		if(MemoryDatabase.MEMMORY_DATABASE.get(className) == null)
			createNewTable(className);
		
		MemoryDatabase.MEMMORY_DATABASE.get(className).add(data);
	}
	
	public void removeIntoMemory(String className, Object data) throws Exception {
		if(MemoryDatabase.MEMMORY_DATABASE.get(className) == null)
			throw new RuntimeException("Nenhuma 'Tabele' com o nome " + className + " encontrada!");
		
		MemoryDatabase.MEMMORY_DATABASE.get(className).remove(data);
	}
	
	public void upateIntoMemory(String className, Object data) {
		if(MemoryDatabase.MEMMORY_DATABASE.get(className) == null)
			throw new RuntimeException("Nenhuma 'Tabele' com o nome " + className + " encontrada!");
		
		MemoryDatabase.MEMMORY_DATABASE.get(className).remove(data);
	}
	
	public List<Object> readAllIntoMemory(String className) {
		if(MemoryDatabase.MEMMORY_DATABASE.get(className) == null)
			throw new RuntimeException("Nenhuma 'Tabele' com o nome " + className + " encontrada!");
		
		return MemoryDatabase.MEMMORY_DATABASE.get(className);
	}
	
	/**
	 * TESTES............
	 */
//	public static void main(String[] args) {
//		MemoryDatabase database = new MemoryDatabase();
//		
//		WorkSchedule ws = new WorkSchedule();
//		
//		long scheduleId = 1L;
//		String entryHour = "08:00";
//		String departureTime = "12:00";
//		
//		ws.setScheduleId(scheduleId);
//		ws.setEntryHour(entryHour);
//		ws.setDepartureTime(departureTime);
//		
//		database.insertIntoMemory(ws.getClass().getName(), ws);
//		
//		HourMarker hm = new HourMarker();
//		
//		long markerId = 1L;
//		String markerEntryHour = "07:00";
//		String markerDepartureTime = "11:00";
//		
//		hm.setMarkerId(markerId);
//		hm.setEntryHour(markerEntryHour);
//		hm.setDepartureTime(markerDepartureTime);
//		
//		database.insertIntoMemory(hm.getClass().getName(), hm);
//		
//		SummaryHour sm = new SummaryHour();
//		
//		long summaryId = 1L;
//		long summaryMarkerId = 1L;
//		long summaryScheduleId = 1L;
//		
//		sm.setSummaryId(summaryId);
//		sm.setScheduleId(summaryScheduleId);
//		sm.setMarkerId(summaryMarkerId);
//		sm.setHourType(HourType.OVERTIME.getCod());
//		sm.setTotalHours("07:00 08:00");
//		
//		database.insertIntoMemory(sm.getClass().getName(), sm);
//		
//		List<Object> schedules = database.readAllIntoMemory(ws.getClass().getName());
//		List<Object> markers = database.readAllIntoMemory(hm.getClass().getName());
//		List<Object> summaries = database.readAllIntoMemory(sm.getClass().getName());
//		
//		schedules.forEach(System.out::println);
//		markers.forEach(System.out::println);
//		summaries.forEach(System.out::println);
//		
//		
//	}
	
	
}
