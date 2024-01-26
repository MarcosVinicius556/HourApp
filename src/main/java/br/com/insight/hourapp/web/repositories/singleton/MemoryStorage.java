package br.com.insight.hourapp.web.repositories.singleton;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MemoryStorage {

	/**
	 * Armazena todos os dados da aplicação no modelo Chave | Valor
	 * Sendo a chave o nome da tabela (Fornecido utilizando .getClassName()) e valor os registro contidos 
	 * na memória...
	 * 
	 * Utilizado set, pois assim fica mais fácil tratar chaves duplicadas....
	 */
	private static Map<String, Set<Object>> MEMMORY_DATABASE;
	
	public MemoryStorage() {
		if(MEMMORY_DATABASE == null) {
			MEMMORY_DATABASE = new HashMap<>();
		}
	}
	
	public void createNewTable(String className) {
		Set<Object> newList = new HashSet<>();
		MEMMORY_DATABASE.put(className, newList);
	}
	
	public void insertIntoMemory(String className, Object data) {
		if(MemoryStorage.MEMMORY_DATABASE.get(className) == null)
			createNewTable(className);
		
		MemoryStorage.MEMMORY_DATABASE.get(className).add(data);
	}
	
	public void removeFromMemory(String className, Object data) throws Exception {
		if(MemoryStorage.MEMMORY_DATABASE.get(className) == null) {
			System.out.println("Nenhuma 'Tabela' com o nome " + className + " encontrada! Criando tabela...");
			createNewTable(className);
		}
		
		MemoryStorage.MEMMORY_DATABASE.get(className).remove(data);
	}
	
	public void removeAllFromMemory(String className) throws Exception {
		MemoryStorage.MEMMORY_DATABASE.remove(className);
	}
	
	public List<Object> readAllIntoMemory(String className) {
		if(MemoryStorage.MEMMORY_DATABASE.get(className) == null) {
			System.out.println("Nenhuma 'Tabela' com o nome " + className + " encontrada! Criando tabela...");
			createNewTable(className);
		}
		
		return MemoryStorage.MEMMORY_DATABASE.get(className).stream().collect(Collectors.toList());
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
