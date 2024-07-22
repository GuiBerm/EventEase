package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import Repositories.AdminRepository;
import Repositories.EventRepository;
import Repositories.Repository;
import bean.Admin;
import bean.Event;
import bean.RequestData;

public class CreateEvent extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson;
	private Repository<Event, Integer> eventRepository;
	private Repository<Admin, String> adminRepository;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		gson = new Gson();
		// Inicializar atributos de sesion
		eventRepository = new EventRepository();
		adminRepository = new AdminRepository();

	}
	
	private synchronized int generateEventId() {
		Optional<Event> optionalEvent;
		Event event;
		int EventId = -1;

		EventRepository eventRepo = (EventRepository) eventRepository;
		try {

			if (eventRepo.firstEvent()) { 
				EventId = 1;
			} else {
				optionalEvent = eventRepo.getLastEventCreated();
				if (optionalEvent.isPresent()) { 
					event = optionalEvent.get();
					EventId = event.getEventId() + 1;
				} else {
					System.out.println("Error at getLastEventCreated");
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EventId;
	}
	

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Leer parametros de request/Json y de sesión
		HttpSession session;
		String user;

		session = req.getSession();
		user = (String) session.getAttribute("user");

		// Read the JSON data from the request body
		StringBuilder jsonBuilder = new StringBuilder();
		try (BufferedReader reader = req.getReader()) {
			String line;
			while ((line = reader.readLine()) != null) {
				jsonBuilder.append(line);
			}
		}

		// Convert JSON data to a String
		String jsonString = jsonBuilder.toString();

		try {
			// Convert JSON String to RequestData object
			RequestData requestData = gson.fromJson(jsonString, RequestData.class);

			Optional<Admin> a = adminRepository.findById(user);
			Admin admin;
			if (a.isPresent() && requestData.getName() != null) {
				admin = a.get();
				
				Event e = new Event();
				e.setName(requestData.getName());
				e.setAddress(requestData.getCity());
				e.setDate(requestData.getDate());
				e.setPrice(requestData.getMaxPrice());
				e.setCompanyId(admin.getCompanyId());
				
				
				
				e.setEventId(generateEventId());
				
				if(e.getEventId()>0) {	
					eventRepository.save(e);
					resp.getWriter().write("Successfully created");
				}
			}


		} catch (JsonSyntaxException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON format");
		}
	}
}
