package br.com.insight.hourapp.web.services;

import org.apache.log4j.Logger;

import br.com.insight.hourapp.web.entities.HourMarker;
import br.com.insight.hourapp.web.repositories.factory.RepositoryFactory;
import br.com.insight.hourapp.web.repositories.interfaces.BaseRepository;
import br.com.insight.hourapp.web.repositories.interfaces.WorkScheduleRepository;
import br.com.insight.hourapp.web.services.interfaces.HourMarkerService;
import br.com.insight.hourapp.web.services.interfaces.WorkScheduleService;

public class WorkScheduleServiceImpl implements WorkScheduleService {

private static final Logger logger = Logger.getLogger(HourMarker.class);
	
	private WorkScheduleRepository repository;
	
	public WorkScheduleServiceImpl() {
		logger.info("Inicializando os repositórios da " + HourMarkerService.class.getName());
		if(repository == null) 
			repository = RepositoryFactory.createWorkScheduleRepository();
	}
	
	@Override
	public BaseRepository getRepository() {
		return repository;
	}

}
