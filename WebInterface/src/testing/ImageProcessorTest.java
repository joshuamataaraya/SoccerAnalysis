package testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import logic.imageprocessor.Detector;
import logic.imageprocessor.FieldDetector;
import logic.imageprocessor.ImageProcessorr;



public class ImageProcessorTest {

  public ImageProcessorTest(){
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
  }
  
  @Test
  public void testCompareImage() {
    assertTrue(ImageProcessorr.compareImage(
        Imgcodecs.imread("testData/1-rgb.png", Imgcodecs.IMREAD_GRAYSCALE), 
            Imgcodecs.imread("testData/1-rgb.png", Imgcodecs.IMREAD_GRAYSCALE)));
    assertFalse(ImageProcessorr.compareImage(
        Imgcodecs.imread("testData/asdf.png", Imgcodecs.IMREAD_GRAYSCALE), 
            Imgcodecs.imread("testData/1-rgb.png", Imgcodecs.IMREAD_GRAYSCALE)));
  }
  
  @Test
  public void testRgb2hsv() {
    Mat hsv1 = new Mat();
    Imgproc.cvtColor(Imgcodecs.imread("testData/1-rgb.png"), 
        hsv1, Imgproc.COLOR_RGB2HSV);
    Mat hsv2 = ImageProcessorr.rgb2hsv(Imgcodecs.imread("testData/1-rgb.png"));
    List<Mat> channel1 = new ArrayList<>();
    List<Mat> channel2 = new ArrayList<>();
    Core.split(hsv2, channel2);
    Core.split(hsv1, channel1);
    //compare hsv channels
    /*Imgcodecs.imwrite("1.png", channel1.get(0));
    Imgcodecs.imwrite("2.png", channel2.get(0));*/
    assertTrue(ImageProcessorr.compareImage(channel1.get(0), channel2.get(0)));
  }
    
  @Test
  public void testGreenMask() {
    assertTrue(ImageProcessorr.compareImage(Imgcodecs.imread("testData/3-binaria.png", 
        Imgcodecs.IMREAD_GRAYSCALE), 
          ImageProcessorr.greenMask(Imgcodecs.imread("testData/2-hsv.png"))));
  }
  
  @Test
  public void testDilate() {
    Mat realImage =  ImageProcessorr.dilate(Imgcodecs.imread("testData/3-binaria.png"));
    Imgproc.cvtColor(realImage, realImage, Imgproc.COLOR_RGB2GRAY);
    assertTrue(ImageProcessorr.compareImage(Imgcodecs.imread("testData/4-dilatada.png",
          Imgcodecs.IMREAD_GRAYSCALE), realImage));
  }
    
  @Test
  public void testImfill() {
    Mat dilatedImage =  ImageProcessorr.dilate(Imgcodecs.imread("testData/3-binaria.png"));
    Imgproc.cvtColor(dilatedImage, dilatedImage, Imgproc.COLOR_RGB2GRAY);
    Mat realImage =  ImageProcessorr.imfill(dilatedImage);
    Mat correctImage = Imgcodecs.imread("testData/5-rellenada.png",
        Imgcodecs.IMREAD_GRAYSCALE);
    assertTrue(ImageProcessorr.compareImage(ImageProcessorr.bwareaopen(correctImage), 
        ImageProcessorr.bwareaopen(realImage)));
  }
    
  @Test
  public void testBwareaopen() {
    Mat realImage = Imgcodecs.imread("testData/5-rellenada.png");
    Mat grayRealImage = new Mat();
    Imgproc.cvtColor(realImage, grayRealImage, Imgproc.COLOR_RGB2GRAY);
    assertTrue(ImageProcessorr.compareImage(Imgcodecs.imread("testData/6-pulida.png",
        Imgcodecs.IMREAD_GRAYSCALE), 
          ImageProcessorr.bwareaopen(grayRealImage)));
  }
  
  @Test
  public void testFinalTouch() {
    Mat realImage = ImageProcessorr.finalTouch(Imgcodecs.imread("testData/6-pulida.png",
        Imgcodecs.IMREAD_GRAYSCALE));
    Mat correctImage = Imgcodecs.imread("testData/7-finalTouch.png", 
        Imgcodecs.IMREAD_GRAYSCALE);
    assertTrue(ImageProcessorr.compareImage(correctImage, realImage));
  }
    
  @Test
  public void testRemoveScore() {
    Mat realImage = ImageProcessorr.removeScore(Imgcodecs.imread("testData/7-finalTouch.png"));
    Imgproc.cvtColor(realImage, realImage, Imgproc.COLOR_BGR2GRAY);
    Mat correctImage = Imgcodecs.imread("testData/8-noScore.png",
        Imgcodecs.IMREAD_GRAYSCALE);
    assertTrue(ImageProcessorr.compareImage(correctImage, realImage));
  }
  
  @Test 
  public void testDetectField(){
    /*Mat realImage = ImageProcessorr.detectField(Imgcodecs.imread("testData/1-rgb.png"));
    /*Mat correctImage = Imgcodecs.imread("testData/8-noScore.png",
        Imgcodecs.IMREAD_GRAYSCALE);
    assertTrue(ImageProcessorr.compareImage(correctImage, realImage));*/
    Detector detector = new FieldDetector(Imgcodecs.imread("testData/1-rgb.png"));
    detector.detect();
  }

}
