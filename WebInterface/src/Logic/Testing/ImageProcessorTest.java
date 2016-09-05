package Logic.Testing;

import static org.junit.Assert.*;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import Logic.ImageProcessor.ImageProcessorr;

public class ImageProcessorTest {

	public ImageProcessorTest(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	@Test
	public void testCompareImage(){
		assertTrue(ImageProcessorr.compareImage(Imgcodecs.imread("testData/1-rgb.png"), Imgcodecs.imread("testData/1-rgb.png")));
		assertTrue(ImageProcessorr.compareImage(Imgcodecs.imread("testData/asdf.png"), Imgcodecs.imread("testData/asdf.png")));
	}
	@Test
	public void testRgb2hsv() {
		assertTrue(ImageProcessorr.compareImage(Imgcodecs.imread("testData/2-hsv.png"), ImageProcessorr.rgb2hsv(Imgcodecs.imread("testData/1-rgb.png"))));
	}
	@Test
	public void testGreenMask() {
		 assertTrue(ImageProcessorr.compareImage(Imgcodecs.imread("testData/3-binaria.png"), ImageProcessorr.greenMask(Imgcodecs.imread("testData/2-hsv.png"))));
	}
	@Test
	public void testDilate() {
		 assertTrue(ImageProcessorr.compareImage(Imgcodecs.imread("testData/4-dilatada.png"), ImageProcessorr.dilate(Imgcodecs.imread("testData/3-binaria.png"))));
	}
	@Test
	public void testImfill() {
		assertTrue(ImageProcessorr.compareImage(Imgcodecs.imread("testData/5-rellenada.png"), ImageProcessorr.imfill(Imgcodecs.imread("testData/4-dilatada.png"))));
	}
	@Test
	public void testBwareaopen() {
	    Mat realImage = Imgcodecs.imread("testData/5-rellenada.png");
	    Mat grayRealImage = new Mat();
        Imgproc.cvtColor(realImage, grayRealImage, Imgproc.COLOR_RGB2GRAY);
		assertTrue(ImageProcessorr.compareImage(Imgcodecs.imread("testData/6-pulida.png"), ImageProcessorr.bwareaopen(grayRealImage)));
	}
	@Test
	public void testFinalTouch() {
		Mat realImage = Imgcodecs.imread("testData/6-pulida.png");
	    Mat grayRealImage = new Mat();
        Imgproc.cvtColor(realImage, grayRealImage, Imgproc.COLOR_RGB2GRAY);
        assertTrue(ImageProcessorr.compareImage(Imgcodecs.imread("testData/7-finalTouch.png"), ImageProcessorr.finalTouch(grayRealImage)));
	}
	@Test
	public void testRemoveScore() {
		assertTrue(ImageProcessorr.compareImage(Imgcodecs.imread("testData/8-noScore.png"), ImageProcessorr.removeScore(Imgcodecs.imread("testData/7-finalTouch.png"))));
	}
	@Test
	public void testDetectField(){
		assertTrue(ImageProcessorr.compareImage(Imgcodecs.imread("testData/8-noScore.png"), ImageProcessorr.detectField(Imgcodecs.imread("testData/1-rgb.png"))));
	}

}
