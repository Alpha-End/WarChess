package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import data.ViewData;
import data.XMLData;
import entity.Figure;
import entity.Map;
import util.R;
import view.Game;

public class CursorMove {
	int x,y;
	Map map;
	ArrayList<Figure> bluefigurelist,redfigurelist;
	ArrayList<BufferedImage> cursor_img;
	long lastpainttime;//上一次界面绘制时间
	
	int cursorindex=0;
	public CursorMove(Game g){
		map=g.map;
		bluefigurelist=g.bluefigurelist;
		redfigurelist=g.redfigurelist;
		
		cursor_img=new ArrayList<>();
		for(int i=0;i<XMLData.CURSOR_IMG_PATH.length;i++){
			cursor_img.add(R.load(XMLData.CURSOR_IMG_PATH[i]));
		}
		
		x=0;y=0;
		try{
			Figure firstfigure=bluefigurelist.get(0);
			x=firstfigure.getX();
			y=firstfigure.getY();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g){
		BufferedImage cur_img=cursor_img.get(cursorindex%cursor_img.size());
		int cur_x,cur_y;
		cur_x=(ViewData.MAP_PIXEL-cur_img.getWidth())/2+x*ViewData.MAP_PIXEL;
		cur_y=(ViewData.MAP_PIXEL-cur_img.getHeight())/2+y*ViewData.MAP_PIXEL;
		g.drawImage(cur_img,cur_x,cur_y,null);
		if(System.currentTimeMillis()-lastpainttime>ViewData.INTERVAL_TIME){
			lastpainttime=System.currentTimeMillis();
			cursorindex++;
		}
	}
	
	public void cursorMove(KeyEvent e){
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
	}
	
}
