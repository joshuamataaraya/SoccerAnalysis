package logic.imageprocessor;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import logic.Constants;

import java.util.ArrayList;
import java.util.List;


public class FieldDetector extends OpencvDetector {

  public FieldDetector(Mat image) {
    super(image);
  }

  @Override
  public Object detect() {
    //detects the soccer field
    Mat imageMat = (Mat) image;
    Mat rgb = (Mat) imageMat.clone();
    Mat hsv = (Mat) processor.rgb2hsv(rgb);
    Imgcodecs.imwrite("testData/2-hsv.png", hsv);
    Mat greenMask = greenMask(hsv);
    Imgcodecs.imwrite("testData/3-binaria.png", greenMask);
    Mat dilatedImage = (Mat) processor.dilate(greenMask);
    Imgcodecs.imwrite("testData/4-dilatada.png", dilatedImage);
    Mat filledImage = imfill(dilatedImage, new Point(0,0));
    Imgcodecs.imwrite("testData/5-rellenada.png", filledImage);
    Mat polishedImage = bwareopen(filledImage);
    Imgcodecs.imwrite("testData/6-pulida.png", polishedImage);
    Mat finalImage = finalTouch(polishedImage);    
    Imgcodecs.imwrite("testData/7-finalTouch.png", finalImage);
    Mat imageWithoutScore = removeScore(finalImage);
    Imgcodecs.imwrite("testData/8-noScore.png", imageWithoutScore);
    return imageWithoutScore;
  }
  
  private Mat greenMask(Mat image) {
    //crates a binary mask of green pixeles of an image
    //image must in hsv format
    Scalar alfaMin = new Scalar(Constants.GREEN - Constants.SENSITIVITY, 100, 100); 
    Scalar alfaMax = new Scalar(Constants.GREEN + Constants.SENSITIVITY, 255, 255); 
    Mat binaryImage = (Mat) processor.mask((Mat) image, alfaMin, alfaMax);
    return binaryImage;
  }
  
  @SuppressWarnings("unchecked")
  private Mat bwareopen(Mat image) {
    /*Eliminates spurious regions*/
    List<MatOfPoint> contours = new ArrayList<>();//all contorus are saved here
    List<MatOfPoint> littleContours = new ArrayList<>();//little contours are saved here 
    Mat polishedImage = image.clone();
    Mat clonedImage = image.clone();
    contours = (List<MatOfPoint>) processor.findContours(clonedImage);
    if (!contours.isEmpty()) {
      for (int i = 0; i < contours.size(); i++) {
        if (Imgproc.contourArea((contours.get(i))) < Constants.MAXSIZE) {
          littleContours.add(contours.get(i));//saved temporarily
        }
      }
      return (Mat) processor.fillContours(polishedImage, littleContours, Constants.BLACK);
    }
    return null;
  }
  
  private Mat finalTouch(Mat image) {
    //fills possible holes that were not filled before
    //generally are regions close to the borders
    Mat invImage = (Mat) processor.complement(image);
    Mat invPoolished = bwareopen(invImage);
    return (Mat) processor.complement(invPoolished);
  }
  
  private Mat removeScore(Mat image) {
    return (Mat) processor.drawRectangle(image, 
        Constants.SCOREPOINT1, Constants.SCOREPOINT2, Constants.BLACK);
  }
}
