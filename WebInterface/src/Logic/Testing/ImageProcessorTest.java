package Logic.Testing;

import static org.junit.Assert.*;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import Logic.ImageProcessor.ImageProcessor;

public class ImageProcessorTest {

	public ImageProcessorTest(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	@Test
	public void testCompareImage(){
		assertTrue(ImageProcessor.compareImage(Imgcodecs.imread("testData/1-rgb.png"), Imgcodecs.imread("testData/1-rgb.png")));
		assertTrue(ImageProcessor.compareImage(Imgcodecs.imread("testData/asdf.png"), Imgcodecs.imread("testData/asdf.png")));
	}
	@Test
	public void testRgb2hsv() {
		assertTrue(ImageProcessor.compareImage(Imgcodecs.imread("2-hsv.png"), ImageProcessor.rgb2hsv(Imgcodecs.imread("1-rgb.png"))));
	}
	@Test
	public void testGreenMask() {
		 assertTrue(ImageProcessor.compareImage(Imgcodecs.imread("3-binaria.png"), ImageProcessor.greenMask(Imgcodecs.imread("2-hsv.png"))));
	}
	@Test
	public void testDilate() {
		 assertTrue(ImageProcessor.compareImage(Imgcodecs.imread("4-dilatada.png"), ImageProcessor.dilate(Imgcodecs.imread("3-binaria.png"))));
	}
	@Test
	public void testImfill() {
		assertTrue(ImageProcessor.compareImage(Imgcodecs.imread("5-rellenada.png"), ImageProcessor.imfill(Imgcodecs.imread("4-dilatada.png"))));
	}
	@Test
	public void testBwareaopen() {
	    Mat realImage = Imgcodecs.imread("5-rellenada.png");
	    Mat grayRealImage = new Mat();
        Imgproc.cvtColor(realImage, grayRealImage, Imgproc.COLOR_RGB2GRAY);
		assertTrue(ImageProcessor.compareImage(Imgcodecs.imread("6-pulida.png"), ImageProcessor.bwareaopen(grayRealImage)));
	}
	@Test
	public void testFinalTouch() {
		Mat realImage = Imgcodecs.imread("6-pulida.png");
	    Mat grayRealImage = new Mat();
        Imgproc.cvtColor(realImage, grayRealImage, Imgproc.COLOR_RGB2GRAY);
        assertTrue(ImageProcessor.compareImage(Imgcodecs.imread("7-finalTouch.png"), ImageProcessor.finalTouch(grayRealImage)));
	}
	@Test
	public void testRemoveScore() {
		assertTrue(ImageProcessor.compareImage(Imgcodecs.imread("8-noScore.png"), ImageProcessor.removeScore(Imgcodecs.imread("7-finalTouch.png"))));
	}
	@Test
	public void testDetectField(){
		assertTrue(ImageProcessor.compareImage(Imgcodecs.imread("8-noScore.png"), ImageProcessor.detectField(Imgcodecs.imread("1-rgb.png"))));
	}

}
