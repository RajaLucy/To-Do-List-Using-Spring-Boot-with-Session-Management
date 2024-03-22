package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class UserDescription {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UdeId;
	private String userDescription;
	private String sessionId;
	public Long getUdeId() {
		return UdeId;
	}
	public void setUdeId(Long udeId) {
		UdeId = udeId;
	}
	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
	

}
