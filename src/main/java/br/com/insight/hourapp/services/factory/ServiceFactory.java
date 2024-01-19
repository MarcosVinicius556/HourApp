package br.com.insight.hourapp.services.factory;

import br.com.insight.hourapp.services.HourMarkerServiceImpl;
import br.com.insight.hourapp.services.SummaryHoursServiceImpl;
import br.com.insight.hourapp.services.WorkScheduleServiceImpl;
import br.com.insight.hourapp.services.interfaces.HourMarkerService;
import br.com.insight.hourapp.services.interfaces.SummaryOfHoursWorkedService;
import br.com.insight.hourapp.services.interfaces.WorkScheduleService;

/**
 * 
 * @author Marcos Vinicius
 * 
 * @apiNote Classe Factory para controle e centralização<br> 
 * 		 	da geração das classes Services;
 */
public class ServiceFactory {

	public static HourMarkerService createHourMarkerService() {
		return new HourMarkerServiceImpl();
	}
	
	public static SummaryOfHoursWorkedService createSummaryOfHoursWorkedService() {
		return new SummaryHoursServiceImpl();
	}
	
	public static WorkScheduleService createWorkScheduleService() {
		return new WorkScheduleServiceImpl();
	}
}
