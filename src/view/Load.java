package view;

import java.awt.Graphics;

import data.ViewData;
import interFace.PaintInterface;
import interFace.StateControl;

public class Load implements PaintInterface,StateControl{
	@Override
	public void paint(Graphics e) {
		// TODO Auto-generated method stub
		
	}
	public void start(){
		ViewData.state=ViewData.LOAD;
	}
	public void end(){
		
	}
}
