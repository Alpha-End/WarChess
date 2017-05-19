package data;

import java.awt.Font;

import view.Game;
import view.Load;
import view.MainPanel;
import view.Plot;
import view.Select;
import view.Welcome;

public class ViewData {
	public static final int FRAMEW=1024,FRAMEH=768;//界面宽高
	public static final int FRAMES=30;//帧数
	public static final int DIALOGBOX_ALPHA=1;//对话框透明度
	public static final int SELECT_BACKGROUND_ALPHA=1;//选择界面背景透明度
	public static final Font SPEAKERFONT=new Font("黑体",Font.BOLD,35);//发言者字体格式
	public static final Font DIALOGUEFONT=new Font("黑体",Font.LAYOUT_RIGHT_TO_LEFT,25);//发言者字体格式
	public static final Font OPTIONFONT=new Font("黑体",Font.BOLD,40);//选项字体格式
	
	public static final int WELCOME=0,LOAD=1,SAVE=2,NEWGAME=3,PLOT=4,SELECT=5,GAME=6;//游戏状态
	public static final int INTERVAL_TIME=100;//LOADING等界面绘制间隔时间
	
	public static int state=0;
	
	public static long lastpainttime=0;//上一次界面绘制时间
	
	public static String now_action_id=null;
	
	
	public static MainPanel mainpanel;
	public static Welcome welcome;
	public static Plot plot;
	public static Load load;
	public static Select select;
	public static Game game;
	
}
