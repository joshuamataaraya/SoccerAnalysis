/**
 * 
 */
package logic.videoprocessor;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;

public class OpenCVVideoProcessorTest {

	@Test
	public void countFramesTest() {
		OpenCVVideoProcessor vp = new OpenCVVideoProcessor("testData/video.mp4","testData/");
		int expected = 132;
		int actual = vp.getFrameCount();
		assertEquals(expected, actual);
	}
	@Test
	public void createVideoTest(){
		OpenCVVideoProcessor vp = new OpenCVVideoProcessor("testData/video.mp4","testData/");
		int frames = vp.getFrameCount();
		while(frames>0){
			Mat frame = (Mat) vp.readFrame();
			vp.writeFrame(frame);
			frames--;
		}
		vp.saveVideo();
	}

}
