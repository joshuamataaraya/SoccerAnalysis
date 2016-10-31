/*
 * @author Josué Arrieta Salas
 * @version v0.1.1-alpha
 */

package logic;

import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
/**
 * The Class Constants. Contains constants used in the program.
 */

public class Constants {
  /**used to set min and max for the green color.
  * If this value goes up, it will detect a larger range of colors.
  * If this value goes down, it will detect less greenish colors.
  **/
  public static int SENSITIVITY = 31;
  
  /**
   * value for green in hsv format
   * If this value goes up it will detect redish colors.
   * If this value goes down, it will detect yellowish colors.
   */
  public static int GREEN = 60;
  
  /**
   * min value of SV in an HSV format when detecting green pixels.
   * If this value goes up, it will not detect some "dark" green colors.
   */
  public static int SV = 0;
  
  /**
   * maximun size of a countour.
   * if this value goes up, eliminates bigger blobs when using bwareopen.
   * if this value goes down, eliminates smaller blobs when using bwareopen 
   */
  public static int MAXSIZE = 1135;
  
  /**
   * min value to normalize an image.
   * If this value goes up, it will set the min value of a image bigger. 
   */
  public static int minValue = 0;
 
  /**
   * max value to normalize an image.
   * If this value goes up, it will set the max values of the image bigger.
   * if this value goes down, it will set the max values of the image smaller. 
   */
  public static int maxValue = 255;

  /**
   * point to paint the scoreboard black. 
   * Must not change. 
   */
  public static Point SCOREPOINT1 = new Point(426,1);
  
  /**
   * point to paint the scoreboard black. 
   * Must not change. 
   */
  public static Point SCOREPOINT2 = new Point(637,80);
  
  /**
   * start point of an image. Must not change.
   */
  public static Point STARTPOINT = new Point(1,1);
    
  /**
   * black color in Scalar type. Must not change. 
   */
  public static Scalar BLACK = new Scalar(0);
  
  /**
   * white color in Scalar Type. Must not change. 
   */
  public static Scalar WHITE = new Scalar(255);
  
  /**
   * red color in scalar type. Must not change. 
   */
  public static Scalar RED = new Scalar(0,0,222);
  
  /**
   * size of window for std
   * If the window gets bigger, makes the players blobs bigger.
   * If the windows get smaller, makes the players blobs smaller. 
   */
  public static Size windowSize =  new Size(10, 10);
}
