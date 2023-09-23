// package com.jtspringproject.JtSpringProject.controller;

// import java.security.NoSuchAlgorithmException;
// import java.sql.*;
// import java.util.List;

// import javax.servlet.http.Cookie;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import org.apache.jasper.tagplugins.jstl.core.If;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;
// import java.security.MessageDigest;
// import org.springframework.web.servlet.ModelAndView;

// //import com.jtspringproject.JtSpringProject.models.Category;
// //import com.jtspringproject.JtSpringProject.models.Product;
// import com.jtspringproject.JtSpringProject.models.User;
// //import com.jtspringproject.JtSpringProject.services.categoryService;
// //import com.jtspringproject.JtSpringProject.services.productService;
// import com.jtspringproject.JtSpringProject.services.userService;
// import com.mysql.cj.protocol.Resultset;

// import net.bytebuddy.asm.Advice.This;
// import net.bytebuddy.asm.Advice.OffsetMapping.ForOrigin.Renderer.ForReturnTypeName;

// @Controller
// @RequestMapping("/admin")
// public class AdminController {
	
// 	@Autowired
// 	private userService userService;

	
// 	int adminlogcheck = 0;
// 	String usernameforclass = "";
// 	@RequestMapping(value = { "/", "/logout" }, method = RequestMethod.GET)
// 	public String returnIndex(HttpServletRequest request) {
//     // Invalidate the session to log the user out
//     request.getSession().invalidate();
//     return "redirect:/userLogin"; // Redirect to the login page
// }

// 	@RequestMapping(value = {"/","/logout"})
// 	public String returnIndex() {
// 		adminlogcheck =0;
// 		usernameforclass = "";
// 		return "userLogin";
// 	}
	
	
	
// 	@GetMapping("/index")
// 	public String index(Model model) {
// 		if(usernameforclass.equalsIgnoreCase(""))
// 			return "userLogin";
// 		else {
// 			model.addAttribute("username", usernameforclass);
// 			return "index";
// 		}
			
// 	}
	
	
// 	@GetMapping("login")
// 	public String adminlogin() {
		
// 		return "adminlogin";
// 	}
// 	@GetMapping("Dashboard")
// 	public String adminHome(Model model) {
// 		if(adminlogcheck==1)
// 			return "adminHome";
// 		else
// 			return "redirect:/admin/login";
// 	}
// 	@GetMapping("/loginvalidate")
// 	public String adminlog(Model model) {
		
// 		return "adminlogin";
// 	}
// 	@RequestMapping(value = "loginvalidate", method = RequestMethod.POST)
// 	public ModelAndView adminlogin( @RequestParam("username") String username, @RequestParam("password") String pass) {
		
// 		User user=this.userService.checkLogin(username, pass);
		
// 		String userType = user.getuser_type(); // Assuming user.getRole() returns the user's type

//     if ("admin".equals(userType)) {
//         ModelAndView mv = new ModelAndView("adminHome");
//         adminlogcheck = 1;
//         mv.addObject("admin", user);
//         return mv;
//     } else if ("investor".equals(userType)) {
//         ModelAndView mv = new ModelAndView("investorHome");
//         // Handle actions for investor type
//         return mv;
//     } else {
//         ModelAndView mv = new ModelAndView("adminlogin");
//         mv.addObject("msg", "Please enter correct username and password");
//         return mv;
//     }
	
// //In this code, we've added an else if block to check for the "investor" user type. If the user's usertype is "investor," it will be redirected to the "investorHome" page. Make sure to adjust the view names and actions according to your application's requirements and naming conventions.






// 		// if(user.getRole().equals("ROLE_ADMIN")) {
// 		// 	ModelAndView mv = new ModelAndView("adminHome");
// 		// 	adminlogcheck=1;
// 		// 	mv.addObject("admin", user);
// 		// 	return mv;
// 		// }
// 		// else if(user.getRole().equals("ROLE_NORMAL")) {
// 		// 	ModelAndView mv = new ModelAndView("investorHome");
// 		// 	adminlogcheck=1;
// 		// 	mv.addObject("investor", user);
// 		// 	return mv;
// 		// }
// 		// else {
// 		// 	ModelAndView mv = new ModelAndView("adminlogin");
// 		// 	mv.addObject("msg", "Please enter correct username and password");
// 		// 	return mv;
// 		// }
	
		
// 	}

	
	
// 	@GetMapping("profileDisplay")
// 	public String profileDisplay(Model model) {
// 		String displayusername,displaypassword,displayemail,displayaddress;
// 		try
// 		{
// 			Class.forName("com.mysql.jdbc.Driver");
// 			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/startup-portal","root","root1234");
// 			Statement stmt = con.createStatement();
// 			ResultSet rst = stmt.executeQuery("select * from users where username = '"+usernameforclass+"';");
			
// 			if(rst.next())
// 			{
// 			int userid = rst.getInt(1);
// 			displayusername = rst.getString(2);
// 			displayemail = rst.getString(3);
// 			displaypassword = rst.getString(4);
// 			displayaddress = rst.getString(5);
// 			model.addAttribute("userid",userid);
// 			model.addAttribute("username",displayusername);
// 			model.addAttribute("email",displayemail);
// 			model.addAttribute("password",displaypassword);
// 			model.addAttribute("address",displayaddress);
// 			}
// 		}
// 		catch(Exception e)
// 		{
// 			System.out.println("Exception:"+e);
// 		}
// 		System.out.println("Hello");
// 		return "updateProfile";
// 	}
	
// 	@RequestMapping(value = "updateuser",method=RequestMethod.POST)
// 	public String updateUserProfile(@RequestParam("userid") int userid,@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("address") String address) 
	
// 	{
// 		try
// 		{
// 			Class.forName("com.mysql.jdbc.Driver");
// 			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/startup-portal","root","root1234");
// 			//String hashedPassword = hashPassword(password);
// 			PreparedStatement pst = con.prepareStatement("update users set username= ?,email = ?,password= ?, address= ? where uid = ?;");
// 			pst.setString(1, username);
// 			pst.setString(2, email);
// 			pst.setString(3, password);
// 			pst.setString(4, address);
// 			pst.setInt(5, userid);
// 			int i = pst.executeUpdate();	
// 			usernameforclass = username;
// 		}
// 		catch(Exception e)
// 		{
// 			System.out.println("Exception:"+e);
// 		}
// 		return "redirect:/index";
// 	}
// 	// private static String hashPassword(String password) {
//     //     try {
//     //         MessageDigest md = MessageDigest.getInstance("SHA-256");
//     //         byte[] hashBytes = md.digest(password.getBytes());
//     //         StringBuilder hexHash = new StringBuilder();
//     //         for (byte b : hashBytes) {
//     //             hexHash.append(String.format("%02x", b));
//     //         }
//     //         return hexHash.toString();
//     //     } catch (NoSuchAlgorithmException e) {
//     //         e.printStackTrace();
//     //         return null; // Handle the error appropriately in your application
//     //     }
//     // }

// }
