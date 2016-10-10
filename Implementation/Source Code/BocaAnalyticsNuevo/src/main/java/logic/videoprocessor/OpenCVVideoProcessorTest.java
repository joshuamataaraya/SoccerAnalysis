/**
 * 
 */
package logic.videoprocessor;

import static org.junit.Assert.*;

import org.junit.Test;
import org.opencv.core.Core;

public class OpenCVVideoProcessorTest {

	@Test
	public void countFramesTest1() {
		OpenCVVideoProcessor vp = new OpenCVVideoProcessor("testData/video.mp4");
		int expected = 132;
		int actual = vp.countFrames();
		assertEquals(expected, actual);
	}
	@Test
	public void countFramesTest2() {
		OpenCVVideoProcessor vp = new OpenCVVideoProcessor("testData/video.mp4");
		vp.getVideoFrames();
		int expected = 132;
		int actual = vp.countFrames();
		assertEquals(expected, actual);
	}

}
