package br.com.insight.hourapp.resources;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.insight.hourapp.entities.SummaryHour;
import br.com.insight.hourapp.entities.WorkSchedule;
import br.com.insight.hourapp.resources.util.BufferedReaderToJson;
import br.com.insight.hourapp.services.factory.ServiceFactory;
import br.com.insight.hourapp.services.interfaces.SummaryHoursService;
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
				json = gson.toJson(summary);
			} else {
				List<SummaryHour> summaries = summaryService.findAll(new SummaryHour());
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
			
			SummaryHour summary = gson.fromJson(json, SummaryHour.class);
			summaryService.insert(summary);
			
			resp.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			logger.error(e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writer.write(e.getMessage());
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = null;
		Gson gson = null;
		String json = "";
		try {
			resp.setContentType("text/plain");
			writer = resp.getWriter();
			gson = new Gson();
			json = BufferedReaderToJson.bufferedReaderToJson(req.getReader());
			
			SummaryHour summary = gson.fromJson(json, SummaryHour.class);
			summaryService.update(summary);
			
			resp.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			logger.error(e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writer.write(e.getMessage());
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writer = null;
		Gson gson = null;
		String json = "";
		try {
			resp.setContentType("text/plain");
			writer = resp.getWriter();
			gson = new Gson();
			json = BufferedReaderToJson.bufferedReaderToJson(req.getReader());
			
			SummaryHour summary = gson.fromJson(json, SummaryHour.class);
			summaryService.remove(summary);
			
			resp.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			logger.error(e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writer.write(e.getMessage());
		}
	}
	
}
