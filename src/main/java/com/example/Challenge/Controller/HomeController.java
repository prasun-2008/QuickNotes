package com.example.Challenge.Controller;

import com.example.Challenge.Model.Notes;
import com.example.Challenge.Model.Question;
import com.example.Challenge.Model.UserModel;
import com.example.Challenge.Services.EmailService;
import com.example.Challenge.Services.NotesServices;
import com.example.Challenge.Services.QuestionService;
import com.example.Challenge.Services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Controller
public class HomeController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private QuestionService questionService;

    private Random rand = new Random();

    @Autowired
    private UserService userService;

    @Autowired
    private NotesServices notesServices;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // If user is authenticated and not anonymous, redirect or show error
        if (auth != null && auth.isAuthenticated() &&
                !(auth instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("errorMessage", "You are already logged in.");
            return "index";  // Or any page you want to show the error on
        }
        return "Login";
    }

//    @PostMapping("/login")
//    public String login(@RequestParam String email, @RequestParam String password) {
//
//    }



    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/error")
    @ResponseBody
    public String error() {
        return "under maintenance sir";
    }

    @GetMapping("/notes")
    public String notes(Model model) {
        List<Notes> notes = notesServices.getAllNotes();
        model.addAttribute("notes", notes);

        return "Notes";
    }

    @GetMapping("/questions")
    public String questions(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String email = auth.getName();
        UserModel user = userService.findByEmail(email);

        model.addAttribute("fullname", "Welcome! " + user.getFullName());
        model.addAttribute("questions", questionService.getAllQuestions()); // this line is key

        return "Questions"; // must match your Thymeleaf file: templates/Questions.html
    }


    @PostMapping("/message")
    public String message(@RequestParam String name , @RequestParam String subject ,@RequestParam String message, @RequestParam String email) {
        System.out.println(message);
        emailService.sendEmail(email, subject, message, name);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); // This gives the logged-in username (email)

        // Now fetch full user info from DB using email
        UserModel user = userService.findByEmail(email);  // You need to add this method in UserService




        // If user is authenticated and not anonymous, redirect or show error
        if (auth != null && auth.isAuthenticated() &&
                !(auth instanceof AnonymousAuthenticationToken)) {
            model.addAttribute("errorMessage", "You are already logged in.");
            return "index";  // Or any page you want to show the error on
        }
        return "Register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String fullname , @RequestParam String email, @RequestParam String password, Model model, HttpSession session) {

        UserModel user = new UserModel(fullname, email, password);
        if (userService.emailExists(email)) {
            model.addAttribute("errorMessage", "Email is already registered. Please use a different email.");
            return "Register";  // Return to register page with error
        }

        int OTP = rand.nextInt(100000,999999);
        session.setAttribute("OTP", OTP);
        session.setAttribute("user", user);
        emailService.sendOTP(email, OTP);


        return "verify";
    }



    @PostMapping("/verify")
    public String verify(@RequestParam int code, HttpSession session, Model model) {
        Integer otp = (Integer) session.getAttribute("OTP");
        System.out.println(otp);
        UserModel user = (UserModel) session.getAttribute("user");
        System.out.println(user);


        if (otp == null || user == null) {
            model.addAttribute("errorMessage", "Session expired or invalid. Please register again.");
            return "Register";
        }

        if (otp == code) {
            userService.registerUser(user);
            session.removeAttribute("OTP");
            session.removeAttribute("user");
            return "Login";
        } else {
            model.addAttribute("errorMessage", "Invalid OTP. Please try again.");
            return "verify";
        }
    }

    @GetMapping("/notes/{id}")
    public String notesDetails(Model model, @PathVariable int id) {
        Notes notes = notesServices.getNote(id);
        model.addAttribute("note", notes);
        return "NotesDetail";
    }

    @GetMapping("/questions/{id}")
    public String viewQuestion(@PathVariable Long id, Model model) {
        Question question = questionService.getById(id);
        model.addAttribute("question", question);
        return "ViewQuestion"; // You’ll need ViewQuestion.html
    }


}