package br.com.insight.hourapp.web.repositories.factory;

import br.com.insight.hourapp.web.repositories.HourMarkerRepositoryImpl;
import br.com.insight.hourapp.web.repositories.SummaryHoursRepositoryImpl;
import br.com.insight.hourapp.web.repositories.WorkScheduleRepositoryImpl;
import br.com.insight.hourapp.web.repositories.interfaces.HourMarkerRepository;
import br.com.insight.hourapp.web.repositories.interfaces.SummaryHoursRepository;
import br.com.insight.hourapp.web.repositories.interfaces.WorkScheduleRepository;

/**
 * 
 * @author Marcos Vinicius
 * 
 * @apiNote Classe Factory para controle e centralização<br> 
 * 		 	da geração das classes Repositorys;
 */
public class RepositoryFactory {

	public static HourMarkerRepository createHourMarkerRepository() {
		return new HourMarkerRepositoryImpl();
	}
	
	public static SummaryHoursRepository createSummaryOfHoursWorkedRepository() {
		return new SummaryHoursRepositoryImpl();
	}
	
	public static WorkScheduleRepository createWorkScheduleRepository() {
		return new WorkScheduleRepositoryImpl();
	}
}