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
 * The Class PlayerDetectorTest. Test functionality of the class PlayerDetector.
 */
public class PlayerDetectorTest {

  /**
   * Instantiates a new player detector test.
   */
  public PlayerDetectorTest() {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }
  
  /**
   * Test detect. 
   */
  @Test
  public void testDetect() {
    ImageProcessor processor = new OpencvImageProcessor();
    Detector detector = new PlayerDetector(Imgcodecs.imread("testData/1-rgb.png"));
    Mat correctImage = Imgcodecs.imread("testData/jugadores.png",
        Imgcodecs.IMREAD_GRAYSCALE);
    Mat realImage = (Mat) detector.detect();
    assertTrue(processor.compareImage(realImage, correctImage));
  }

}
