package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import data.ViewData;
import data.XMLData;
import interFace.PaintInterface;
import interFace.StateControl;
import util.R;

public class Welcome implements PaintInterface,StateControl{
	ArrayList<BufferedImage> welcome;
	int index;
	long lastpainttime;//上一次界面绘制时间
	public Welcome() {
		// TODO Auto-generated constructor stub
		welcome=new ArrayList<>();
		index=0;
		lastpainttime=System.currentTimeMillis();
		for(int i=0;i<XMLData.WELCOME_IMG_PATH.length;i++){
			welcome.add(R.load(XMLData.WELCOME_IMG_PATH[i]));
		}
	}
	@Override
	public void paint(Graphics e) {
		// TODO Auto-generated method stub
		if(index<welcome.size()){
			
			e.drawImage(welcome.get(index%welcome.size()),0,0,null);
			if(System.currentTimeMillis()-lastpainttime>ViewData.INTERVAL_TIME){
				lastpainttime=System.currentTimeMillis();
				index++;
			}
		}
		else{
			e.drawImage(welcome.get(welcome.size()-1),0,0,null);
		}
	}
	public void start(){
		ViewData.state=ViewData.WELCOME;
	}
	public void end(){
		
	}
}
