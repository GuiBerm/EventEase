package bean;

import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Event {
	private String name;
	private Integer eventId;
	private String description;
	private String category;
	@JsonProperty("city")
	private String city;
	@JsonProperty("date")
	private String date;
	@JsonProperty("numberOfTickets")
	private int numberOfTickets;
	@JsonProperty("termsAndConditions")
	private String termsAndConditions;
	private double price;
	private String promotionalCode;
	@JsonProperty("companyId")
	private Integer companyId;
	private boolean soldedOut;
	
	
	public Event() {
		
	}
	public Event(String name, Integer  eventId,  String description, String category, String adrdress, String date,
			int numberOfTickets, String termsAndConditions, double price, String promotionalCode, String city) {
		
		this.name = name;
		this.eventId = eventId;
		this.description = description;
		this.category = category;
		this.city = city;
		this.date = date;
		this.numberOfTickets = numberOfTickets;
		this.termsAndConditions = termsAndConditions;
		this.price= price;
		this.promotionalCode = promotionalCode;
		soldedOut = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAddress() {
		return city;
	}

	public void setAddress(String adress) {
		this.city = adress;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNumberOfTickets() {
		return numberOfTickets;
	}

	public void setNumberOfTickets(int numberOfTickets) {
		this.numberOfTickets = numberOfTickets;
	}
	
	public void decreMentTickets() {
		this.numberOfTickets--;
		if (numberOfTickets == 0) {
			setSoldedOut(true);
		}
	}
	


	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price= price;
	}

	public String getPromotionalCode() {
		return promotionalCode;
	}

	public void setPromotionalCode(String promotionalCode) {
		this.promotionalCode = promotionalCode;
	}
	
	public Integer  getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyld) {
		this.companyId= companyld;
	}
	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public boolean isSoldedOut() {
		return soldedOut;
	}
	public void setSoldedOut(boolean soldedOut) {
		this.soldedOut = soldedOut;
	}
	

}