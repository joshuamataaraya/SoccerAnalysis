/*
 * @author Josué Arrieta Salas
 * @version v0.1.1-alpha
 */

package logic.imageprocessor;

import logic.Constants;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Class OpencvImageProcessor.
 * Contains basic functions for image processing especially in the opencv framework.
 * All images in this class are of type opencv Mat.
 * All colors or values, are in opencv Scalar type.
 * Points are of type opencv Point.
 */
public class OpencvImageProcessor extends ImageProcessor {

  /**
   * Instantiates a new opencv image processor.
   */
  public OpencvImageProcessor() {  }

  /**
   *  Uses opencv core.inRange().
   *  http://docs.opencv.org/java/2.4.2/org/opencv/core/Core.html
   * @see logic.imageprocessor.ImageProcessor#mask(java.lang.Object,java.lang.Object, java.lang.Object)
   */
  @Override
  public Mat mask(Object image, Object alphaMin, Object alphaMax) {
    Mat binaryImage = new Mat();
    //search for pixels between min and max, and creates a binary image.
    Core.inRange((Mat )image, (Scalar) alphaMin, (Scalar) alphaMax, binaryImage);
    return binaryImage;
  }

  /**
   * Uses OpenCV function Imgproc.cvtColor().
   * http://docs.opencv.org/java/2.4.9/org/opencv/imgproc/Imgproc.html
   * @see logic.imageprocessor.ImageProcessor#rgb2hsv(java.lang.Object)
   */
  @Override
  public Mat rgb2hsv(Object image) {
    //receives an image in rgb format, and transforms it into hsv
    Mat hsv = new Mat();
    //make conversion
    Imgproc.cvtColor((Mat)image, hsv, Imgproc.COLOR_RGB2HSV);
    return hsv;
  }

  /**
   * uses Opencv function Imagproc.dilate().
   * http://docs.opencv.org/java/2.4.9/org/opencv/imgproc/Imgproc.html
   * @see logic.imageprocessor.ImageProcessor#dilate(java.lang.Object)
   */
  @Override
  public Mat dilate(Object image) {
    Mat dilatedMat = new Mat();
    Mat imageMat = (Mat) image;
    dilatedMat = imageMat.clone();
    //opencv dilate
    Imgproc.dilate((Mat) image, dilatedMat, new Mat()); 
    return dilatedMat;
  }

  /**
   * Uses Opencv function Core.bitwise_not().
   * http://docs.opencv.org/java/2.4.2/org/opencv/core/Core.html
   * @see logic.imageprocessor.ImageProcessor#complement(java.lang.Object)
   */
  @Override
  public Mat complement(Object image) {
    Mat invertedImage = new Mat();
    Core.bitwise_not((Mat) image, invertedImage);//image complement
    return invertedImage;
  }

  /**
   * Uses Opencv function Core.bitwise_or().
   * http://docs.opencv.org/java/2.4.2/org/opencv/core/Core.html
   * @see logic.imageprocessor.ImageProcessor#or(java.lang.Object, java.lang.Object)
   */
  @Override
  public Mat or(Object image1, Object image2) {
    Mat orImage = new Mat();
    //same size and dimensions
    Core.bitwise_or((Mat) image1, (Mat) image2, orImage);//or between images
    return orImage;
  }

  /**
   * Uses opencv function Imgproc.floodFill().
   * http://docs.opencv.org/java/2.4.9/org/opencv/imgproc/Imgproc.html
   * @see logic.imageprocessor.ImageProcessor#floodFill(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  @Override
  public Mat floodFill(Object image, Object point, Object color) {
    Mat imageMat = (Mat) image;
    Mat matImageClone = imageMat.clone();
    //create a mask. In this case it's basically empty.
    Mat mask = Mat.zeros(matImageClone.rows() + 2, matImageClone.cols() + 2, CvType.CV_8U);
    //fill it with a given color, in a given point.
    Imgproc.floodFill(matImageClone, mask, (Point) point, (Scalar) color);
    //starts to fill in point
    return matImageClone;
  }

  /**
   * Uses opencv function Imgproc.rectangle().
   * http://docs.opencv.org/java/2.4.9/org/opencv/imgproc/Imgproc.html
   * @see logic.imageprocessor.ImageProcessor#drawRectangle(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object)
   */
  @Override
  public Mat drawRectangle(Object image, Object point1, Object point2, Object color) {
    Mat matImage = (Mat) image;
    Mat matImageClone = matImage.clone();
    //draw a rectangle according to two points and a color.
    Imgproc.rectangle(matImageClone, (Point) point1, (Point) point2, (Scalar) color, -1);
    return matImageClone;
  }

  /**
   * Uses opencv function Imgproc.drawContours().
   * http://docs.opencv.org/java/2.4.9/org/opencv/imgproc/Imgproc.html
   * @see logic.imageprocessor.ImageProcessor#fillContours(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  @Override
  public Mat fillContours(Object image, Object contours, Object color) {
    Mat imageMat = (Mat) image;
    Mat clonedImage = imageMat.clone();
    //fill a image, with a list of countours; with a given color.
    Imgproc.drawContours(clonedImage, (List<MatOfPoint>) contours, -1, (Scalar)color,-1);
    return clonedImage;
  }

  /**
   * Uses opencv function Imgproc.findContours().
   * http://docs.opencv.org/java/2.4.9/org/opencv/imgproc/Imgproc.html
   * @see logic.imageprocessor.ImageProcessor#findContours(java.lang.Object)
   */
  @Override
  public List<MatOfPoint> findContours(Object image) {
    List<MatOfPoint> contours = new ArrayList<>();//all contorus are saved here
    Mat imageMat = (Mat) image;
    Mat clone = imageMat.clone();
    //find all countours of the image.
    Imgproc.findContours(clone, contours, new Mat(), 
        Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0,0));
    return contours;
  }

  /**
   * Uses opencv functions. Core.countNonZero() and Core.Compare.
   * http://docs.opencv.org/java/2.4.2/org/opencv/core/Core.html
   * @see logic.imageprocessor.ImageProcessor#compareImage(java.lang.Object, java.lang.Object)
   */
  @Override
  public Boolean compareImage(Object image1, Object image2) {
    //both image 1 and image 2 must be converted to 1 channel before
    Mat matImage1 = (Mat) image1;
    Mat matImage2 = (Mat) image2;
    if (matImage1.cols() != matImage2.cols()
        || matImage1.rows() != matImage2.rows() || matImage1.dims() != matImage2.dims()) {
      return false;//image must have the same dimensions; and same size.
    }
    Mat result = new Mat();
    //if substracting both images
    Core.compare(matImage1, matImage2, result, 1);
    //and have the same quantity of cero pixels, then must be the same image.
    return Core.countNonZero(result) == 0;
  }

  /**
   * Uses opencv function Core.split().
   * http://docs.opencv.org/java/2.4.2/org/opencv/core/Core.html
   * @see logic.imageprocessor.ImageProcessor#hh(java.lang.Object)
   */
  @Override
  public Object hh(Object image) {
    //gets H of an HSV image
    List<Mat> channel = new ArrayList<>();
    //splits channels to get H channel
    Core.split((Mat) image, channel);
    return channel.get(0);
  }

  /**
   *  Uses opencv function ImgCodecs.imwrite().
   *  http://docs.opencv.org/java/3.0.0/org/opencv/imgcodecs/Imgcodecs.html
   * @see logic.imageprocessor.ImageProcessor#paintPlayers(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  @Override
  public Object paintPlayers(Object originalImage, Object field, Object players) {
    //paints the players in the final image
    Mat originalImageMat = (Mat)originalImage;
    Mat finalImage = originalImageMat.clone();
    Mat playersMat = (Mat) getPlayers(field, players);//gets all players
    List<MatOfPoint> contours = new ArrayList<>();//all contours of players are saved here
    contours = findContours(playersMat);
    if (!contours.isEmpty()) {
      finalImage = fillContours(finalImage, contours, Constants.RED);//fill all players
    }
    //Imgcodecs.imwrite("testData/blobsPintados.png", (Mat) finalImage);
    return finalImage;
  }


  /**
   *  Uses opencv function ImgCodecs.imwrite().
   *  http://docs.opencv.org/java/3.0.0/org/opencv/imgcodecs/Imgcodecs.html
   * @see logic.imageprocessor.ImageProcessor#getPlayers(java.lang.Object, java.lang.Object)
   */
  @Override
  public Object getPlayers(Object field, Object players) {
    Mat invertedField = complement(field);
    Mat playersMat = or(invertedField, (Mat)players);//invert and or to get the blobs in the field
    //fill the background of the field, so we do not paint it :)
    playersMat = floodFill(playersMat, Constants.STARTPOINT, Constants.BLACK);
    //Imgcodecs.imwrite("testData/blobsBinarios.png", (Mat) playersMat);
    /*List<MatOfPoint> contours = new ArrayList<>();//all contours of players are saved here
    contours = findContours(playersMat);
    if (!contours.isEmpty()) {
      Imgproc.drawContours(playersMat , contours, -1, Constants.RED,0);
    }*/
    return playersMat;
  }

  /**
   * Uses opencv function: Core.countNonZero(), Core.bitwise_and().
   * http://docs.opencv.org/java/2.4.2/org/opencv/core/Core.html
   * For more information about the dice metric.
   * https://en.wikipedia.org/wiki/S%C3%B8rensen%E2%80%93Dice_coefficient
   * @see logic.imageprocessor.ImageProcessor#dice(java.lang.Object, java.lang.Object, java.lang.Object)
   */
  @Override
  public double dice(Object groundTruth, Object field, Object players) {
    //dice index between groundTruth and image
    Mat playersMat = (Mat) getPlayers(field, players);
    //get how many player pixels they really are
    double playerPixels = Core.countNonZero(playersMat);
    //get how many players pixels they must be
    double groundTruthPixels = Core.countNonZero((Mat) groundTruth);
    Mat andMat = new Mat();
    //get how many players they are that are in both images
    Core.bitwise_and(playersMat, (Mat) groundTruth, andMat);
    double andMatPixels =  Core.countNonZero(andMat);
    //formula of dice metric
    return (2 * andMatPixels) / (playerPixels + groundTruthPixels);
  }
}
