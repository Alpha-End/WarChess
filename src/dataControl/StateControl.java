package dataControl;

import data.ViewData;
import data.XMLData;

public class StateControl {
	public static void start(interFace.StateControl state){
		state.start();
	}
	public static void end(interFace.StateControl state){
		state.end();
	}
	public static void next_action(){
		switch(XMLData.next_action){
		case ViewData.PLOT:{start(ViewData.plot);break;}
		case ViewData.SELECT:{start(ViewData.select);break;}
		}
	}
	
	
}
