package bean;

public class RequestData {

	private String name;

	private int eventId;

	private String tipo;
	private String city;
	private String date;
	private Double maxPrice;
	
	public RequestData() {
		this.tipo = "";
		this.city = "";
		this.maxPrice = 0.;
	}
	public RequestData(String tipo, String city, String date, Double maxPrice) {
		this.tipo = tipo;
		this.city = city;
		this.date = date;
		this.maxPrice = maxPrice;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
}
