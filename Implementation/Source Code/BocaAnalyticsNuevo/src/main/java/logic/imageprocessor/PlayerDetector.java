/*
 * @author Josué Arrieta Salas
 * @version v0.1.1-alpha
 */

package logic.imageprocessor;

import logic.Constants;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;


// TODO: Auto-generated Javadoc
/**
 * The Class PlayerDetector.
 * Its purpose is to detect the players of the soccer field.
 * The whole algorithm was taken from (pages 3-5):
 * https://github.com/joshuamataaraya/SoccerAnalysis/blob/master/Requirements/User%20Needs.pdf
 * Players must have their uniform different to green.
 * Referee would be detected.
 * The soccer field must be green.
 */
public class PlayerDetector extends OpencvDetector {

  /**
   * Instantiates a new player detector.
   *
   * @param image of the soccer match. Must be in RGB format Must be opencv Mat type.
   */
  public PlayerDetector(Mat image) {
     super(image);
  }
  
  /* (non-Javadoc)
   * @see logic.imageprocessor.Detector#detect()
   *  Uses opencv function ImgCodecs.imwrite():
   *  http://docs.opencv.org/java/3.0.0/org/opencv/imgcodecs/Imgcodecs.html
   */
  @Override
  public Object detect() {
    //detects the players
    Mat imageMat = (Mat) image;
    Mat rgb = (Mat) imageMat.clone();
    Mat hsv = (Mat) processor.rgb2hsv(rgb);
    //Imgcodecs.imwrite("testData/2-hsv.png", hsv);
    Mat variance = stdfilt(hsv);
    //Imgcodecs.imwrite("testData/varianza.png", variance);
    variance = (Mat) processor.dilate(variance);
    Mat umbralizada = im2bw(variance);
    //set background to point (0, 70% of height):
    Mat filledImage = imfill(umbralizada, new Point(0, imageMat.height()  * 0.70));
    //Imgcodecs.imwrite("testData/jugadores.png", filledImage);
    //Imgcodecs.imwrite("testData/umbralizada.png", umbralizada);
    filledImage.convertTo(filledImage, 0);
    return filledImage;
  }
  
  /**
   * Normalize image. Normalize an image between 2 values
   * Uses OpenCV function:
   * http://docs.opencv.org/java/2.4.2/org/opencv/core/Core.html
   * @param image the opencv Mat image to be normalized, must be one channeled.
   * @param minValue the min value that the image can take.
   * @param maxValue the max value that the image can take.
   * @return the normalized opencv Mat image. Maintains its format.
   */
  private Mat normalizeImage(Mat image, int minValue, int maxValue) {
    Mat clone = image.clone();
    Core.normalize(clone, clone, minValue, maxValue, Core.NORM_MINMAX);
    return clone;
  }
  
  /**
   * Stdfilt.
   * Calculates local variance for an image.
   *
   * @param image the opencv Mat image to be calculated the variance. Expected to be H of HSV.
   * @return the opencv mat gray image corresponding to the local variance.
   */
  private Mat stdfilt(Mat image) {
    Mat hh = (Mat) processor.hh(image);
    hh = normalizeImage(hh, Constants.minValue , Constants.maxValue);
    hh.convertTo(hh, CvType.CV_32F);
    
    //uses classical formula of standar deviation = sqrt(E(x-u)^2)
    Mat mu = hh.clone();
    Imgproc.blur(hh, mu, Constants.windowSize);
    
    Mat h2 = hh.clone();
    Core.multiply(hh, hh, h2);
    
    Mat mu2 = h2.clone();
    Imgproc.blur(h2, mu2, Constants.windowSize);
    
    Mat mu22 = mu.clone();
    Core.multiply(mu, mu, mu22);
    
    Mat sub = mu2.clone();
    Core.subtract(mu2, mu22, sub);
    
    //get local standard deviation
    Mat std = sub.clone();
    Core.sqrt(sub, std);
    
    //get local variance
    Mat variance = std.clone();
    Core.multiply(std, std, variance);

    return variance;
  }
  
  /**
   * Graythresh. Gets optimum threshold of a binary image.
   * Uses opencv functions Imgprocs.threshold():
   * http://docs.opencv.org/java/2.4.9/org/opencv/imgproc/Imgproc.html
   * @param image the opencv Mat image to get its optimum threshold. In binary format.
   * @return a double with the optimum threshold.
   */
  private double graythresh(Mat image) {
    Mat clone = image.clone();
    clone.convertTo(clone, CvType.CV_8UC1);
    double umbral = Imgproc.threshold(clone, clone, 0, 255, 
        Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
    return umbral;
  }
  
  /**
   * Im 2 bw. Given an optimum threshold, deletes all those values below that value.
   * Values above given threshold remains.
   * Uses opencv functions Imgprocs.threshold():
   * http://docs.opencv.org/java/2.4.9/org/opencv/imgproc/Imgproc.html
   * @param image the opencv Mat image to be optimized. In Binary format.
   * @return the opencv mat image optimized. In binary format.
   */
  private Mat im2bw(Mat image) {
    double umbral = graythresh(image);// get optimum threshold.
    Mat clone = image.clone();
    Imgproc.threshold(clone, clone, umbral, 255, Imgproc.THRESH_BINARY);
    return clone;
  }
}











