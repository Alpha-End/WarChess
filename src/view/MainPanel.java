package view;

import java.awt.Graphics;

import javax.swing.JPanel;

import data.ViewData;
import interFace.PaintInterface;

public class MainPanel extends JPanel implements Runnable{
	PaintInterface paintI;
	
	public MainPanel() {
		// TODO Auto-generated constructor stub
		ViewData.welcome=new Welcome();
		ViewData.plot=new Plot();
		ViewData.load=new Load();
		ViewData.select=new Select();
		ViewData.game=new Game();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long nextpainttime;
		while(true){
			switch(ViewData.state){//根据不同的状态绘制不同的界面
			case ViewData.WELCOME:{paintI=ViewData.welcome;break;}
			case ViewData.PLOT:{paintI=ViewData.plot;break;}
			case ViewData.LOAD:{paintI=ViewData.load;break;}
			case ViewData.SELECT:{paintI=ViewData.select;break;}
			case ViewData.GAME:{paintI=ViewData.game;break;}
			}
			
			try{
				nextpainttime=ViewData.lastpainttime+1000/ViewData.FRAMES-System.currentTimeMillis();//下一帧绘制时间
				if(nextpainttime>0){
					Thread.sleep(nextpainttime);//下一帧重绘时间			
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			this.repaint();//界面重绘
			ViewData.lastpainttime=System.currentTimeMillis();
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		paintI.paint(g);
	}
}
