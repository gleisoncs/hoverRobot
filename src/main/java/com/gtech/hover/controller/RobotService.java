package com.gtech.hover.controller;

import javax.inject.Named;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Named
public class RobotService {

	private String comando = "";

	private int ptX = 0;
	private int ptY = 0;
	private int maxX_size = 5;
	private int maxY_size = 5;
	
	private String direction = "N";

	private String northDirection = "N";
	private String southDirection = "S";
	private String eastDirection = "E";
	private String westDirection = "W";
	private String leftCommand = "L";
	private String rightCommand = "R";
	private String moveCommand = "M";

	public RobotService(String arg1) {
		this.comando = arg1;
	}

	public String getPosition(){
		if (preValidation() == false) 
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		
		parseCommand();
		
		if (postValidation()) 
			return String.format("(%s, %s, %s)", String.valueOf(ptX), String.valueOf(ptY), direction);
		else 
			throw new WebApplicationException(Response.Status.NOT_FOUND);
	}

	private boolean preValidation() {
		if (comando.equals("")) {
			return false;
		}
		String[] commandoParsed = comando.split("");
		for (int i = 0; i < commandoParsed.length; i++) {
			String arg = (String) commandoParsed[i] ;
			if ("LRM".indexOf(arg) < 0)
				return false;
		}
		return true;
	}

	private boolean postValidation() {
		return ((ptX > maxX_size) || (ptY > maxY_size) || (ptX < 0) || (ptY < 0)) ? false : true;
	}

	private void parseCommand() {
		String[] commandoParsed = comando.split("");
		for (int i = 0; i < commandoParsed.length; i++) {
			String arg = (String) commandoParsed[i] ;
			doCommand(arg);
		}
	}

	private void doCommand(String command) {
		if (command.equals(leftCommand)) {
			if (direction.equals(northDirection)) {
				doSpin(westDirection);
			} else if (direction.equals(westDirection)) {
				doSpin(southDirection);
			} else if (direction.equals(southDirection)) {
				doSpin(eastDirection);
			} else if (direction.equals(eastDirection)) {
				doSpin(northDirection);
			}
		} else if (command.equals(rightCommand)) {
			if (direction.equals(northDirection)) {
				doSpin(eastDirection);
			} else if (direction.equals(eastDirection)) {
				doSpin(southDirection);
			} else if (direction.equals(southDirection)) {
				doSpin(westDirection);
			} else if (direction.equals(westDirection)) {
				doSpin(northDirection);
			}
		} else if (command.equals(moveCommand)) {
			doMove();
		}
	}

	private void doMove() {
		if (direction.equals(northDirection)) {
			ptY = ptY + 1;
		} else if (direction.equals(eastDirection)) {
			ptX = ptX + 1;
		} else if (direction.equals(southDirection)) {
			ptY = ptY - 1;
		} else if (direction.equals(westDirection)) {
			ptX = ptX - 1;
		}
	}

	private void doSpin(String direction) {
		this.direction = direction;
	}
}