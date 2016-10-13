/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package logic.videoprocessor;

import static org.junit.Assert.assertEquals;


import org.junit.Test;
import org.opencv.core.Mat;

// TODO: Auto-generated Javadoc
/**
 * The Class OpenCVVideoProcessorTest.
 */
public class OpenCvVideoProcessorTest {

  /**
   * Count frames test.
   */
  @Test
  public void countFramesTest() {
    OpenCvVideoProcessor vp = new OpenCvVideoProcessor("testData/video.mp4","testData/");
    int expected = 132;
    int actual = vp.getFrameCount();
    assertEquals(expected, actual);
  }

  /**
   * Creates the video test.
   */
  @Test
  public void createVideoTest() {
    OpenCvVideoProcessor vp = new OpenCvVideoProcessor("testData/video.mp4","testData/");
    int frames = vp.getFrameCount();
    while ( frames > 0 ) {
      Mat frame = (Mat) vp.readFrame();
      vp.writeFrame(frame);
      frames--;
    }
    vp.saveVideo();
  }

}
