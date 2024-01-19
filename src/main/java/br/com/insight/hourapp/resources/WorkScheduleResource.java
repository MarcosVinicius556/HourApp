package br.com.insight.hourapp.resources;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.insight.hourapp.entities.WorkSchedule;
import br.com.insight.hourapp.resources.util.BufferedReaderToJson;
import br.com.insight.hourapp.services.factory.ServiceFactory;
import br.com.insight.hourapp.services.interfaces.WorkScheduleService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/HourMarkers")
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
				json = gson.toJson(schedule);
			} else {
				List<WorkSchedule> schedules = scheduleService.findAll(new WorkSchedule());
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
		try {
			resp.setContentType("text/plain");
			writer = resp.getWriter();
			gson = new Gson();
			json = BufferedReaderToJson.bufferedReaderToJson(req.getReader());
			
			WorkSchedule workSchedule = gson.fromJson(json, WorkSchedule.class);
			scheduleService.insert(workSchedule);
			
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
		Gson gson = null;
		String json = "";
		try {
			resp.setContentType("text/plain");
			writer = resp.getWriter();
			gson = new Gson();
			json = BufferedReaderToJson.bufferedReaderToJson(req.getReader());
			
			WorkSchedule workSchedule = gson.fromJson(json, WorkSchedule.class);
			scheduleService.remove(workSchedule);
			
			resp.setStatus(HttpServletResponse.SC_OK);
		} catch (Exception e) {
			logger.error(e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writer.write(e.getMessage());
		}
	}
	
}
