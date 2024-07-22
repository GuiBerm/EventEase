package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Repositories.AdminRepository;
import Repositories.Repository;
import Repositories.UserRepository;
import bean.Admin;
import bean.User;

import java.io.*;
import java.util.Optional;

public class Broker extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Repository userRepository;
	private Repository adminRepository;

	@Override
	public void init() throws ServletException {
		super.init();
		userRepository = new UserRepository();
		adminRepository = new AdminRepository();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		System.out.println("aconcaguina");
		
		
    	String profileType = request.getParameter("profileType");
    	String id = request.getParameter("id");
    	String password = request.getParameter("password");
    	
    	HttpSession session = request.getSession(true);

        if (profileType.equals("admin")) {
        	Optional<Admin> optionalAdmin;
        	Admin admin;
        	
        	//Verify credentials 
        	optionalAdmin = adminRepository.findById(id);
        	if(optionalAdmin.isPresent()) {
        		admin = optionalAdmin.get();
        		if(admin.getPassword().equals(password)) { //The credentials introduced are correct 
        		
			    session.setAttribute("user", id); //We set the attribute id 
			    
			    //Then we forward to the homePage of the admin 
			    response.sendRedirect(request.getContextPath() + "/scripts/EventEaseStaff/Staff.html");              
        		}else {
            		PrintWriter out = response.getWriter();
    				out.println("<html>");
    				out.println("<head>");

    				out.println("<title>ServletB </title>");
    				out.println("<link rel=\"stylesheet\" href=\"styles/default.css\" type=\"text/css\"></link>");

    				out.println("</head>");
    				out.println("<body>");
    				out.println("<div> This password is not correct  </div>");
    				out.println("</body>");
    				out.println("</html>");
        		}
        	}else {
        		PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");

				out.println("<title>ServletB </title>");
				out.println("<link rel=\"stylesheet\" href=\"styles/default.css\" type=\"text/css\"></link>");

				out.println("</head>");
				out.println("<body>");
				out.println("<div> This profile do not exist in the system </div>");
				out.println("</body>");
				out.println("</html>");
        	}
        
        } else if (profileType.equals("user")) {
        	//Verify credentials        	
        	Optional<User> optionalUser;
        	User user;
        	
        	//Verify credentials 
        	optionalUser = userRepository.findById(id);
        	if(optionalUser.isPresent()) {
        		user = optionalUser.get();
        		if(user.getPassword().equals(password)) { //The credentials introduced are correct 
        		
			    session.setAttribute("user", id); //We set the attribute id 
			    
			    //Then we forward to the homePage of the user 
			    String forwardPath = "/scripts/EventEaseUser/User.html";
			    response.sendRedirect(request.getContextPath() + "/scripts/EventEaseUser/User.html");   
			    
        		}else {
            		PrintWriter out = response.getWriter();
    				out.println("<html>");
    				out.println("<head>");

    				out.println("<title>ServletB </title>");
    				out.println("<link rel=\"stylesheet\" href=\"styles/default.css\" type=\"text/css\"></link>");

    				out.println("</head>");
    				out.println("<body>");
    				out.println("<div> This password is not correct  </div>");
    				out.println("</body>");
    				out.println("</html>");
        		}
        	}else {
        		PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");

				out.println("<title>ServletB </title>");
				out.println("<link rel=\"stylesheet\" href=\"styles/default.css\" type=\"text/css\"></link>");

				out.println("</head>");
				out.println("<body>");
				out.println("<div> This profile do not exist in the system </div>");
				out.println("</body>");
				out.println("</html>");
        	}
        }
    }
}
