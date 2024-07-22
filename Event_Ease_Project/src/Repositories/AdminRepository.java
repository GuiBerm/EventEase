package Repositories;

import bean.Admin;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;

public class AdminRepository extends Repository<Admin, String> {
	   private final static  String filePath  = "C:\\Users\\carlo\\git\\repository\\Event_Ease_Project\\src\\json\\admin.json";

	@Override
	public List<Admin> readFile() throws IOException {
		File file = new File(filePath);
        if (!file.exists()) {
            return List.of();
        }
        return objectMapper.readValue(file, new TypeReference<List<Admin>>() {});	
	}

	@Override
	public void writeFile(List<Admin> admins) throws IOException {
		File file = new File(filePath);
		objectMapper.writeValue(file, admins);
	}

	@Override
	public List<Admin> findAll() throws IOException {
		return readFile();
	}

	@Override
	public Optional<Admin> findById(String id) throws IOException {
		List<Admin> admins = readFile();
		return admins.stream().filter(a -> a.getId().equals(id)).findFirst();	
	}

	@Override
	public void save(Admin admin ) throws IOException {
		List<Admin> admins = readFile();
		admins.add(admin);
		writeFile(admins);
		
	}

	@Override
	public void deleteById(String id) throws IOException {
		List<Admin> admins = readFile();
		admins.removeIf(a -> a.getId().equals(id));
		writeFile(admins);
	}

}
