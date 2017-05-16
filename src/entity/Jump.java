package entity;

import data.ViewData;
import data.XMLData;

public class Jump {
	String next_action;
	String next_action_path;
	String next_action_id;
	
	public void jumpThisAction(){
		switch(next_action){
		case "SELECT":{XMLData.next_action=ViewData.SELECT;break;}
		case "PLOT":{XMLData.next_action=ViewData.PLOT;break;}
		}
		XMLData.next_action_id=this.next_action_id;
		XMLData.next_action_path=this.next_action_path;
	}

	public String getNext_action() {
		return next_action;
	}

	public void setNext_action(String next_action) {
		this.next_action = next_action;
	}

	public String getNext_action_path() {
		return next_action_path;
	}

	public void setNext_action_path(String next_action_path) {
		this.next_action_path = next_action_path;
	}

	public String getNext_action_id() {
		return next_action_id;
	}

	public void setNext_action_id(String next_action_id) {
		this.next_action_id = next_action_id;
	}

	public Jump(String next_action, String next_action_path, String next_action_id) {
		super();
		this.next_action = next_action;
		this.next_action_path = next_action_path;
		this.next_action_id = next_action_id;
	}
	
}
