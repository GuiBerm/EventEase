package bean;

public class Ticket {
	Integer ticketId;
	String info;
	boolean validated; 
	String userId;
	private Integer  eventId;

	public Ticket() {
		
	}
	public Ticket(int ticketId, String info, String userId, Integer  eventId) {
		this.ticketId = ticketId;
		this.info = info;
		this.userId = userId;
		this.validated = false;
		this.eventId = eventId;
	}
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer  getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	
}
