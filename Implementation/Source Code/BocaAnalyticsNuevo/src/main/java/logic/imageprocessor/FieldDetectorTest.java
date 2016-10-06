package logic.imageprocessor;

import static org.junit.Assert.*;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class FieldDetectorTest {

  public FieldDetectorTest() {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }
 
  @Test
  public void testDetect() {
    ImageProcessor processor = new OpencvImageProcessor();
    Detector detector = new FieldDetector(Imgcodecs.imread("testData/1-rgb.png"));
    Mat correctImage = Imgcodecs.imread("testData/8-noScore.png",
        Imgcodecs.IMREAD_GRAYSCALE);
    assertTrue(processor.compareImage(correctImage, detector.detect()));
  }
}
