package util;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;


public class R {
	

	static public BufferedImage load(String s) {
		try{
			return ImageIO.read(R.class.getResourceAsStream("/"+s));
		}catch(Exception e){
			throw new RuntimeException();
		}
		
	}
	static public int random(int a,int b){
		return ((int)(Math.random()*Math.abs(a-b)))+Math.min(a, b);
	}
	
	
	static public BufferedImage hyalineProcess(BufferedImage sourceImage,int W,int H,int X,int Y,int alpha){
	    int width = sourceImage.getWidth();
	    int height = sourceImage.getHeight();
	    BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
	    for(int i= 0 ; i < width ; i++){  
	            for(int j = 0 ; j < height; j++){  
	                int rgb = sourceImage.getRGB(i, j); 
	                if((i>X&&i<X+W)&&(j>Y&&j<Y+H)){//如果i,j在范围内则透明化
	                	rgb=((alpha * 255 / 10) << 24) | (rgb & 0x00ffffff);
	                }
	                grayImage.setRGB(i, j, rgb);  
	        }  
	    } 
	    return grayImage;
	}
	
	
}
