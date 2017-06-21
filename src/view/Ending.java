package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import data.ViewData;
import data.XMLData;
import interFace.PaintInterface;
import interFace.StateControl;
import util.R;

public class Ending implements PaintInterface,StateControl{
	BufferedImage victory,defeat;
	public Ending() {
		// TODO Auto-generated constructor stub
		victory=R.load(XMLData.VICTORY_IMG);
		defeat=R.load(XMLData.DEFEAT_IMG);
	}
	@Override
	public void paint(Graphics e) {
		// TODO Auto-generated method stub
		if(ViewData.victory){
			e.drawImage(victory, 0, 0, null);
			
		}
		else{
			e.drawImage(defeat, 0, 0, null);
		}
		
	}
	public void start(){
		ViewData.state=ViewData.ENDING;
	}
	public void end(){
		
	}
}