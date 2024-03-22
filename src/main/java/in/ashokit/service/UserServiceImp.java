package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.Repsitory.UserDescRepository;
import in.ashokit.Repsitory.UserRepository;
import in.ashokit.entity.User;
import in.ashokit.entity.UserDescription;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserDescRepository userDesc;
	
	
	@Override
	public boolean saveUser(User u) {
	
		User findByUserEmail = userRepo.findByUserEmail(u.getUserEmail());
		if(findByUserEmail != null)
		{
			return false;
		}
		
		User save = userRepo.save(u);
		if(save.getUserId()!=null)
		{
			return true;
		}
		return false;
	}


	@Override
	public User loginUser(String email, String password) {
		User user = userRepo.findByUserEmailAndUserPassWord(email, password);
		
		return user;
	}


	@Override
	public String getName(Long id) {
		User user=null;
		Optional<User> findById = userRepo.findById(id);
		if(findById.isPresent())
		{
			 user = findById.get();
			
		}
		return user.getUserName();
	}


	@Override
	public boolean saveUserDescription(UserDescription user) {
		UserDescription save = userDesc.save(user);
		if(save.getUdeId()!=null)
		{
			return true;
		}
		return false;
	}


	@Override
	public List<UserDescription> getList(String sid) {
		
		List<UserDescription> findBySessionId = userDesc.findBySessionId(sid);
		return findBySessionId;
	}


	@Override
	public void deleteByid(String id) {
	
		userDesc.deleteBySessionId(id);
	}


	@Override
	public List<UserDescription> getAllData() {
		
		return userDesc.findAll() ;
	}


	

}
