package Repositories;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;


//Abstract class  which defines the basic structure of a repository

//Have to be defined as a singleton , not done yet 

public abstract class Repository<T, B> {
	
	protected   ObjectMapper objectMapper;
	
	public Repository() {
	
		this.objectMapper = new ObjectMapper();
	}
	
	public abstract List<T> readFile() throws IOException;
	public abstract  void writeFile(List<T> users) throws IOException;
	public abstract List<T> findAll() throws IOException;
	public abstract Optional<T> findById(B id) throws IOException;
	public abstract void save(T object) throws IOException;
	public abstract void deleteById(B id) throws IOException;
	
}
