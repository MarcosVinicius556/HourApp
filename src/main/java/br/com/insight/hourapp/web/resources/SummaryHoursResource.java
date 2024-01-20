package br.com.insight.hourapp.web.resources;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.insight.hourapp.web.entities.SummaryHour;
import br.com.insight.hourapp.web.entities.WorkSchedule;
import br.com.insight.hourapp.web.entities.dto.SummaryHourDTO;
import br.com.insight.hourapp.web.entities.enums.HourType;
import br.com.insight.hourapp.web.resources.util.BufferedReaderToJson;
import br.com.insight.hourapp.web.services.factory.ServiceFactory;
import br.com.insight.hourapp.web.services.interfaces.SummaryHoursService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SummaryHours")
public class SummaryHoursResource extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(WorkSchedule.class);

	private SummaryHoursService summaryService;
	
	/**
	 * @apiNote Inicializa os serviços desta classe
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		if(summaryService == null) {
			summaryService = ServiceFactory.createSummaryHoursService();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = null;
		PrintWriter writer = null;
		Gson gson = null;
		String json = "";
		try {
			resp.setContentType("text/plain");
			action = req.getParameter("action");
			writer = resp.getWriter();
			gson = new Gson();
			
			if(action != null && action.equals("byId")) {
				long summaryId = Long.parseLong(req.getParameter("summaryId"));
				SummaryHour summary = new SummaryHour();
				summary.setSummaryId(summaryId);
				summary = summaryService.findById(summary);
				if(summary == null) {
					resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		            writer.write("Nenhum Atraso ou Hora Extra com o ID " + summaryId + " encontrado!");
					return;
				}
				json = gson.toJson(new SummaryHourDTO(summary));
			} else {
				List<SummaryHourDTO> summaries = new ArrayList<>();
				summaryService.findAll(new SummaryHour()).forEach(s -> summaries.add(new SummaryHourDTO(s)));
				Type jsonType = new TypeToken<List<SummaryHour>>() {}.getType();
				
				json = gson.toJson(summaries, jsonType);
			}
			
			writer.append(json);
		} catch (Exception e) {
			logger.error(e);
			
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writer.write(e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = null;
		Gson gson = null;
		String json = "";
		try {
			resp.setContentType("text/plain");
			writer = resp.getWriter();
			gson = new Gson();
			json = BufferedReaderToJson.bufferedReaderToJson(req.getReader());
			
			SummaryHourDTO dto = gson.fromJson(json, SummaryHourDTO.class);
			SummaryHour summary = dto.fromDTO();
			summary.setCreated(new GregorianCalendar());
			/**
			 * TODO Verifiacar como deve ser feito....
			 */
			summary.setTotalHours("01:00");
			summary.setHourType(HourType.LATE.getCod());
			
			summaryService.insert(summary);
			
			json = gson.toJson(new SummaryHourDTO(summary));
			
			resp.setStatus(HttpServletResponse.SC_OK);
			writer.append(json);
		} catch (Exception e) {
			logger.error(e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writer.write(e.getMessage());
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = null;
		writer = resp.getWriter();
		resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        writer.write("Operação não permitida!");
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = null;
		try {
			resp.setContentType("text/plain");
			writer = resp.getWriter();
			
			long summaryId = Long.parseLong(req.getParameter("summaryId"));
			SummaryHour summary = new SummaryHour();
			summary.setSummaryId(summaryId);
			summaryService.remove(summary);
			
			resp.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			logger.error(e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writer.write(e.getMessage());
		}
	}
	
}
