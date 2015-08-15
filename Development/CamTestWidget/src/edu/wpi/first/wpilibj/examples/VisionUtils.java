/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.examples;

import edu.wpi.first.wpijavacv.WPIBinaryImage;
import edu.wpi.first.wpijavacv.WPIColor;
import edu.wpi.first.wpijavacv.WPIColorImage;
import edu.wpi.first.wpijavacv.WPIPoint;

/**
 *
 * @author gavin
 */
public final class VisionUtils {

    public static WPIColorImage applyBGFilter(WPIColorImage img, int sqrsInX, int sqrsInY, double devFactor) {
	int colorMagSum;
	int colorMagAvg;
	int devMagSum;
	int dev;
	int squareWidth = img.getWidth() / sqrsInX;
	int squareHeight = img.getHeight() / sqrsInY;
	WPIBinaryImage toReturn;
	int[][] pixelMag = new int[squareWidth][squareHeight];
	//int[][] colorSqrMagAvg = new int[sqrsInX][sqrsInY];
	//Iterate over X and Y squares
	//System.out.println("In method.");
	for (int iterX = 0; iterX < sqrsInX; iterX++) {
	    for (int iterY = 0; iterY < sqrsInY; iterY++) {
		colorMagSum = 0;
		//Find BK average
		for (int x = squareWidth * iterX;
			x < squareWidth * (iterX + 1); x++) {
		    for (int y = squareHeight * iterY;
			    y < squareHeight * (iterY + 1); y++) {
			pixelMag[x - squareWidth * iterX][y - squareHeight * iterY] = getMag(img, x, y);
			colorMagSum += pixelMag[x - squareWidth * iterX][y - squareHeight * iterY];
		    }
		}
		//System.out.println("Out first loop");
		colorMagAvg =
			colorMagSum / ((img.getWidth() / sqrsInX) * (img.getWidth() / sqrsInY));
		System.out.println("Avg = " + colorMagAvg + " at (" + iterX + ", " + iterY + ")");

		//Now find standard dev 
		devMagSum = 0;
		for (int x = squareWidth * iterX;
			x < squareWidth * (iterX + 1); x++) {
		    for (int y = squareHeight * iterY;
			    y < squareHeight * (iterY + 1); y++) {
			devMagSum += Math.abs(pixelMag[x - squareWidth * iterX][y - squareHeight * iterY] - colorMagAvg);
		    }
		}
		//System.out.println("Out second loop");
		dev = devMagSum/ (squareWidth * squareHeight);
		
		//Now remove background pixels as defined by devFactor
		for (int x = squareWidth * iterX;
			x < squareWidth * (iterX + 1); x++) {
		    for (int y = squareHeight * iterY;
			    y < squareHeight * (iterY + 1); y++) {
			if(pixelMag[x - squareWidth * iterX][y - squareHeight * iterY] > colorMagAvg) {
			    //Whiten/remove the point
			    img.drawPoint(new WPIPoint(x, y), WPIColor.WHITE, 0);
			}
		    }
		}
		//System.out.println("Out last loop");
	    }
	}
	return img;
	/*toReturn = img.getBlueChannel().getThresholdInverted(254);
	toReturn.and(img.getGreenChannel().getThresholdInverted(254));
	toReturn.and(img.getRedChannel().getThresholdInverted(254));
	return toReturn;*/
    }

    public static int getMag(WPIColorImage img, int x, int y) {
	//TODO: Non-fudge it?
	int rgb = img.getBufferedImage().getRGB(x, y);
	return -1 * (rgb >> 16);
	/*int RGB = img.getBufferedImage().getRGB(x, y);
	int R = RGB >> 16;
	int G = (RGB >> 8) % (256);
	int B = (RGB) % 256;
	return (int) (Math.sqrt((B * B + R * R + G * G) / 3));*/
    }
}
