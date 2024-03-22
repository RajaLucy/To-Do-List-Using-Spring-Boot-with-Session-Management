package in.ashokit.Repsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	
public User findByUserEmail(String email);
	
	public User findByUserEmailAndUserPassWord(String email,String pass);
	
}
