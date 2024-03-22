package in.ashokit.service;

import java.util.List;

import in.ashokit.entity.User;
import in.ashokit.entity.UserDescription;

public interface UserService {
	
	
	public boolean saveUser(User u);
	
    public User loginUser(String email,String password);
    
    
    public String getName(Long id);
    
    
    public  boolean saveUserDescription(UserDescription user);
    
    
    public List<UserDescription> getAllData();
    
    public List<UserDescription> getList(String sid);
    
    
    public void deleteByid(String id);

}
