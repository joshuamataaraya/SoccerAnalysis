package logic.imageprocessor;

import org.opencv.core.Mat;
import org.opencv.core.Point;

import logic.Constants;



abstract class OpencvDetector extends Detector {

  public OpencvDetector(Mat image) {
    super(image);
    processor = new OpencvImageProcessor();
  }
  
  protected Mat imfill(Mat image, Point point) {
    //Fill holes in an image
    //Point must be part of the background
    Mat clone = image.clone();
    clone = (Mat) processor.floodFill(clone, point, Constants.WHITE);
    
    Mat invertedImage = (Mat) processor.complement(clone);
    return (Mat) processor.or(image, invertedImage);
  }
}
