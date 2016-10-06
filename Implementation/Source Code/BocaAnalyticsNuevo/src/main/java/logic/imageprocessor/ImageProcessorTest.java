package logic.imageprocessor;

import static org.junit.Assert.*;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
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
  }

}
