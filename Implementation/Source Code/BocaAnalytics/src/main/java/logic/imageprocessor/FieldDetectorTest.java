/*
 * @author Josué Arrieta Salas
 * @version v0.1.1-alpha
 */

package logic.imageprocessor;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

// TODO: Auto-generated Javadoc
/**
 * The Class FieldDetectorTest. Test FieldDetector funcionality.
 */
public class FieldDetectorTest {

  /**
   * Instantiates a new field detector test.
   */
  public FieldDetectorTest() {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }
 
  /**
   * Test detect.
   */
  @Test
  public void testDetect() {
    ImageProcessor processor = new OpencvImageProcessor();
    Detector detector = new FieldDetector(Imgcodecs.imread("testData/1-rgb.png"));
    Mat correctImage = Imgcodecs.imread("testData/8-noScore.png",
        Imgcodecs.IMREAD_GRAYSCALE);
    assertTrue(processor.compareImage(correctImage, detector.detect()));
  }
}
