/**
 * 
 */
package logic.videoprocessor;

import static org.junit.Assert.*;

import org.junit.Test;
import org.opencv.core.Core;

public class OpenCVVideoProcessorTest {

	@Test
	public void test() {

		OpenCVVideoProcessor vp = new OpenCVVideoProcessor("testData/video.mp4");
		Object o = vp.getVideoFrames();
	}

}
