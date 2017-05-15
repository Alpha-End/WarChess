package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.ViewData;

public class MainFrame extends JFrame{
	public MainFrame() {
		// TODO Auto-generated constructor stub
		super("盗梦宗师 战棋版");
		this.setSize(ViewData.FRAMEW, ViewData.FRAMEH);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.addKeyListenerToKeyThread();
		
		ViewData.mainpanel=new MainPanel();
		this.add(ViewData.mainpanel);
		Thread t=new Thread(ViewData.mainpanel);
		t.start();
		
	}
	private void addKeyListenerToKeyThread(){
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				dataControl.KeyListener.releaseKey(e);
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				dataControl.KeyListener.getKey(arg0);
			}
		});
	}
}
