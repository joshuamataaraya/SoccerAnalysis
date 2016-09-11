package logic.imageprocessor;

import static org.junit.Assert.*;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class PlayerDetectorTest {

  public PlayerDetectorTest() {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }
  
  @Test
  public void testDetect() {
    ImageProcessor processor = new OpencvImageProcessor();
    Detector detector = new PlayerDetector(Imgcodecs.imread("testData/1-rgb.png"));
    detector.detect();
  }

}
