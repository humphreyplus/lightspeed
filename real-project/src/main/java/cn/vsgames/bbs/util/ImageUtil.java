package cn.vsgames.bbs.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtil {
	
	public static void avatarCrop(String srcImageFile, String destImageFile, 
			int x, int y, int length) throws IOException {
		
		BufferedImage image = ImageIO.read(new File(srcImageFile));
		BufferedImage newImage = image.getSubimage(x, y, length, length);
		if(!ImageIO.write(newImage, "PNG", new File(destImageFile))) {
			throw new IOException("No appropriate writer is found.");
		}
	}
	
}
