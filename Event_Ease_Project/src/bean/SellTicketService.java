package bean;

import java.io.IOException;
import java.util.Optional;

import Repositories.EventRepository;
import Repositories.Repository;
import Repositories.TicketRepository;
import exceptions.notFoundElementException;

public class SellTicketService {
	private Repository<Event, Integer> eventRepository;
	private Repository<Ticket, Integer> ticketRepository;
	private Event event;

	public SellTicketService(int eventId) {
		eventRepository = new EventRepository();
		ticketRepository = new TicketRepository();

		Optional<Event> optionalEvent;
		try {
			optionalEvent = eventRepository.findById(eventId);
			if (optionalEvent.isPresent()) {
				this.event = optionalEvent.get();
			} else {
				System.out.println("Error in the  constructor, the event doesn't exist ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//	Post: Returns the price which have the tickets of the event we are going to buy 
	public double getTicketPrice() {
		return this.event.getPrice();
	}

//	Post: True if is no more  possible to buy a ticket, false if there are available tickets yet
	public boolean isSoldedOut() {
		return this.event.isSoldedOut();
	}

//	Desc: Create the ticket associated to an event and to a user  that 
//	has been buyed an is added to the dataBase a new entry 
	public synchronized void  createTicket(int eventId, String userId) throws IOException, notFoundElementException {
		String  ticketInfo;
		int ticketId;
		Ticket ticket;
		
		ticketInfo = "";
		ticketId = 0;
		
		//We need to  generate the ticket info description
		ticketInfo = "Ticket of the event "+event.getName();
		//Now we have to generate the ticket Id
		ticketId = generateTicketId();
		if(ticketId != -1) { //Correct value	
			if( ticketRepository.findById(ticketId).isEmpty()) { //We verify that any other ticket of the system has this id
				//Now we can finally create the ticket and add it to the DB
				ticket = new Ticket(ticketId, ticketInfo, userId, eventId);
				ticketRepository.save(ticket);
				//We decrement the number of tickets left to be selled and write it in the db
				event.decreMentTickets();
				eventRepository.deleteById(event.getEventId());
				eventRepository.save(event);
				
				
			
			}else {
				System.out.println("Error: Another ticket exist with this id ");
			}
		}else {
			System.out.println("Error at generateTicket");
		}
		
	}

	// Method used to generate the ticket Id
	// Pre: Must be verifyed that is an unique identifier
	// Post: returns a integer that will be the primary key for tickets, -1 if an
	// error has happened
	private synchronized int generateTicketId() {
		Optional<Ticket> optionalTicket;
		Ticket ticket;
		int ticketId = -1;

		TicketRepository ticketRepo = (TicketRepository) ticketRepository;
		try {

			if (ticketRepo.firstTicket()) { // If this is the first ticket that is going to be generated
				ticketId = 1;
			} else {
				optionalTicket = ticketRepo.getLastTicketCreated();
				if (optionalTicket.isPresent()) { // Verify that the result is not a null object
					ticket = optionalTicket.get();
					ticketId = ticket.getTicketId() + 1;
				} else {
					System.out.println("Error at getLastTicketCreated");
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ticketId;
	}

}
