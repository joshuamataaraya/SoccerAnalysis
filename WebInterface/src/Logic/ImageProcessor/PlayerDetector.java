package logic.imageprocessor;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import logic.Constants;

class PlayerDetector extends OpencvDetector {

  public PlayerDetector(Mat image) {
     super(image);
  }

  @Override
  public Object detect() {
    //detects the players
    Mat imageMat = (Mat) image;
    Mat rgb = (Mat) imageMat.clone();
    Mat hsv = (Mat) processor.rgb2hsv(rgb);
    Imgcodecs.imwrite("testData/2-hsv.png", hsv);
    Mat variance = stdfilt(hsv);
    Imgcodecs.imwrite("testData/varianza.png", variance);
    variance = (Mat) processor.dilate(variance);
    Mat umbralizada = im2bw(variance);
    Mat filledImage = imfill(umbralizada, new Point(0, imageMat.height()  * 0.70));
    Imgcodecs.imwrite("testData/jugadores.png", filledImage);
    Imgcodecs.imwrite("testData/umbralizada.png", umbralizada);
    filledImage.convertTo(filledImage, 0);
    return filledImage;
  }
  
  private Mat normalizeImage(Mat image, int minValue, int maxValue) {
    Mat clone = image.clone();
    Core.normalize(clone, clone, minValue, maxValue, Core.NORM_MINMAX);
    return clone;
  }
  
  private Mat stdfilt(Mat image) {
    Mat hh = (Mat) processor.hh(image);
    hh = normalizeImage(hh, Constants.minValue , Constants.maxValue);
    hh.convertTo(hh, CvType.CV_32F);
    
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
    
    Mat std = sub.clone();
    Core.sqrt(sub, std);
    
    Mat variance = std.clone();
    Core.multiply(std, std, variance);
   
    /*// find out the mean image
    Imgproc.blur(h, out, new Size(5,5));
    // substract mean image
    Core.absdiff(out,h,out);
    // square to get std matrix
    Core.pow(out,2.0,out);
    // square to get mean matrix
    Core.pow(out,2.0,out);
    //Core.normalize(out, out, 0, 255, Core.NORM_MINMAX);
    Imgproc.blur(h, h, new Size(5,5));*/

    
    return variance;
  }
  
  private double graythresh(Mat image) {
    Mat clone = image.clone();
    clone.convertTo(clone, CvType.CV_8UC1);
    double umbral = Imgproc.threshold(clone, clone, 0, 255, 
        Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
    return umbral;
  }
  
  private Mat im2bw(Mat image) {
    double umbral = graythresh(image);
    Mat clone = image.clone();
    Imgproc.threshold(clone, clone, umbral, 255, Imgproc.THRESH_BINARY);
    return clone;
  }
}











