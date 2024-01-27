package com.example.demo.model;

import java.util.List;
import java.util.ArrayList;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_table")
public class UserTable {

	@Id
	@Column(name="user_id")
	Integer user_id;
	
	@Column(name="subscription_plan")
	String subscription_plan;
	
	@Column(name="available_screen")
	Integer available_screen;
	
	private List<Integer> watchingMovies;
	 
	
	public UserTable() {
		
	}

	public UserTable(Integer user_id, String subscription_plan, Integer available_screen, List<Integer> watchingMovies ) {
		this.user_id = user_id;
		this.subscription_plan = subscription_plan;
		this.available_screen = available_screen;
		this.watchingMovies = new ArrayList<>();

	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getSubscription_plan() {
		return subscription_plan;
	}

	public void setSubscription_plan(String subscription_plan) {
		this.subscription_plan = subscription_plan;
	}

	public Integer getAvailable_screen() {
		return available_screen;
	}

	public void setAvailable_screen(Integer available_screen) {
		this.available_screen = available_screen;
	}
	
	 public List<Integer> getWatchingMovies() {
	        return watchingMovies;
    }

    public void setWatchingMovies(List<Integer> watchingMovies) {
        this.watchingMovies = watchingMovies;
    }

    public void setAvailableScreensBySubscription(String subscriptionPlan) {
        switch (subscriptionPlan) {
            case "Basic":
                this.available_screen = 1;
                break;
            case "Standard":
                this.available_screen = 2;
                break;
            case "Premium":
                this.available_screen = 3;
                break;
            default:
                // Handle any other subscription plans if needed
                this.available_screen = 0;
                break;
        }
    }
}
