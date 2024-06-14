package cmpt276.asn2.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cmpt276.asn2.models.User;
import cmpt276.asn2.models.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class UsersController {

  @Autowired
  private UserRepository userRepo;

  @GetMapping("/users/view")
  public String getAllUsers(Model model) {
    System.out.println("Getting all users");

    List<User> users = userRepo.findAll();
    // end of database call
    model.addAttribute("us", users);
    return "showAll";
  }

  @GetMapping("/users/view/{uid}")
  public String getUser(Model model, @PathVariable String uid) {

    System.out.println("GET User " + uid);
    // call db

    return "showUser";
  }

  // data coming from form would be a PostMapping
  @PostMapping("/users/add")
  public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response) {
    System.out.println("ADD user");
    String newName = newuser.get("name");
    String newColour = newuser.get("colour");
    int newWidth = Integer.parseInt(newuser.get("width"));
    int newHeight = Integer.parseInt(newuser.get("height"));
    userRepo.save(new User(newName, newColour, newWidth, newHeight));
    response.setStatus(201);
    return "addedUser";
  }

}