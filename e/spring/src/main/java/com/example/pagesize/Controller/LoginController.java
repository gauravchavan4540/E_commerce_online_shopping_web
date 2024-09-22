package com.example.pagesize.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pagesize.Repo.UserRepo;
import com.example.pagesize.modul.LoginReturn;
import com.example.pagesize.modul.User;

@RestController
@CrossOrigin  //(origins = { "http://localhost:4200","http://3.93.19.80:8080/amazon"})
@RequestMapping("login")
public class LoginController {
	@Autowired
	UserRepo userRepo;

	@GetMapping("test")
	public String samle() {
		return "Welcome";
	}

	@RequestMapping("getName{id}")
	public String[] getName(@PathVariable int id) {
		User user = userRepo.findById(id).get();
		String[] sa = new String[1];
		sa[0] = user.getName();
		return sa;
	}

	@RequestMapping("log")
	public LoginReturn login(@RequestBody String[] sa) {
		if (sa == null)
			return new LoginReturn(-1, -1, "Currepted Data");
		String username = sa[0];
		if (username == null || username.length() < 1)
			return new LoginReturn(-1, -1, "Enter UserName");
		String password = sa[1];
		if (password == null || password.length() < 1)
			return new LoginReturn(-1, -1, "Enter Password");
		int count = userRepo.countByUserName(username);
		if (count == 0)
			return new LoginReturn(-1, -1, "Wrong UserName");
		if (count > 1)
			return new LoginReturn(-1, -1, "User Already Present");
		User user = userRepo.findByUserName(username);
		if (user.getPassword().equals(password)) {
			return new LoginReturn(user.getId(), user.getAccountType(), null);
		} else
			return new LoginReturn(-1, -1, "Wrong Password");
	}
}