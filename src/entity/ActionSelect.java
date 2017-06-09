package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import data.ViewData;
import data.XMLData;
import util.R;

public class ActionSelect {
	public static final int AWAIT=1,ATTACK=0;
	final int OPTIONMAXCOUNT=2;
	boolean[] optionlist;
	public int index;
	static BufferedImage action_select_box;
	static BufferedImage action_selected_box;
	public ActionSelect() {
		// TODO Auto-generated constructor stub
		optionlist=new boolean[OPTIONMAXCOUNT];
		
		if(action_select_box==null){			
			action_select_box=R.load(XMLData.ACTION_SELECT_BOX_IMG_PATH);
		}
		if(action_selected_box==null){			
			action_selected_box=R.load(XMLData.ACTION_SELECTED_BOX_IMG_PATH);
		}
	}
	public void insertOption(int option){
		optionlist[option]=true;
		for(int i=0;i<OPTIONMAXCOUNT;i++){
			if(optionlist[i]){
				index=i;
				return;
			}
		}
	}
	
	public void nextOption(){
		int i=index;
		while(i<OPTIONMAXCOUNT-1){
			i++;
			if(optionlist[i]){
				index=i;
				return;
			}
		}
	}
	public void lastOption(){
		int i=index;
		while(i>0){
			i--;
			if(optionlist[i]){
				index=i;
				return;
			}
		}
	}
	public void paint(Graphics g,int relative_x,int relative_y){
		int x=relative_x,y=relative_y;
		g.setFont(ViewData.ACTIONSELECTBOXFONT);
		
		if(optionlist[ATTACK]){
			paintSingle(g, "攻击", x, y, ATTACK);
			
			y+=action_select_box.getHeight();
		}
		if(optionlist[AWAIT]){
			paintSingle(g, "待机", x, y, AWAIT);
			
			y+=action_select_box.getHeight();
		}
	}
	
	void paintSingle(Graphics g,String s,int x,int y,int i){
		if(i==index){
			g.drawImage(action_selected_box, x, y, null);
		}
		else{
			g.drawImage(action_select_box, x, y, null);
			
		}
		int font_x=(action_select_box.getWidth()-ViewData.ACTIONSELECTBOXFONT.getSize()*2)/2,font_y=(action_select_box.getHeight()+ViewData.ACTIONSELECTBOXFONT.getSize())/2;
		g.drawString(s, x+font_x, y+font_y);
	}
}
