package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserTable;
import com.example.demo.service.UserTableService;

@RestController
public class UserTableController {

	
	@Autowired
	UserTableService usertableservice;
	
	  @GetMapping("/{userId}/subscription")
	    public String getSubscriptionPlan(@PathVariable Integer userId) {
	        UserTable user = usertableservice.getUserById(userId);
	        if (user != null) {
	            return user.getSubscription_plan();
	        } else {
	            return "There are no user found";
	        }
	    }
	  
	  
	  @PostMapping("/{userId}/subscription")
	  public String setSubscriptionPlan(@PathVariable Integer userId, @RequestBody Map<String, String> subscription) {
	      UserTable user = usertableservice.getUserById(userId);
	      if (user != null) {
	          // Extract the subscription plan value from the JSON object
	          String subscriptionPlan = subscription.get("subscription_plan");

	          // Update the subscription plan
	          user.setSubscription_plan(subscriptionPlan);
	          
	          // Update the available screens based on the subscription plan
	          int availableScreens = 0;
	          switch (subscriptionPlan) {
	              case "Basic":
	                  availableScreens = 1;
	                  break;
	              case "Standard":
	                  availableScreens = 2;
	                  break;
	              case "Premium":
	                  availableScreens = 3;
	                  break;
	              // Handle any other subscription plans if needed
	          }
	          user.setAvailable_screen(availableScreens);
	          
	          usertableservice.saveUser(user);
	          return "Successfully updated the subscription plan";
	      } else {
	          return "User not found";
	      }
	  }

	  
	  @PostMapping("/addUser")
	    public String addUser(@RequestBody UserTable newUser) {
	        // Set available screens based on the subscription plan
	        newUser.setAvailableScreensBySubscription(newUser.getSubscription_plan());

	        usertableservice.saveUser(newUser);
	        return "successfully added a user ";
	    }
	  
	  

}
