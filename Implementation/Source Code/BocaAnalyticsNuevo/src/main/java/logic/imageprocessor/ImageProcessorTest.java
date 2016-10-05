package logic.imageprocessor;

import static org.junit.Assert.*;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;

public class ImageProcessorTest {

  public ImageProcessorTest(){
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }
  
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

}
