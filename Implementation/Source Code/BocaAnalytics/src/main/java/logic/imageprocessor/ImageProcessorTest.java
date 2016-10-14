/*
 * @author Josué Arrieta Salas
 * @version v0.1.1-alpha
 */

package logic.imageprocessor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;

// TODO: Auto-generated Javadoc
/**
 * The Class ImageProcessorTest.
 */
public class ImageProcessorTest {

  /**
   * Instantiates a new image processor test.
   */
  public ImageProcessorTest() {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }
  
  /**
   * Test compare image.
   */
  @Test
  public void testCompareImage() {
    ImageProcessor processor = new OpencvImageProcessor();
    assertTrue(processor.compareImage(
        Imgcodecs.imread("testData/1-rgb.png", Imgcodecs.IMREAD_GRAYSCALE), 
            Imgcodecs.imread("testData/1-rgb.png", Imgcodecs.IMREAD_GRAYSCALE)));
    assertFalse(processor.compareImage(
        Imgcodecs.imread("testData/asdf.png", Imgcodecs.IMREAD_GRAYSCALE), 
            Imgcodecs.imread("testData/1-rgb.png", Imgcodecs.IMREAD_GRAYSCALE)));
  }
  
  /*
  @Test
  //es usado estilo main
  public void testPaintPlayers() {
    ImageProcessor processor = new OpencvImageProcessor();
    Mat originalImage = Imgcodecs.imread("testData/1-rgb.png");
    Detector fieldDetector = new FieldDetector(originalImage);
    Detector playerDetector = new PlayerDetector(originalImage);
    processor.paintPlayers(originalImage, fieldDetector.detect(), playerDetector.detect());
    
    Mat groundTruth = Imgcodecs.imread("testData/groundTruth.png",Imgcodecs.IMREAD_GRAYSCALE);
    Double dice = processor.dice(groundTruth, fieldDetector.detect(), playerDetector.detect());
    System.out.println(dice);
  }*/

}
