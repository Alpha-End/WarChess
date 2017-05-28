package view;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import data.ViewData;
import data.XMLData;
import dataControl.StateControl;
import entity.Figure;
import entity.Map;
import game.CursorMove;
import interFace.PaintInterface;
import util.R;

public class Game implements PaintInterface,Runnable,interFace.StateControl{
	public Map map;
	int gamestate;
	final int BLUEROUNDSTART=0,REDROUNDSTART=1,CURSORMOVE=2,FIGURESELECTED=3,FIGUREMOVE=4,FIGUREMOVING=5;//蓝方回合开始，红方回合开始，光标自由移动，人物选中,人物移动，人物移动中
	public ArrayList<Figure> bluefigurelist,redfigurelist;
	
	CursorMove cursormove;
	public Game() {
		// TODO Auto-generated constructor stub
		
		int[][] l=new int[][]{{1,1,2,100,1,100,100,100,100,100,100,100,1,100,100},
			{100,1,100,100,100,1,100,100,100,100,100,100,100,100,100},
			{100,1,1,1,100,100,100,100,100,100,100,100,100,100,100},
			{1,1,1,1,1,100,100,100,100,100,100,100,100,100,100},
			{100,100,1,1,1,100,100,100,100,1,100,100,100,100,100},
			{100,100,100,100,1,1,1,1,1,1,100,100,100,100,1},
			{100,100,100,100,100,100,100,100,1,2,100,100,100,1,100},
			{100,100,100,100,100,100,100,100,100,1,2,1,1,2,1},
			{100,100,100,100,100,100,100,100,100,100,1,1,1,1,2},
			{100,100,100,100,100,100,100,100,100,100,100,1,1,1,1}};
		map=new Map(10, 15, l);
		map.roundstate=map.BLUE;

		map.setMap_img(R.load("img/map_0.png"));
		
		bluefigurelist=new ArrayList<>();
		
		redfigurelist=new ArrayList<>();
		
		gamestate=CURSORMOVE;
		
		BufferedImage rider=R.load("img/figure_rider_blue.png");
		Figure figure=new Figure(4, 4, "赛斯", 30, 13, 22, 11, 0, 8, map.BLUE, rider);
		bluefigurelist.add(figure);
		
		cursormove=new CursorMove(this);
	}
	@Override
	public void paint(Graphics e) {
		// TODO Auto-generated method stub
		map.paint(e);
		paintFigure(e);
		switch(gamestate){
		case CURSORMOVE:{cursormove.paint(e);}
		}
	}
	
	void paintFigure(Graphics e){
		for(int i=0;i<bluefigurelist.size();i++){
			Figure f=bluefigurelist.get(i);
			if((f.getX()-map.getX()<ViewData.FRAMEW/ViewData.MAP_PIXEL)&&(f.getX()-map.getX()>0)&&(f.getY()-map.getY()<ViewData.FRAMEH/ViewData.MAP_PIXEL)&&(f.getY()-map.getY()>0)){
				int x=f.getX()-map.getX(),y=f.getY()-map.getY();
				e.drawImage(f.getFigureimg(), x*ViewData.MAP_PIXEL, y*ViewData.MAP_PIXEL,null);
			}
		}
		for(int i=0;i<redfigurelist.size();i++){
			Figure f=redfigurelist.get(i);
			if((f.getX()-map.getX()<ViewData.FRAMEW/ViewData.MAP_PIXEL)&&(f.getX()-map.getX()>0)&&(f.getY()-map.getY()<ViewData.FRAMEH/ViewData.MAP_PIXEL)&&(f.getY()-map.getY()>0)){
				int x=f.getX()-map.getX(),y=f.getY()-map.getY();
				e.drawImage(f.getFigureimg(), x*ViewData.MAP_PIXEL, y*ViewData.MAP_PIXEL,null);
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ViewData.state=ViewData.GAME;
	}
	
	public void getKey(KeyEvent e){
		if(gamestate==CURSORMOVE){
			cursormove.cursorMove(e);
		}
	}
	@Override
	public void start() {
		// TODO Auto-generated method stub
		if(!XMLData.next_action_id.equals(ViewData.now_action_id)){//若内存中台词过期则进入LOAD界面，并读取
			ViewData.now_action_id=new String(XMLData.next_action_id);//更新剧情ID
			StateControl.start(ViewData.load);//进入LOAD界面
			
			Thread t=new Thread(this);//开启新线程，读取
			t.start();
			
			
		}
		else{
			ViewData.state=ViewData.GAME;//进入游戏界面
		}
	}
	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}	

}
