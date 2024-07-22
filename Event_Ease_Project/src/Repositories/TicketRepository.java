package Repositories;

import bean.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;


public class TicketRepository extends  Repository<Ticket, Integer> {
	   private final static  String filePath  = "C:\\Users\\carlo\\git\\repository\\Event_Ease_Project\\src\\json\\tickets.json";

	
	public TicketRepository() {
		super();
		
	}

	@Override
	public List<Ticket> readFile() throws IOException {
		
		File file = new File(filePath);
        if (!file.exists()) {
            return List.of();
        }
        return objectMapper.readValue(file, new TypeReference<List<Ticket>>() {});	
		
	}

	@Override
	public void writeFile(List<Ticket> tickets) throws IOException {
		File file = new File(filePath);
		objectMapper.writeValue(file, tickets);
	}

	@Override
	public List<Ticket> findAll() throws IOException {
		return readFile();
	}

	@Override
	public Optional<Ticket> findById(Integer id) throws IOException {
		List<Ticket> tickets = readFile();
		return tickets.stream().filter(t -> t.getTicketId() == id).findFirst();
	}


	@Override
	public void deleteById(Integer id) throws IOException {
		List<Ticket> tickets = readFile();
		tickets.removeIf(t -> t.getTicketId() == id);
		writeFile(tickets);
		
	}

	@Override
	public void save(Ticket ticket ) throws IOException {
        List<Ticket> tickets = readFile();
        tickets.add(ticket);
        writeFile(tickets);		
	}
	
	public Optional<Ticket> getLastTicketCreated() throws IOException{
		List<Ticket> tickets = readFile();
		return  tickets.stream().max((t1,t2) -> Integer.compare(t1.getTicketId(), t2.getTicketId()));
	}
	
	public boolean firstTicket() throws IOException {
		List<Ticket> tickets = readFile();
		return tickets.isEmpty();
	}
	
	public List<Ticket> findAllEventsUser(String userId) throws IOException{
		List<Ticket> tickets; 
		tickets = readFile();
		return tickets.stream().filter(t -> t.getUserId().equals(userId)).collect(Collectors.toList());
		
	}

	

}
