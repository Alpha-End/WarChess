package game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import data.ViewData;
import entity.Figure;
import entity.Map;
import view.Game;

public class RedFigureAction{
	ArrayList<Figure> redfigurelist;
	ArrayList<Figure> bluefigurelist;
	Map map;
	Game g;
	
	int screen_w,screen_h;
	
	public RedFigureAction(Game g) {
		// TODO Auto-generated constructor stub
		this.g=g;
		redfigurelist=g.redfigurelist;
		bluefigurelist=g.bluefigurelist;
		map=g.map;
		screen_h=ViewData.FRAMEH/ViewData.MAP_PIXEL;
		screen_w=ViewData.FRAMEW/ViewData.MAP_PIXEL;
	}
	public void redFigureAction() {
		// TODO Auto-generated constructor stub
		map.setX(0);
		map.setY(0);
		for(int i=0;i<redfigurelist.size();i++){
			Figure figure=redfigurelist.get(i);
			map.roundstate=map.RED;
			int[][] figurereachable=map.getReachable(figure.getX(), figure.getY(), figure.getDistance());
			
			
			
			//System.out.println(figure.getX()+"   "+figure.getY()+"   "+figure.getDistance());
			for(int j=0;j<map.getWidth();j++){
				for(int k=0;k<map.getHeight();k++){
					//System.out.print(figurereachable[j][k]+" ");
					if(figurereachable[j][k]>=0&&map.figureHaveEnermy(figure, j, k)&&!map.haveAnotherFigure(j, k,figure)){
						Figure bluefigure=map.getEnermy(figure, j, k).get(0);
						map.figureRemove(figure.getX(), figure.getY(), j, k, figure);
						figure.moveTo(j, k);
						figure.attackAnotherFigure(bluefigure);

					}
				}
				//System.out.println("");
			}
			figure.setMoveable(true);
		}
		map.roundstate=map.BLUE;
	}
}
