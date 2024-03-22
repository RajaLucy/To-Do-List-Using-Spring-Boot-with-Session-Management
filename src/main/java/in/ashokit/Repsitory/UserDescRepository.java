package in.ashokit.Repsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.UserDescription;
import jakarta.transaction.Transactional;

@Repository
public interface UserDescRepository extends JpaRepository<UserDescription, Long>{

	
	List<UserDescription> findBySessionId(String id);
	
	@Transactional
	@Modifying
    @Query("DELETE FROM UserDescription WHERE sessionId = :sid")
	public void deleteBySessionId(String sid);
	
}
