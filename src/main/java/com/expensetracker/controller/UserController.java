package com.expensetracker.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import com.expensetracker.entity.ConfirmationToken;
import com.expensetracker.entity.User;
import com.expensetracker.repository.ConfirmationTokenRepository;
import com.expensetracker.service.EmailSenderService;
import com.expensetracker.service.UserService;
import com.expensetracker.util.EnvUtil;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	Environment environment;

	@Autowired
	private EnvUtil envUtil;
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable int id) {
	
		return userService.getUserById(id);
	}
	
	@GetMapping("userList")
	public List<User> getAllUser() {
		
		return userService.getAllUsers();
	}
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> saveUser(@RequestBody User user, HttpServletRequest request) throws UnknownHostException {
		
		Map<String, String> map = new HashMap<>();
		
//		System.out.println("userName: "+ user.getUserName() + " password: "+ user.getPassword());
//		
//		return userService.saveUser(user);
		
		System.out.println("Email: " + user.getEmail());
//		ResponseEntity<Object> responseEntity = loginService.loginUser(user);
//		return responseEntity;
		map.put("response", "Email already exists!");
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			return ResponseEntity.ok(map);
		} else {

			System.out.println("hi.....");
			
//			String portNumber = environment.getProperty("server.port");
//			String hostAddress = InetAddress.getLocalHost().getHostAddress();

			ConfirmationToken confirmationToken = new ConfirmationToken(user);

			user.setActive(false);
			userService.save(user);
			confirmationTokenRepository.save(confirmationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("bushanvideos2020@gmail.com");
//			mailMessage.setText("To confirm your account, please click here : " + envUtil.getServerUrlPrefi()
//					+ request.getContextPath() + "/confirm-account?token=" + confirmationToken.getConfirmationToken());
			
			mailMessage.setText("To confirm your account, please click here : " + envUtil.getAngularUrl()
			+ request.getContextPath() + "/" + confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(mailMessage);
			map.put("response", "Registerd successfully..");
		}
		return ResponseEntity.ok(map);
	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<Map<String, String>> confirmUserAccount(@RequestParam(value = "token") String confirmationToken) throws UnknownHostException {
		
		Map<String, String> map = new HashMap<>();
		try {
						
			ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
			
//			if(token != null && token.getUser().getActive()) {
//				map.put("response", "Already Verified");
//				return ResponseEntity.ok(map);
//			}	
			
			
			if (token != null) {			
				
				User user = userService.findUserByEmail(token.getUser().getEmail());
				user.setActive(true);
				userService.save(user);
				
				map.put("response", "Account Verified");
				
//				RedirectView redirectView = new RedirectView();
//			    redirectView.setUrl(envUtil.getAngularUrl()+"/Account Verified");
//			    return redirectView;
				
			} else 
			{
				map.put("message", "The link is invalid or broken! Please contact support team!");
				map.put("response", "Something Wrong!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return ResponseEntity.ok(map);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		
		userService.deleteById(id);
	}
}
