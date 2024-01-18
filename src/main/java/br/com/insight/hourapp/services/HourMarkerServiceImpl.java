package br.com.insight.hourapp.services;

import org.apache.log4j.Logger;

import br.com.insight.hourapp.entities.HourMarker;
import br.com.insight.hourapp.repositories.factory.RepositoryFactory;
import br.com.insight.hourapp.repositories.interfaces.BaseRepository;
import br.com.insight.hourapp.repositories.interfaces.HourMarkerRepository;
import br.com.insight.hourapp.services.interfaces.HourMarkerService;

public class HourMarkerServiceImpl implements HourMarkerService {

private static final Logger logger = Logger.getLogger(HourMarker.class);
	
	private HourMarkerRepository repository;
	
	public HourMarkerServiceImpl() {
		logger.info("Inicializando os repositórios da " + HourMarkerService.class.getName());
		if(repository == null) 
			repository = RepositoryFactory.createHourMarkerRepository();
	}
	
	@Override
	public BaseRepository getRepository() {
		return repository;
	}

}
