package br.com.insight.hourapp.web.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import br.com.insight.hourapp.web.entities.HourMarker;
import br.com.insight.hourapp.web.entities.SummaryHour;
import br.com.insight.hourapp.web.entities.WorkSchedule;
import br.com.insight.hourapp.web.entities.dto.SummaryHourCalculateDTO;
import br.com.insight.hourapp.web.entities.dto.SummaryHourDTO;
import br.com.insight.hourapp.web.entities.enums.HourType;
import br.com.insight.hourapp.web.repositories.factory.RepositoryFactory;
import br.com.insight.hourapp.web.repositories.interfaces.BaseRepository;
import br.com.insight.hourapp.web.repositories.interfaces.SummaryHoursRepository;
import br.com.insight.hourapp.web.resources.enums.CalculateMode;
import br.com.insight.hourapp.web.services.factory.ServiceFactory;
import br.com.insight.hourapp.web.services.interfaces.HourMarkerService;
import br.com.insight.hourapp.web.services.interfaces.SummaryHoursService;
import br.com.insight.hourapp.web.services.interfaces.WorkScheduleService;

public class SummaryHoursServiceImpl implements SummaryHoursService {

private static final Logger logger = Logger.getLogger(HourMarker.class);
	
	private SummaryHoursRepository repository;
	private WorkScheduleService scheduleService;
	private HourMarkerService markerService;
	
	public SummaryHoursServiceImpl() {
		logger.info("Inicializando os repositórios da " + HourMarkerService.class.getName());
		if(repository == null) 
			repository = RepositoryFactory.createSummaryOfHoursWorkedRepository();
		if(scheduleService == null)
			scheduleService = ServiceFactory.createWorkScheduleService();
		if(markerService == null)
			markerService = ServiceFactory.createHourMarkerService();
	}
	
	@Override
	public BaseRepository getRepository() {
		return repository;
	}

	@Override
	public List<SummaryHourDTO> calculateTotalHours(SummaryHourCalculateDTO dto, CalculateMode calcMode) {
		List<WorkSchedule> schedules = new ArrayList<>();
		List<HourMarker> markers = new ArrayList<>();
 		List<SummaryHour> newSummaries = new ArrayList<>();
 		List<SummaryHourDTO> newSummariesDTO = new ArrayList<>();
		
		try {
			
			dto.getScheduleIds().forEach(id -> {
				WorkSchedule w = new WorkSchedule();
				w.setScheduleId(Long.parseLong(id));
				schedules.add(scheduleService.findById(w));
			});
			
			dto.getMarkerIds().forEach(id -> {
				HourMarker h = new HourMarker();
				h.setMarkerId(Long.parseLong(id));
				markers.add(markerService.findById(h));
			});
			
			switch (calcMode) {
				case MARKER_LESS_SCHEDULE:
					//Converte para uma lista de DTO's
					newSummaries = calculateByMarker(markers, schedules);
					break;
				case SCHEDULE_LESS_MARKER:
					newSummaries = calculateBySchedule(schedules, markers);
					break;
			}		
			
			newSummaries.forEach(s -> this.insert(s));  
			newSummariesDTO = newSummaries.stream().map(s -> new SummaryHourDTO(s)).collect(Collectors.toList());
			
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		
		return newSummariesDTO; 
	}
	
	/**************************************************************
	 * @apiNote Faz o cálculo das horas extras e de atraso, fazendo a operação de (Horários de Trabalho - Marcações)
	 * @param schedules
	 * @param markers
	 * @return
	 *************************************************************/
	private List<SummaryHour> calculateBySchedule(List<WorkSchedule> schedules, List<HourMarker> markers) {
		List<SummaryHour> newSummaries = new ArrayList<>();
		if(schedules.size() >= markers.size()) {
			for(WorkSchedule schedule : schedules) {
				for(HourMarker marker : markers) {
					newSummaries.addAll(processByScheduleDiff(schedule, marker));
				}
			}
		} else {
			for(HourMarker marker : markers) {
				for(WorkSchedule schedule : schedules) {
					newSummaries.addAll(processByScheduleDiff(schedule, marker));
				}
			}
		}
		return newSummaries;
	}
	
	private List<SummaryHour> processByScheduleDiff(WorkSchedule schedule, HourMarker marker) {
		List<SummaryHour> newSummaries = new ArrayList<>();
		String diffHour = "";
		HourType hourType = null;

		String markerEntryHour = marker.getEntryHour();
		String scheduleEntryHour = schedule.getEntryHour();
		
		String markerDepartureTime = marker.getDepartureTime();
		String scheduleDepartureTime = schedule.getDepartureTime();
		
		/**
		 * Se houver atraso/extra, gera novo registro de summaryHour
		 */
		Object[] entryDiff = calculateDiff(scheduleEntryHour, markerEntryHour, true);
		diffHour = (String) entryDiff[0];
		hourType = (HourType) entryDiff[1];
		if(hourType != null) {
			SummaryHour summary = new SummaryHour();
			summary.setHourType(hourType.getCod());
			summary.setTotalHours(diffHour);
			
			newSummaries.add(summary);
		}
		
		Object[] departureDiff = calculateDiff(scheduleDepartureTime, markerDepartureTime, false);
		diffHour = (String) departureDiff[0];
		hourType = (HourType) departureDiff[1];
		if(hourType != null) {
			SummaryHour summary = new SummaryHour();
			summary.setHourType(hourType.getCod());
			summary.setTotalHours(diffHour);
			
			newSummaries.add(summary);
		}
		
		return newSummaries;
	}
	
	/************************************************************
	 * @apiNote Faz o cálculo das horas extras e de atraso, fazendo a operação de (Marcações - Horários de Trabalho)
	 * @param schedules
	 * @param markers
	 * @return
	 ************************************************************/

	private List<SummaryHour> calculateByMarker(List<HourMarker> markers, List<WorkSchedule> schedules) {
		List<SummaryHour> newSummaries = new ArrayList<>();
		if(schedules.size() >= markers.size()) {
			for(WorkSchedule schedule : schedules) {
				for(HourMarker marker : markers) {
					newSummaries.addAll(processByMarkerDiff(schedule, marker));
				}
			}
		} else {
			for(HourMarker marker : markers) {
				for(WorkSchedule schedule : schedules) {
					newSummaries.addAll(processByMarkerDiff(schedule, marker));
				}
			}
		}
		return newSummaries;
	}
	
	private List<SummaryHour> processByMarkerDiff(WorkSchedule schedule, HourMarker marker) {
		List<SummaryHour> newSummaries = new ArrayList<>();
		String diffHour = "";
		HourType hourType = null;

		String markerEntryHour = marker.getEntryHour();
		String scheduleEntryHour = schedule.getEntryHour();
		
		String markerDepartureTime = marker.getDepartureTime();
		String scheduleDepartureTime = schedule.getDepartureTime();
		
		/**
		 * Se houver atraso/extra, gera novo registro de summaryHour
		 */
		Object[] entryDiff = calculateDiff(markerEntryHour, scheduleEntryHour, true);
		diffHour = (String) entryDiff[0];
		hourType = (HourType) entryDiff[1];
		if(hourType != null) {
			SummaryHour summary = new SummaryHour();
			summary.setHourType(hourType.getCod());
			summary.setTotalHours(diffHour);
			
			newSummaries.add(summary);
		}
		
		Object[] departureDiff = calculateDiff(markerDepartureTime, scheduleDepartureTime, false);
		diffHour = (String) departureDiff[0];
		hourType = (HourType) departureDiff[1];
		if(hourType != null) {
			SummaryHour summary = new SummaryHour();
			summary.setHourType(hourType.getCod());
			summary.setTotalHours(diffHour);
			
			newSummaries.add(summary);
		}
		
		return newSummaries;
	}
	
	/**
	 * @apiNote Calcula a difereça de dois horários, sempre o primeiro parâmetro menos o segundo
	 * @param hour_calc_1
	 * @param hour_calc_2
	 * @param entryHour
	 * @return
	 */
	private Object[] calculateDiff(String hour_calc_1, String hour_calc_2, boolean entryHour) {
		Object[] objReturn = new Object[2];
		
		String[] arr_hour_1 = hour_calc_1.split(":");
		String[] arr_hour_2 = hour_calc_2.split(":");
		
		int hour_1 = Integer.parseInt(arr_hour_1[0]);
		int	 minute_1 = Integer.parseInt(arr_hour_1[1]);
		
		int hour_2 = Integer.parseInt(arr_hour_2[0]);
		int minute_2 = Integer.parseInt(arr_hour_2[1]);
		
		/**
		 * Se for entrada, a regra é diferente, pois se o primeiro 
		 * horário for inferior ao segundo, signifca que será hora extra
		 */
		
		objReturn[0] = hour_calc_1 + " " + hour_calc_2;
		if(hour_1 > hour_2) {
			objReturn[1] = entryHour ? HourType.LATE : HourType.OVERTIME;
		} else if( hour_1 < hour_2) {
			objReturn[1] = entryHour ? HourType.OVERTIME : HourType.LATE;
		} else {
			if(minute_1 > minute_2) {
				objReturn[1] = entryHour ? HourType.LATE : HourType.OVERTIME;
			} else if( minute_1 < minute_2) {
				objReturn[1] = entryHour ? HourType.OVERTIME : HourType.LATE;
			}
		}
		
		
		return objReturn;
	}
	

}
