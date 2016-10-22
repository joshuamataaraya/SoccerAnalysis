/*
 * @author Josué Arrieta Salas
 * @version v0.1.1-alpha
 */

package logic.imageprocessor;

import logic.Constants;
import org.opencv.core.Mat;
import org.opencv.core.Point;




// TODO: Auto-generated Javadoc
/**
 * The Class OpencvDetector. Detects objects especially for the OpenCV framework.
 * To represent images uses Mat. Every method uses it.
 */
abstract class OpencvDetector extends Detector {

  /**
   * Instantiates a new opencv detector.
   *
   * @param image the image, must be an opencv mat. In RGB format.
   */
  public OpencvDetector(Mat image) {
    super(image);
    processor = new OpencvImageProcessor();
  }
  
  /**
   * Imfill. Fill holes inside the image.
   *
   * @param image to be filled. Must be in binary format.
   * @param point to the background of the image.
   * @return the opencv mat, with the holes filled. In binary format.
   */
  protected Mat imfill(Mat image, Point point) {
    //Fill holes in an image
    //Point must be part of the background
    image = (Mat)processor.drawRectangle(image, new Point(0,0), new Point(50,50), Constants.BLACK);
    Mat clone = image.clone();
    clone = (Mat) processor.floodFill(clone, point, Constants.WHITE);
    //paint background of color white
    
    Mat invertedImage = (Mat) processor.complement(clone);
    //make ore between the inverted filled image and the original image
    return (Mat) processor.or(image, invertedImage);
  }
}
