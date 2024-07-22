package servlet;

import java.io.IOException;
import java.util.List;
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

//Devolverle los eventos al admin
public class AdminServlet extends HttpServlet {

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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Leer parametros de request/Json y de sesión
		
		//System.out.println("Ha llegado un json ");
		
		
		HttpSession session;
		String user;

		session = req.getSession();
		user = (String) session.getAttribute("user");

		try {
			Optional<Admin> a = adminRepository.findById(user);
			Admin admin;
			if (a.isPresent()) {
				admin = a.get();
				List<Event> filteredEvents =  ((EventRepository) eventRepository).findAllEventsAdmin(admin.getCompanyId());
				//System.out.println(gson.toJson(filteredEvents));
				resp.setContentType("application/json");
				resp.getWriter().write(gson.toJson(filteredEvents));
				
			}

		} catch (JsonSyntaxException e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON format");
		}
	}

}