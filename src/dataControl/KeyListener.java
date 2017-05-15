package dataControl;

import java.awt.event.KeyEvent;

import data.ViewData;
import data.XMLData;

public class KeyListener{
	public static void getKey(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		if(ViewData.state==ViewData.WELCOME){
			getKeyInWelcome(e);
		}
		if(ViewData.state==ViewData.PLOT){
			getKeyInPlot(e);
		}
		/*
		if(e.getKeyCode()==KeyEvent.VK_X){
			ViewData.state=ViewData.PLOT;
		}
		if(e.getKeyCode()==KeyEvent.VK_Z){
			ViewData.state=ViewData.LOAD;
		}
		*/
		
		
	}
	private static void getKeyInPlot(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_X){
			ViewData.plot.nextDialogue();
		}
	}
	private static void getKeyInWelcome(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			StateControl.end(ViewData.welcome);
			XMLData.dialogue_path="src/xml/dialogue_0.xml";
			XMLData.next_xml_action_id="dialogue_0";
			XMLData.next_action=ViewData.PLOT;
			StateControl.next_action();
		}
	}
	public static void releaseKey(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
