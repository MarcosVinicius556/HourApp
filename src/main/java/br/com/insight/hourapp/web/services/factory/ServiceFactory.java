package br.com.insight.hourapp.web.services.factory;

import br.com.insight.hourapp.web.services.HourMarkerServiceImpl;
import br.com.insight.hourapp.web.services.SummaryHoursServiceImpl;
import br.com.insight.hourapp.web.services.WorkScheduleServiceImpl;
import br.com.insight.hourapp.web.services.interfaces.HourMarkerService;
import br.com.insight.hourapp.web.services.interfaces.SummaryHoursService;
import br.com.insight.hourapp.web.services.interfaces.WorkScheduleService;

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
	
	public static SummaryHoursService createSummaryHoursService() {
		return new SummaryHoursServiceImpl();
	}
	
	public static WorkScheduleService createWorkScheduleService() {
		return new WorkScheduleServiceImpl();
	}
}
