package Logic.ImageProcessor;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

import Logic.Constants;

public abstract class DetectorOpenCV extends Detector {

	public DetectorOpenCV(Mat image) {
		super(image);
	}
	
	protected Mat imfill(Point point) {
		//Fill holes in an image
		//Point must be part of the background
    Mat imageMat = (Mat) image;
		Mat clone = imageMat.clone();
    Mat mask = new Mat(clone.rows() + 2, clone.cols() + 2, CvType.CV_8UC1);
    Imgproc.floodFill(clone, mask, point, Constants.WHITE);
    
    Mat invertedImage = new Mat();
    Core.bitwise_not(clone, invertedImage);//image complement
    
    Mat filledImage = new Mat();
    Core.bitwise_or(imageMat, invertedImage, filledImage);//or between images
    //Imgcodecs.imwrite("5-rellenada.jpg", filledImage);
    
    return filledImage;
	}
	
	public void setImage(Mat image) {
		this.image = image;
	}
	

}
