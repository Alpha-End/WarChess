package view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import data.ViewData;
import data.XMLData;
import entity.Dialogue;
import interFace.PaintInterface;
import interFace.StateControl;
import util.R;

public class Load implements PaintInterface,StateControl{
	ArrayList<BufferedImage> loading;
	int index;
	long lastpainttime;//上一次界面绘制时间
	public Load() {
		// TODO Auto-generated constructor stub
		loading=new ArrayList<>();
		index=0;
		lastpainttime=System.currentTimeMillis();
		for(int i=0;i<XMLData.LOADING_IMG_PATH.length;i++){
			loading.add(R.load(XMLData.LOADING_IMG_PATH[i]));
		}
	}
	@Override
	public void paint(Graphics e) {
		// TODO Auto-generated method stub
		e.drawImage(loading.get(index%loading.size()),0,0,null);
		if(System.currentTimeMillis()-lastpainttime>ViewData.INTERVAL_TIME){
			lastpainttime=System.currentTimeMillis();
			index++;
		}
	}
	public void start(){
		ViewData.state=ViewData.LOAD;
	}
	public void end(){
		
	}
}
