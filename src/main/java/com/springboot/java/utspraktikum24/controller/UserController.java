package com.springboot.java.utspraktikum24.controller;

import com.springboot.java.utspraktikum24.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.springboot.java.utspraktikum24.repositories.UserRepositories;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller

public class UserController {
	@Autowired
	UserRepositories userRepositories;

	@GetMapping("/registration")
	public String getRegPage(@ModelAttribute("user") UserModel user) {
		return "registrasi";
	}

	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("user") UserModel user, Model model, RedirectAttributes redirectAttributes) {
		userRepositories.save(user);
		model.addAttribute("message", "Submitted Successfully");

		// Redirect to the login page after successful registration
		redirectAttributes.addFlashAttribute("successMessage", "Registration successful. Please Login.");
		return "redirect:/login";
	}

	@GetMapping("/login")
	public String getLoginPage(Model model, @ModelAttribute("user") UserModel user) {
		return "login";
	}

	@PostMapping("/authenticate")
	public String authenticateUser(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) {
		// See if the user exists in the database
		UserModel user = userRepositories.findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			// The authentication successfully, go to dashboard
			return "redirect:/dashboard";
		} else {
			model.addAttribute("error", "Invalid username or password");
			return "login"; // Back to login form
		}
	}

	/*
	 * @GetMapping("/admin-index") public String getAdminIndexPage() { return
	 * "admin/admin-index"; }
	 */

	@GetMapping("/dashboard")
	public String getDashboardPage(Model model) {
		// Add logic to retrieve data for the dashboard if needed
		return "dashboard";
	}

	/*
	 * @GetMapping("/menu") public String getMenuPage(Model model) { // Add logic to
	 * retrieve data for the menu if needed return "menu"; }
	 */

	// Add logic to retrieve data for the contact if needed return "order_pesanan";

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

}