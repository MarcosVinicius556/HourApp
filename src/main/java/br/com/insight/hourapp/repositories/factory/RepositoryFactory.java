package br.com.insight.hourapp.repositories.factory;

import br.com.insight.hourapp.repositories.HourMarkerRepositoryImpl;
import br.com.insight.hourapp.repositories.SummaryOfHoursWorkedRepositoryImpl;
import br.com.insight.hourapp.repositories.WorkScheduleRepositoryImpl;
import br.com.insight.hourapp.repositories.interfaces.HourMarkerRepository;
import br.com.insight.hourapp.repositories.interfaces.SummaryOfHoursWorkedRepository;
import br.com.insight.hourapp.repositories.interfaces.WorkScheduleRepository;

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
	
	public static SummaryOfHoursWorkedRepository createSummaryOfHoursWorkedRepository() {
		return new SummaryOfHoursWorkedRepositoryImpl();
	}
	
	public static WorkScheduleRepository createWorkScheduleRepository() {
		return new WorkScheduleRepositoryImpl();
	}
}