package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import Repositories.EventRepository;
import Repositories.Repository;
import Repositories.TicketRepository;
import bean.Event;
import bean.RequestData;
import bean.Ticket;

//Devolver al usuario: Tickets(todos) o Eventos(todos o por búsqueda)
public class UserServlet  extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Gson gson;
	private Repository<Event, Integer> eventRepository;
	private Repository<Ticket, Integer> ticketRepository;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		gson = new Gson();
		//Inicializar atributos de sesion 
		eventRepository = new EventRepository();
		ticketRepository = new TicketRepository();

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Leer parametros de request/Json y de sesión 
		HttpSession session;
		String user;
		
		session = req.getSession();
		user = (String)session.getAttribute("user");
		System.out.println(user);
		
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
            
            // Use requestData as needed
            if(requestData.getTipo().equals("evento")) {
            	List<Event> filteredEvents = eventRepository.findAll().stream()
                        .filter(e -> (requestData.getCity() == null || requestData.getCity().isEmpty() || e.getAddress().equalsIgnoreCase(requestData.getCity())))
                        .filter(e -> (requestData.getDate() == null || requestData.getDate().isEmpty() || e.getDate().equals(requestData.getDate())))
                        .filter(e -> (requestData.getMaxPrice() == 0. || e.getPrice() <= (requestData.getMaxPrice())))
                        .filter(e -> (requestData.getName() == null || requestData.getName().isEmpty() || e.getName().equalsIgnoreCase(requestData.getName())))
                        .collect(Collectors.toList());
            	
            	resp.setContentType("application/json");
            	System.out.println(gson.toJson(filteredEvents));
                resp.getWriter().write(gson.toJson(filteredEvents));
            }
            else if(requestData.getTipo().equals("ticket")) {
            	List<Ticket> tickets = ((TicketRepository) ticketRepository).findAllEventsUser(user);
            	resp.setContentType("application/json");
            	System.out.println(gson.toJson(tickets));
                resp.getWriter().write(gson.toJson(tickets));
            }
        } catch (JsonSyntaxException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON format");
        }
	}

}
