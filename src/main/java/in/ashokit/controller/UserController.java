package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.bindings.LoginUser;
import in.ashokit.entity.User;
import in.ashokit.entity.UserDescription;
import in.ashokit.service.UserServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private UserServiceImp userSer;

	//login form loading 
	@GetMapping("/")
	public String LoginForm(Model model)
	{
		model.addAttribute("Login", new LoginUser());
		return "LoginForm";
		
	}
	
	//Register Page Loading
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
	    model.addAttribute("User", new User());
	    return "Register";
	}

	
	
	@PostMapping("/registerUser")
	public String RegisterUser(Model model,@ModelAttribute("User") User u)
	{
		boolean saveUser = userSer.saveUser(u);
		if(saveUser)
		{
			model.addAttribute("Suc", "Register Success");
		}
		else {
			model.addAttribute("Fail", "Given mail id is exists Try Another");
		}
		
		return "Register";
		
	}
	
	
	@PostMapping("/loginUser")
	public String LoginUser(Model model,@ModelAttribute("Login")LoginUser l,HttpServletRequest req)
	{
		User loginUser = userSer.loginUser(l.getUserEmail(), l.getUserPassword());
		
		if(loginUser == null)
		{
			model.addAttribute("invalid", "invalid Crediatials");
			return "LoginForm";
		}
		
		
		HttpSession session = req.getSession(true);
		session.setAttribute("UID", loginUser.getUserId());
		   
			return "redirect:/dashboard";
		
		
	}
	
	@GetMapping("/dashboard")
	public String DashBoard(Model model,HttpServletRequest req)
	{
		HttpSession session = req.getSession(false);
		Object attribute = session.getAttribute("UID");
		Long id=(Long)attribute;
		String name = userSer.getName(id);
		model.addAttribute("My", "Hii "+name+" wirte your notes");
		return "DashBoardView";
		
	}
	
	
	
	
	@PostMapping("/saveDesc")
	public String saveDescr(@RequestParam String todoText, HttpServletRequest req, Model model) {
	    HttpSession session = req.getSession(false);
	    Object attribute = session.getAttribute("UID");
	    Long id = (Long) attribute;

	    UserDescription u = new UserDescription();
	    u.setSessionId(String.valueOf(id));
	    u.setUserDescription(todoText);
	    boolean b = userSer.saveUserDescription(u);

	    
	    String name = userSer.getName(id);
		
	    
	    if (b) {
	        
	    	model.addAttribute("My", "Hii "+name);
	        List<UserDescription> list = userSer.getList(String.valueOf(id));
	        model.addAttribute("data", list);
	    }

	    return "DashBoardView";
	}
	
	@GetMapping("/delete")
	public  String clearData(HttpServletRequest req,Model model)
	{
		 HttpSession session = req.getSession(false);
		    Object attribute = session.getAttribute("UID");
		    Long id = (Long) attribute;
		userSer.deleteByid(String.valueOf(id));
		return "redirect:/dashboard";
		
	}
	
	
	
	@GetMapping("/getMyDataAll")
	public String getMyAllData(HttpServletRequest req,Model model)
	{
		HttpSession session = req.getSession(false);
	    Object attribute = session.getAttribute("UID");
	    Long id = (Long) attribute;
	    
	    List<UserDescription> allData = userSer.getAllData();
	    
	    model.addAttribute("data", allData);
	    return "DashBoardView";
		
	}
	
	
	
	

	
	
	
	
	
}
