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
import game.RedFigureAction;
import interFace.PaintInterface;
import util.R;

public class Game implements PaintInterface,Runnable,interFace.StateControl{
	public Map map;
	int gamestate;
	final int BLUEROUNDSTART=0,REDROUNDSTART=1,CURSORMOVE=2;//蓝方回合开始，红方回合开始，光标自由移动，人物选中,人物移动，人物移动中
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
			
		int[][] ll=new int[15][10];	
		for(int i=0;i<10;i++){
			for(int j=0;j<15;j++){
				ll[j][i]=l[i][j];
			}
		}
		map=new Map(10, 15, ll);
		map.roundstate=map.BLUE;

		map.setMap_img(R.load("img/map_0.png"));
		
		bluefigurelist=new ArrayList<>();
		
		redfigurelist=new ArrayList<>();
		
		gamestate=CURSORMOVE;
		
		BufferedImage soilder=R.load("img/figure_soldier_blue.png");
		Figure figure=new Figure(4, 5, "王五", 30, 30, 21, 6, 0, 6, map.BLUE, soilder);
		bluefigurelist.add(figure);
		soilder=R.load("img/figure_soldier_red.png");
		figure=new Figure(8, 6, "跟班1", 23, 23, 13, 2, 0, 5, map.RED, soilder);
		redfigurelist.add(figure);
		figure=new Figure(9, 6, "跟班2", 20, 20, 13, 2, 0, 5, map.RED, soilder);
		redfigurelist.add(figure);
		figure=new Figure(10, 8, "亨利", 23, 23, 14, 2, 0, 5, map.RED, soilder);
		redfigurelist.add(figure);
		soilder=R.load("img/figure_archer_red.png");
		//figure=new Figure()
		
		for(int i=0;i<bluefigurelist.size();i++){
			map.addFigure(bluefigurelist.get(i).getX(), bluefigurelist.get(i).getY(), bluefigurelist.get(i));
		}
		for(int i=0;i<redfigurelist.size();i++){
			map.addFigure(redfigurelist.get(i).getX(), redfigurelist.get(i).getY(), redfigurelist.get(i));
		}
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
	
	
	
	public static void checkFigureList(ArrayList<Figure> bluefigurelist,ArrayList<Figure> redfigurelist){
		int b=0,r=0;;
		for(int i=0;i<bluefigurelist.size();i++){
			if(bluefigurelist.get(i).isAlive()){
				b++;
			}
		}
		for(int i=0;i<redfigurelist.size();i++){
			if(redfigurelist.get(i).isAlive()){
				r++;
			}
		}
		if(b==0){
			ViewData.victory=false;
			ViewData.state=ViewData.ENDING;
		}
		if(r==0){
			ViewData.victory=true;
			ViewData.state=ViewData.ENDING;
		}
	}
	void paintFigure(Graphics e){
		for(int i=0;i<bluefigurelist.size();i++){
			Figure f=bluefigurelist.get(i);
			if(f.isAlive()&&(f.getX()-map.getX()<ViewData.FRAMEW/ViewData.MAP_PIXEL)&&(f.getX()-map.getX()>=0)&&(f.getY()-map.getY()<ViewData.FRAMEH/ViewData.MAP_PIXEL)&&(f.getY()-map.getY()>=0)){
				int x=f.getX()-map.getX(),y=f.getY()-map.getY();
				e.drawImage(f.getFigureimg(), x*ViewData.MAP_PIXEL+(ViewData.MAP_PIXEL-f.getFigureimg().getWidth())/2, y*ViewData.MAP_PIXEL+(ViewData.MAP_PIXEL-f.getFigureimg().getHeight())/2,null);
				//System.out.println(f.getHp());
			}

		}

		for(int i=0;i<redfigurelist.size();i++){
			Figure f=redfigurelist.get(i);
			if(f.isAlive()&&(f.getX()-map.getX()<ViewData.FRAMEW/ViewData.MAP_PIXEL)&&(f.getX()-map.getX()>=0)&&(f.getY()-map.getY()<ViewData.FRAMEH/ViewData.MAP_PIXEL)&&(f.getY()-map.getY()>=0)){
				int x=f.getX()-map.getX(),y=f.getY()-map.getY();
				e.drawImage(f.getFigureimg(), x*ViewData.MAP_PIXEL+(ViewData.MAP_PIXEL-f.getFigureimg().getWidth())/2, y*ViewData.MAP_PIXEL+(ViewData.MAP_PIXEL-f.getFigureimg().getHeight())/2,null);
				//System.out.println(f.getHp());
			}

		}
		checkFigureList(bluefigurelist, redfigurelist);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ViewData.state=ViewData.GAME;
	}
	
	public void getKey(KeyEvent e){
		if(gamestate==CURSORMOVE&&cursormove.isLoading()&&e.getKeyCode()==KeyEvent.VK_ENTER){
			redRoundStart();
		}
		else if(gamestate==CURSORMOVE){
			cursormove.cursorMove(e);
		}
	}
	void redRoundStart(){
		for(int i=0;i<bluefigurelist.size();i++){
			Figure figure=bluefigurelist.get(i);
			if(figure.isAlive()){
				bluefigurelist.get(i).setMoveable(true);
			}
			
		}
		gamestate=REDROUNDSTART;
		RedFigureAction rd=new RedFigureAction(this);
		rd.redFigureAction();
		map.refreshMap();

		gamestate=CURSORMOVE;
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
