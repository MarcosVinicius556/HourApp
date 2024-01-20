package br.com.insight.hourapp.web.resources;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.insight.hourapp.web.entities.HourMarker;
import br.com.insight.hourapp.web.entities.SummaryHour;
import br.com.insight.hourapp.web.entities.WorkSchedule;
import br.com.insight.hourapp.web.entities.dto.WorkScheduleDTO;
import br.com.insight.hourapp.web.resources.util.BufferedReaderToJson;
import br.com.insight.hourapp.web.services.factory.ServiceFactory;
import br.com.insight.hourapp.web.services.interfaces.WorkScheduleService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/WorkSchedules")
public class WorkScheduleResource extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(WorkSchedule.class);

	private WorkScheduleService scheduleService;
	
	/**
	 * @apiNote Inicializa os serviços desta classe
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		if(scheduleService == null) {
			scheduleService = ServiceFactory.createWorkScheduleService();
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
				long scheduleId = Long.parseLong(req.getParameter("scheduleId"));
				WorkSchedule schedule = new WorkSchedule();
				schedule.setScheduleId(scheduleId);
				schedule = scheduleService.findById(schedule);
				if(schedule == null) {
					resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
		            writer.write("Nenhum Horário de Trabalho com o ID " + scheduleId + " encontrado!");
					return;
				}
				json = gson.toJson(new WorkScheduleDTO(schedule));
			} else {
				List<WorkScheduleDTO> schedules = new ArrayList<>();
				scheduleService.findAll(new WorkSchedule()).forEach(w -> schedules.add(new WorkScheduleDTO(w)));
				Type jsonType = new TypeToken<List<WorkSchedule>>() {}.getType();
				
				json = gson.toJson(schedules, jsonType);
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
		List<WorkSchedule> schedules = null;
		try {
			resp.setContentType("text/plain");
			writer = resp.getWriter();
			gson = new Gson();
			json = BufferedReaderToJson.bufferedReaderToJson(req.getReader());
			schedules = scheduleService.findAll(new WorkSchedule()); 
			
			if(schedules != null && schedules.size() < 3){
				WorkSchedule workSchedule = gson.fromJson(json, WorkSchedule.class);
				scheduleService.insert(workSchedule);	
				resp.setStatus(HttpServletResponse.SC_OK);
			} else {
				resp.setStatus(HttpServletResponse.SC_CONFLICT);
				writer.write("Só é possível inserir até três horários");
			}
			
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
			
			WorkSchedule workSchedule = gson.fromJson(json, WorkSchedule.class);
			scheduleService.update(workSchedule);
			
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
		try {
			resp.setContentType("text/plain");
			writer = resp.getWriter();
			
			long scheduleId = Long.parseLong(req.getParameter("scheduleId"));
			WorkSchedule schedule = new WorkSchedule();
			schedule.setScheduleId(scheduleId);
			
			schedule = scheduleService.findById(schedule);
			/**
			 * Devolve uma exceção personalizada caso exista registro nas tabelas associadas;
			 */
			if(schedule.getHourMarkers().size() > 0) {
				String markersId = "";
				for(HourMarker hourMarker : schedule.getHourMarkers()) {
					markersId += hourMarker.getMarkerId() + ", ";
				};
				
				resp.setStatus(HttpServletResponse.SC_CONFLICT);
	            writer.write("Existem registros de Marcações com este horário.\n"
	            		   + "Para removê-lo é necessário remover os registros associados.\n"
	            		   + "Registros associados: " + markersId.substring(0, markersId.length() -2));	
				return;
			} else if(schedule.getSummaryHours().size() > 0) {
				String summariesId = "";
				for(SummaryHour summary : schedule.getSummaryHours()) {
					summariesId += summary.getSummaryId() + ", ";
				};
				
				resp.setStatus(HttpServletResponse.SC_CONFLICT);
	            writer.write("Existem registros de registro de Hora Extra/Atraso com este horário.\n"
	            		   + "Para removê-lo é necessário remover os registros associados.\n"
	            		   + "Registros associados: " + summariesId.substring(0, summariesId.length() -2));	
				return;
			}
			
			scheduleService.remove(schedule);
			
			resp.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			logger.error(e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writer.write(e.getMessage());
		}
	}
	
}
