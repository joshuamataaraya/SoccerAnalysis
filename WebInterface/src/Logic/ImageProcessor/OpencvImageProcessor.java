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


class OpencvImageProcessor extends ImageProcessor {

  public OpencvImageProcessor() {  }

  @Override
  public Mat mask(Object image, Object alphaMin, Object alphaMax) {
    Mat binaryImage = new Mat();
    Core.inRange((Mat )image, (Scalar) alphaMin, (Scalar) alphaMax, binaryImage);
    return binaryImage;
  }

  @Override
  public Mat rgb2hsv(Object image) {
    //receives an image in rgb format, and transforms it into hsv
    Mat hsv = new Mat();
    Imgproc.cvtColor((Mat)image, hsv, Imgproc.COLOR_RGB2HSV);
    return hsv;
  }

  @Override
  public Mat dilate(Object image) {
    Mat dilatedMat = new Mat(); 
    Imgproc.dilate((Mat) image, dilatedMat, new Mat()); 
    return dilatedMat;
  }

  @Override
  public Mat complement(Object image) {
    Mat invertedImage = new Mat();
    Core.bitwise_not((Mat) image, invertedImage);//image complement
    return invertedImage;
  }

  @Override
  public Mat or(Object image1, Object image2) {
    Mat orImage = new Mat();
    Core.bitwise_or((Mat) image1, (Mat) image2, orImage);//or between images
    return orImage;
  }

  @Override
  public Mat floodFill(Object image, Object point, Object color) {
    Mat matImage = (Mat) image;
    Mat matImageClone = matImage.clone();
    Mat mask = new Mat(matImageClone.rows() + 2, matImageClone.cols() + 2, CvType.CV_8UC1);
    Imgproc.floodFill(matImageClone, mask, (Point) point, (Scalar) color);
    //starts to fill in point
    return matImageClone;
  }

  @Override
  public Mat drawRectangle(Object image, Object point1, Object point2, Object color) {
    Mat matImage = (Mat) image;
    Mat matImageClone = matImage.clone();
    Imgproc.rectangle(matImageClone, (Point) point1, (Point) point2, (Scalar) color, -1);
    return matImageClone;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Mat fillContours(Object image, Object contours, Object color) {
    Mat imageMat = (Mat) image;
    Mat clonedImage = imageMat.clone();
    Imgproc.drawContours(clonedImage, (List<MatOfPoint>) contours, -1, Constants.BLACK,-1);
    return clonedImage;
  }

  @Override
  public List<MatOfPoint> findContours(Object image) {
    List<MatOfPoint> contours = new ArrayList<>();//all contorus are saved here
    Imgproc.findContours((Mat) image, contours, new Mat(), 
        Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0,0));
    return contours;
  }

  @Override
  public Boolean compareImage(Object image1, Object image2) {
    //both image 1 and image 2 must be converted to 1 channel before
    Mat matImage1 = (Mat) image1;
    Mat matImage2 = (Mat) image2;
    if (matImage1.cols() != matImage2.cols()
        || matImage1.rows() != matImage2.rows() || matImage1.dims() != matImage2.dims()) {
      return false;//image must have the same dimensions
    }
    Mat result = new Mat();
    Core.compare(matImage1, matImage2, result, 1);       
    return Core.countNonZero(result) == 0;
  }

  @Override
  public Object h(Object image) {
    //gets H of an HSV image
    List<Mat> channel = new ArrayList<>();
    Core.split((Mat) image, channel);
    return channel.get(0);
  }
}
