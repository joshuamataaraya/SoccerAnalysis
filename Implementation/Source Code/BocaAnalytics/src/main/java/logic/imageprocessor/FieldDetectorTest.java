/*
 * @author Josué Arrieta Salas
 * @version v0.1.1-alpha
 */

package logic.imageprocessor;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
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
    //Integration test for field detection algorithm.
    ImageProcessor processor = new OpencvImageProcessor();
    Detector detector = new FieldDetector(Imgcodecs.imread("testData/1-rgb.png"));
    //Uses algorithm.
    Mat correctImage = Imgcodecs.imread("testData/8-noScore.png",
        Imgcodecs.IMREAD_GRAYSCALE);
    assertTrue(processor.compareImage(correctImage, detector.detect()));
  }
  
  @Test
  public void testGreenMask() {
    // Unit test for the function greenMask.
    ImageProcessor processor = new OpencvImageProcessor();
    FieldDetector detector = new FieldDetector(null);
    // uses ground truth.
    assertTrue(processor.compareImage(Imgcodecs.imread("testData/3-binaria.png", 
        Imgcodecs.IMREAD_GRAYSCALE),
          detector.greenMask(Imgcodecs.imread("testData/2-hsv.png"))));
  }
  
  @Test
  public void testImfill() {
    // Unit test for the function imfill.
    ImageProcessor processor = new OpencvImageProcessor();
    OpencvDetector detector = new FieldDetector(null);
    Mat realImage =  detector.imfill(Imgcodecs.imread("testData/4-dilatada.png",
        Imgcodecs.IMREAD_GRAYSCALE), new Point(0,0));
    // uses groundtruth
    Mat correctImage = Imgcodecs.imread("testData/5-rellenada.png",
        Imgcodecs.IMREAD_GRAYSCALE);   
    assertTrue(processor.compareImage(correctImage, realImage));
  }
  
  @Test
  public void testBwareaopen() {
    // Unit test for function bwareopen.
    ImageProcessor processor = new OpencvImageProcessor();
    FieldDetector detector = new FieldDetector(null);
    Mat realImage = detector.bwareaopen(Imgcodecs.imread("testData/5-rellenada.png",
        Imgcodecs.IMREAD_GRAYSCALE));
    // uses groundtruth
    Mat correctImage = Imgcodecs.imread("testData/6-pulida.png",
        Imgcodecs.IMREAD_GRAYSCALE);
    
    assertTrue(processor.compareImage(correctImage,realImage));
  }
  
  
}
