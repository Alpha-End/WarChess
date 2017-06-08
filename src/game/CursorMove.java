package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.text.View;

import data.ViewData;
import data.XMLData;
import entity.Cell;
import entity.Figure;
import entity.Map;
import util.R;
import view.Game;

public class CursorMove {
	int x,y;
	Map map;
	ArrayList<Figure> bluefigurelist,redfigurelist;
	ArrayList<BufferedImage> cursor_img;
	
	Cell cell;
	Figure selectedfigure;
	int[][] figurereachable;
	long lastpainttime;//上一次界面绘制时间
	
	boolean cursormoving;
	int information_box_x,information_box_y;
	BufferedImage information_box;
	BufferedImage reachable_box;
	
	int cursorindex=0;
	int state;
	final int CURSORMOVE=0,FIGUREMOVE=1,LOADING=2;
	
	public CursorMove(Game g){
		map=g.map;
		bluefigurelist=g.bluefigurelist;
		redfigurelist=g.redfigurelist;
		cursormoving=false;
		cursor_img=new ArrayList<>();
		for(int i=0;i<XMLData.CURSOR_IMG_PATH.length;i++){
			cursor_img.add(R.load(XMLData.CURSOR_IMG_PATH[i]));
		}
		
		x=0;y=0;
		
		state=CURSORMOVE;
		information_box=R.load(XMLData.INFORMATION_BOX_IMG_PATH);
		information_box_x=ViewData.FRAMEW/12;
		information_box_y=ViewData.FRAMEH/16;
		
		reachable_box=R.hyalineProcess(R.load(XMLData.REACHABLE_BOX_IMG_PATH), ViewData.MAP_PIXEL, ViewData.MAP_PIXEL, 0, 0, 6);
		try{
			Figure firstfigure=bluefigurelist.get(0);
			x=firstfigure.getX();
			y=firstfigure.getY();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g){
		if(System.currentTimeMillis()-lastpainttime>ViewData.INTERVAL_TIME){
			lastpainttime=System.currentTimeMillis();
			cursorindex++;
		}
		
		if(state==CURSORMOVE){			
			paintInformationBox(g);
		}
		if(state==FIGUREMOVE){
			paintReachable(g);
		}
		BufferedImage cur_img=cursor_img.get(cursorindex%cursor_img.size());
		int cur_x,cur_y;
		cur_x=(ViewData.MAP_PIXEL-cur_img.getWidth())/2+x*ViewData.MAP_PIXEL;
		cur_y=(ViewData.MAP_PIXEL-cur_img.getHeight())/2+y*ViewData.MAP_PIXEL;
		g.drawImage(cur_img,cur_x,cur_y,null);
		
	}
	void paintReachable(Graphics g){
		int x=0,y=0;
		
		for(int i=map.getX();i<map.getX()+(ViewData.FRAMEW/ViewData.MAP_PIXEL);i++){
			for(int j=map.getY();j<map.getY()+(ViewData.FRAMEH/ViewData.MAP_PIXEL);j++){
				
				if(figurereachable[i][j]>=0){
					g.drawImage(reachable_box, x*ViewData.MAP_PIXEL, y*ViewData.MAP_PIXEL, null);
				}
				
				y++;
			}
			x++;
			y=0;
		}
		
	}
	boolean isReachable(){
		return figurereachable[x+map.getX()][y+map.getY()]>=0;
	}
	void paintInformationBox(Graphics g){
		if(!cursormoving&&cell!=null&&cell.isFigure()){
			
			g.drawImage(information_box, information_box_x, information_box_y, null);
			g.setFont(ViewData.INFORMATIONBOXFONT);
			String s=cell.getFigure().getName();
			g.drawString(s, information_box_x+information_box.getWidth()/20, information_box_y+25+ViewData.INFORMATIONBOXFONT.getSize());
			s="HP "+cell.getFigure().getHp()+"/"+cell.getFigure().getHpmax();
			g.drawString(s, information_box_x+information_box.getWidth()/20, information_box_y+25+ViewData.INFORMATIONBOXFONT.getSize()*2);
			s="攻击力: "+cell.getFigure().getAttack();
			g.drawString(s, information_box_x+information_box.getWidth()/20, information_box_y+25+ViewData.INFORMATIONBOXFONT.getSize()*3);
			s="守备: "+cell.getFigure().getDefense();
			g.drawString(s, information_box_x+information_box.getWidth()/20, information_box_y+25+ViewData.INFORMATIONBOXFONT.getSize()*4);
			
		}
	}
	public void cursorMove(KeyEvent e){
		if(cursormoving){
			return;
		}
		cursormoving=true;
		
		if(state==CURSORMOVE){
			moveCursor(e);			
		}
		else if(state==FIGUREMOVE){
			moveFigure(e);
			moveCursor(e);	
		}
		
		
		cell=map.getCell(x, y);
		cursormoving=false;
	}
	void moveFigure(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_Z){
			state=CURSORMOVE;
		}
		
		
		if(e.getKeyCode()==KeyEvent.VK_X&&selectedfigure.isMoveable()){
			
			map.figureRemove(selectedfigure.getX(), selectedfigure.getY(), x+map.getX(), y+map.getY(), selectedfigure);
			selectedfigure.moveTo(x+map.getX(), y+map.getY());
		}
	}
	
	void moveCursor(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			if(x>0){
				x--;
				
			}
			else{
				if(map.getX()>0){
					map.setX(map.getX()-1);
				}
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			if(x<map.getWidth()-2){
				x++;
				if(x*ViewData.MAP_PIXEL>=ViewData.FRAMEW){
					if(map.getX()+ViewData.FRAMEW/ViewData.MAP_PIXEL<map.getWidth()){
						map.setX(map.getX()+1);
					}
					x--;
				}
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			if(y>0){
				y--;
			}
			else{
				if(map.getY()>0){
					map.setY(map.getY()-1);
				}
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			if(y<map.getHeight()-2){
				y++;
				if(y*ViewData.MAP_PIXEL>=ViewData.FRAMEH){
					if(map.getY()+ViewData.FRAMEH/ViewData.MAP_PIXEL<map.getHeight()){
						map.setY(map.getY()+1);						
					}
					y--;
				}
			}
		}
		
		if(state==CURSORMOVE&&e.getKeyCode()==KeyEvent.VK_X){
			cell=map.getCell(x, y);
			if(cell.isFigure()&&cell.getFigure().isMoveable()){				
				selectedfigure=cell.getFigure();
				
				
				

				figurereachable=map.getReachable(selectedfigure.getX(),selectedfigure.getY(),selectedfigure.getDistance());
				
				/*
				for(int i=0;i<map.getWidth();i++){
					for(int j=0;j<map.getHeight();j++){
						System.out.print(" "+figurereachable[i][j]);
					}
					System.out.print("\n");
				}
				*/
				state=FIGUREMOVE;
			}
		}
	}
}
