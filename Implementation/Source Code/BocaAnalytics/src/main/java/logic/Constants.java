/*
 * @author Josué Arrieta Salas
 * @version v0.1.1-alpha
 */

package logic;

import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;

public class Constants {
  public static int SENSITIVITY = 31;//used to set min and max for the green color
  public static int GREEN = 60;//value for green in hsv format
  public static int SV = 0;//min value of SV in an HSV format when detecting green pixels
  
  public static int MAXSIZE = 1135;//maximun size of a countour
  public static int minValue = 0;//min value to normalize an image
  public static int maxValue = 255;//max value to normalize an image

  public static Point SCOREPOINT1 = new Point(426,1);//point to paint the scoreboard black
  public static Point SCOREPOINT2 = new Point(637,80);//point to paint the scoreboard black
  public static Point STARTPOINT = new Point(0,0);//start point of an image
    
  public static Scalar BLACK = new Scalar(0);//black color in Scalar type
  public static Scalar WHITE = new Scalar(255);// white color in Scalar Type
  public static Scalar RED = new Scalar(0,0,222);//red color in scalar type
  
  public static Size windowSize =  new Size(10, 10);//size of window for std
}
