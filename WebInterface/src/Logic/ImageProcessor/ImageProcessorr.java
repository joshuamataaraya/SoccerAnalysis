package logic.imageprocessor; 

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import logic.Constants;

public class ImageProcessorr {
	
	
	public ImageProcessorr(){ }
	
	public static Mat detectField(Mat pImage){
		//detects the soccer field
	  Mat rgb = pImage.clone();
      Mat hsv = rgb2hsv(rgb);
      Imgcodecs.imwrite("testData/2-hsv.png", hsv);
      Mat greenMask = greenMask(hsv);
      Imgcodecs.imwrite("testData/3-binaria.png", greenMask);
      Mat dilatedImage = dilate(greenMask);
      Imgcodecs.imwrite("testData/4-dilatada.png", dilatedImage);
      Mat filledImage = imfill(dilatedImage);
      Imgcodecs.imwrite("testData/5-rellenada.png", filledImage);
      Mat polishedImage = bwareaopen(filledImage);
      Imgcodecs.imwrite("testData/6-pulida.png", polishedImage);
      Mat finalImage = finalTouch(polishedImage);    
      Imgcodecs.imwrite("testData/7-finalTouch.png", finalImage);
      Mat imageWithoutScore = removeScore(finalImage);
      Imgcodecs.imwrite("testData/8-noScore.png", imageWithoutScore);
      return imageWithoutScore;
	}
	
  public static Boolean compareImage(Mat image1, Mat image2) {
    //both image 1 and image 2 must be converted to 1 channel before
    if (image1.cols() != image2.cols() || image1.rows() != image2.rows() || image1.dims() != image2.dims()) {
        return false;//image must have the same dimensions
    }
     Mat result = new Mat();
     Core.compare(image1, image2, result, 1);	    
     return Core.countNonZero(result) == 0;
    }
  
  
  
  
  
  
	public static Mat rgb2hsv(Mat pImage){
		//receives an image in rgb format, and transforms it into hsv
		Mat hsv = new Mat();
		Imgproc.cvtColor(pImage, hsv, Imgproc.COLOR_RGB2HSV);
	    //Imgcodecs.imwrite("2-hsv.png", hsv);
	    return hsv;
	}
	
	public static Mat greenMask(Mat pImage){
		//crates a binary mask of green pixeles of an image
		//pImage must be in hsv format
	    Mat binaryImage = new Mat(); 
        Scalar alfaMin = new Scalar(Constants.GREEN - Constants.SENSITIVITY, 100, 100); 
        Scalar alfaMax = new Scalar(Constants.GREEN + Constants.SENSITIVITY, 255, 255); 
        Core.inRange(pImage, alfaMin, alfaMax, binaryImage);
        //Imgcodecs.imwrite("3-binaria.png", binaryImage);
        return binaryImage;
	}
	public static Mat dilate(Mat pImage){
		//dilates an image
		Mat dilatedMat= new Mat(); 
        Imgproc.dilate(pImage, dilatedMat, new Mat()); 
        //Imgcodecs.imwrite("4-dilatada.png", dilatedMat);
        return dilatedMat;
	}
	public static Mat imfill(Mat pImage){
		//https://www.learnopencv.com/filling-holes-in-an-image-using-opencv-python-c/
		//Fill holes in an image
        Mat clone = pImage.clone();
        Mat mask = new Mat(clone.rows() + 2, clone.cols() + 2, CvType.CV_8UC1);
        Imgproc.floodFill(clone, mask, new Point(0,0), Constants.WHITE);
        /*starts to fill in point (0,0)*/
        
        Mat invertedImage = new Mat();
        Core.bitwise_not(clone, invertedImage);//image complement
        
        Mat filledImage = new Mat();
        Core.bitwise_or(pImage, invertedImage, filledImage);//or between images
        //Imgcodecs.imwrite("5-rellenada.jpg", filledImage);
        
        return filledImage;
	}
	public static Mat bwareaopen(Mat pImage){
        /*Eliminates spurious regions*/
        List<MatOfPoint> contours = new ArrayList<>();//all contorus are saved here
        List<MatOfPoint> littleContours = new ArrayList<>();//little contours are saved here to be filled later
        Mat hierarchy = new Mat();
        Mat polishedImage = pImage.clone();
        Mat clonedImage = pImage.clone();
        
        Imgproc.findContours(clonedImage, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE, new Point(0,0));
        
        if (!contours.isEmpty()){
            for (int i=0; i<contours.size(); ++i) {
                if (Imgproc.contourArea((contours.get(i))) < Constants.MAXSIZE){
                	littleContours.add(contours.get(i));//saved temporarily
                }
            }
            Imgproc.drawContours(polishedImage, littleContours, -1, Constants.BLACK,-1);
            //Imgcodecs.imwrite("6-pulida.png", polishedImage);
        }
        return polishedImage;
	}
	public static Mat finalTouch(Mat pImage){
		//fills possible holes that were not filled before
		//generally are regions close to the borders
		Mat invImage = new Mat();
        Core.bitwise_not(pImage.clone(), invImage);//image complement
        Mat invPoolished = bwareaopen(invImage);
        Mat finalImage = new Mat();
        Core.bitwise_not(invPoolished, finalImage);//image complement
        return finalImage;
		
	}
	public static Mat removeScore(Mat pImage){
		Mat removedScoreImage = pImage.clone();
		Imgproc.rectangle(removedScoreImage, Constants.SCOREPOINT1, Constants.SCOREPOINT2, Constants.BLACK, -1);
		return removedScoreImage;
	}
	
	
}
