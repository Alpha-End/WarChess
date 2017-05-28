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
		if(ViewData.state==ViewData.SELECT){
			getKeyInSelect(e);
		}
		
		if(ViewData.state==ViewData.GAME){
			ViewData.game.getKey(e);
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
			ViewData.plot.keyPressed();
		}
	}
	private static void getKeyInSelect(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode()==KeyEvent.VK_UP){
			ViewData.select.UP_KEY();;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			ViewData.select.DOWN_KEY();;
		}
		if(e.getKeyCode()==KeyEvent.VK_X){
			ViewData.select.selectOption();
		}
	}
	private static void getKeyInWelcome(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			StateControl.end(ViewData.welcome);
			XMLData.next_action_path="src/xml/dialogue_0.xml";
			XMLData.next_action_id="dialogue_0";
			XMLData.next_action=ViewData.PLOT;
			StateControl.next_action();
		}
	}
	public static void releaseKey(KeyEvent e) {
		// TODO Auto-generated method stub
		if(ViewData.state==ViewData.PLOT){
			releaseKeyInPlot(e);
		}
	}
	private static void releaseKeyInPlot(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_X){
			ViewData.plot.keyReleased();
		}
	}
}
