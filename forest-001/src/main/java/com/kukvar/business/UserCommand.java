package com.kukvar.business;

import com.kukvar.hibernate.entity.User;

interface LoggingCommand {
	void login();
	void register();
	void logout();
}

public class UserCommand implements LoggingCommand{

	private User user;
	private boolean logged;
	private Actor actor;
	private Action action;
	
	
	public UserCommand(User user, Actor actor, Action action) {
		super();
		this.user = user;
		this.actor = actor;
		this.action = action;
		this.logged = false;
	}

	@Override
	public void login() {
		// TODO Auto-generated method stub
		System.out.println("login");
		
		switch (actor) {
		case VISITOR: {}
		break;
		case CUSTOMER: {}
		break;
		case ADMIN: {}
		break;
		}
		
		this.logged = true;
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		System.out.println("register");
		
		switch (actor) {
		case CUSTOMER: {}
		break;
		case ADMIN: {}
		break;
		}		
		
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		System.out.println("logout");
		
		this.actor = Actor.VISITOR;
		
		this.logged = false;
	}
		
}
