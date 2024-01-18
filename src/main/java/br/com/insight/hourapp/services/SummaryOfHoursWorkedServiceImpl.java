package br.com.insight.hourapp.services;

import org.apache.log4j.Logger;

import br.com.insight.hourapp.entities.HourMarker;
import br.com.insight.hourapp.repositories.factory.RepositoryFactory;
import br.com.insight.hourapp.repositories.interfaces.BaseRepository;
import br.com.insight.hourapp.repositories.interfaces.SummaryOfHoursWorkedRepository;
import br.com.insight.hourapp.services.interfaces.HourMarkerService;
import br.com.insight.hourapp.services.interfaces.SummaryOfHoursWorkedService;

public class SummaryOfHoursWorkedServiceImpl implements SummaryOfHoursWorkedService {

private static final Logger logger = Logger.getLogger(HourMarker.class);
	
	private SummaryOfHoursWorkedRepository repository;
	
	public SummaryOfHoursWorkedServiceImpl() {
		logger.info("Inicializando os repositórios da " + HourMarkerService.class.getName());
		if(repository == null) 
			repository = RepositoryFactory.createSummaryOfHoursWorkedRepository();
	}
	
	@Override
	public BaseRepository getRepository() {
		return repository;
	}

}
