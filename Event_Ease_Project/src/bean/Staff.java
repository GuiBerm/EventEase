package bean;

import java.util.LinkedList;
import java.util.List;

import exceptions.notFoundElementException;

public abstract class Staff extends Profile{
	
	

	
	public Staff() {
		super();
	}
	public Staff(String id, String password, String email, int tlf_number) {
		super(id, password, email, tlf_number);
		//historicoEventos = new LinkedList<Event>();

	}
	
}