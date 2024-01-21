package br.com.insight.hourapp.web.services.interfaces;

import java.util.List;

import br.com.insight.hourapp.web.entities.SummaryHour;
import br.com.insight.hourapp.web.entities.dto.SummaryHourCalculateDTO;
import br.com.insight.hourapp.web.resources.enums.CalculateMode;

/**
 * @author Marcos Vinicius
 */
public interface SummaryHoursService extends BaseService<SummaryHour>{

	/**
	 * @apiNote Cálcula o valor total de horas de atraso/extra.
	 * 			 
	 * @param schedule
	 * @param marker
	 * @return
	 */
	List<SummaryHour> calculateTotalHours(SummaryHourCalculateDTO dto, CalculateMode calcMode);
	
}