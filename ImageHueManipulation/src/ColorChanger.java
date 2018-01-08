// Name         : ColorChanger.java
// Author       : Fatma Serce
// Version      : 1.02
// Modified     : Kevin Tran
// Description  : ColorChanger class provided by professor. Color changer will
// change the hue of an image (default hardcoded for flower.png) at a certain
// hue value (default hardcoded as a float of 90/360.0f) and outputs the change
// as a new image. Most of the code has been completed already and just requires
// bitwise operations to get specific value of the red, green, and blue color
// values that are in an ARGB integer representation.

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.awt.image.*;
public class ColorChanger{
    public static void main(String args[])throws IOException{
        BufferedImage raw,processed;

        //Default picture will be a flower png file that is in the project's
        //current folder.
        raw = ImageIO.read(new File("flower.png"));
        int width = raw.getWidth();
        int height = raw.getHeight();
        processed = new BufferedImage(width,height,raw.getType());

        //Hard coded hue value.
        float hue = 90/360.0f;

        for(int y=0; y<height;y++){
            for(int x=0;x<width;x++){

                //This is how we grab the RGB value of a pixel at x,y
                //coordinates in the image.
                //Step 1 completed.
                int rgb = raw.getRGB(x,y);

                //Starts the new rgb int value and will change it to extract the
                //red, green, and blue value.
                //Step 2 completed.
                int red = (rgb >> 16) & 0xFF;
                int blue = rgb & 0xFF;
                int green = (rgb >> 8) & 0xFF;

                //Use  method to convert RGB values to HSB values.
                //Step 3 completed.
                float[] hsbArray = new float[3];
                Color.RGBtoHSB(red, green, blue, hsbArray);

                //Then use Color.HSBtoRGB() method to convert the HSB value to a
                //new RGB value. The new RGB value is calculated by adding the
                //original RGB value by the hue changing multiplier variable
                //named hue).
                //Step 4 completed.
                int newRGB = Color.HSBtoRGB(hsbArray[0]+hue, hsbArray[1],
                        hsbArray[2]);

                //Set the new RGB value to a pixel at x,y coordinates in the
                //image.
                //Step 5 completed.
                processed.setRGB(x,y,newRGB);
            }
        }
        //Hue-modified image will be written with the name "processed" in the
        //PNG extension
        ImageIO.write(processed,"PNG",new File("processed.png"));
    }
}