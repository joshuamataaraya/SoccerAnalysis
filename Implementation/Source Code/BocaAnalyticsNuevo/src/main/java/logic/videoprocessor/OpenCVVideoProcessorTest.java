/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package logic.videoprocessor;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class OpenCVVideoProcessorTest.
 */
public class OpenCvVideoProcessorTest {

  /**
   * Count frames test.
   * Compares the number of frames of the system result with a known number 
   * of frames 
   */
  @Test
  public void countFramesTest() {
    OpenCvVideoProcessor vp = new OpenCvVideoProcessor("testData/binarias.mpeg","testData/");
    int expected = 154; //GroundTruth: 154 Frames on the test video
    int actual = vp.getFrameCount();
    assertEquals(expected, actual);
  }

//  /**
//   * Creates the video test.
//   */
//  @Test
//  public void createVideoTest() {
//    OpenCvVideoProcessor vp = new OpenCvVideoProcessor("testData/video.mp4","testData/");
//    int frames = vp.getFrameCount();
//    while ( frames > 0 ) {
//      Mat frame = (Mat) vp.readFrame();
//      vp.writeFrame(frame);
//      frames--;
//    }
//    vp.saveVideo();
//  }

}
