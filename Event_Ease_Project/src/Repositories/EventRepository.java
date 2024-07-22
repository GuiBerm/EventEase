package Repositories;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;

import bean.Event;
import bean.Ticket;

public class EventRepository extends  Repository<Event, Integer> {
	   private final static  String filePath  = "C:\\Users\\carlo\\git\\repository\\Event_Ease_Project\\src\\json\\events.json";


	public EventRepository() {
		super();
	}

	@Override
	public List<Event> readFile() throws IOException {
		File file = new File(filePath);
        if (!file.exists()) {
            return List.of();
        }
        return objectMapper.readValue(file, new TypeReference<List<Event>>() {});	
		
	}

	@Override
	public void writeFile(List<Event> events) throws IOException {
		File file = new File(filePath);
		objectMapper.writeValue(file, events );
	}		
	

	@Override
	public List<Event> findAll() throws IOException {
		// TODO Auto-generated method stub
		return readFile();
	}

	@Override
	public Optional<Event> findById( Integer id) throws IOException {
		List<Event> events = readFile();
		return events.stream().filter(e -> e.getEventId().equals(id)).findFirst();
	}
	@Override
	public void save(Event event) throws IOException {
		List<Event> events = readFile();
		events.add(event);
		writeFile(events);
	}

	@Override
	public void deleteById(Integer id) throws IOException {
		List<Event> events = readFile();
		events.removeIf(e -> e.getEventId().equals(id));
		writeFile(events);
	}
	
	public List<Event> findAllEventsAdmin(int companyId) throws IOException {
		List <Event> events;
		events = readFile();
		return events.stream().filter(e -> e.getCompanyId().equals(companyId))
				.collect(Collectors.toList());
		
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	public Optional<Event> getLastEventCreated() throws IOException{
		List<Event> events = readFile();
		return  events.stream().max((t1,t2) -> Integer.compare(t1.getEventId(), t2.getEventId()));
	}
	
	public boolean firstEvent() throws IOException {
		List<Event> events = readFile();
		return events.isEmpty();
	}

	

	
}
