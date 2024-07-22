// repositories/UserRepository.java
package Repositories;

import com.fasterxml.jackson.core.type.TypeReference;

import bean.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


public class UserRepository extends Repository<User, String>{
   private final static  String filePath  = "C:\\Users\\carlo\\git\\repository\\Event_Ease_Project\\src\\json\\users.json";
	
    public UserRepository() {
    	super();
    }
    public  List<User> readFile() throws IOException {
    	File file = new File(filePath);
        // Verificar si el archivo existe
        if (!file.exists()) {
            System.err.println("File does not exist: " + file.getAbsolutePath());
            return List.of();
        }

        return objectMapper.readValue(file, new TypeReference<List<User>>() {});
    }

    public void writeFile(List<User> users) throws IOException {
        objectMapper.writeValue(new File(filePath), users);		
    }

    public List<User> findAll() throws IOException {
        return readFile();
    }

    public Optional<User> findById(String userId) throws IOException {
        return readFile().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
    }

    public void save(User user) throws IOException {
        List<User> users = readFile();
        users.add(user);
        writeFile(users);
    }

    public void deleteById(String id) throws IOException {
        List<User> users = readFile();
        users.removeIf(user -> user.getId().equals(id));
        writeFile(users);
    }
}
