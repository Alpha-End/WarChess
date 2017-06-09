package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import data.ViewData;
import data.XMLData;
import util.R;

public class EnermySelect {
	Figure figure;
	ArrayList<Figure> enermylist;
	int index;
	static BufferedImage enermy_select_box;
	int paint_x,paint_y;
	public EnermySelect(Figure figure) {
		// TODO Auto-generated constructor stub
		this.figure=figure;
		enermylist=new ArrayList<>();
		index=0;
		if(enermy_select_box==null){
			enermy_select_box=R.load(XMLData.ENERMY_SELECT_BOX_IMG_PATH);
		}
	}
	public void addEnermyList(ArrayList<Figure> e){
		enermylist=e;
	}
	public void addEnermy(Figure enermy){
		enermylist.add(enermy);
	}
	public void nextEnermy(){
		index++;
		if(index==enermylist.size()){
			index=0;
		}
	}
	public void lastEnermy(){
		index--;
		if(index<0){
			index=enermylist.size()-1;
		}
	}
	public Figure getEnermy(){
		return enermylist.get(index);
	}
	public void paint(Graphics g,int x,int y){
		g.drawImage(enermy_select_box, x, y, null);
		Figure figure=enermylist.get(index);
		g.setFont(ViewData.ENERMYSELECTBOXFONT);
		paint_y=0;
		
		
		paintText(g, x, y, this.figure.name+" vs "+figure.name);
		paintText(g, x, y, this.figure.hp+" 血量 "+figure.hp);
		paintText(g, x, y, (this.figure.attack-figure.defense)+" 伤害 "+(figure.attack-this.figure.defense));
		
		
	}
	void paintText(Graphics g,int x,int y,String s){
		paint_x=(enermy_select_box.getWidth()-s.length()*ViewData.ENERMYSELECTBOXFONT.getSize());
		paint_y+=ViewData.ENERMYSELECTBOXFONT.getSize()+20;
		g.drawString(s, x+paint_x, y+paint_y);
		
	}
	public void attackEnermy(){
		figure.attackAnotherFigure(getEnermy());
	}
}
