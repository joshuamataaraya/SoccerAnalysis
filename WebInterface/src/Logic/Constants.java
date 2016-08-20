package Logic;
import org.opencv.core.Point;
import org.opencv.core.Scalar;

public class Constants {
	public static int SENSITIVITY = 23;
    public static int GREEN = 60;//in hsv format
    public static int MAXSIZE = 1135;
    
    public static Point SCOREPOINT1 = new Point(5,5);
    public static Point SCOREPOINT2 = new Point(250,75);
    
    public static Scalar BLACK = new Scalar(0);
    public static Scalar WHITE = new Scalar(255);
}
