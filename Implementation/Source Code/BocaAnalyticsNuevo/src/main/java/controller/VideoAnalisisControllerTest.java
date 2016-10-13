/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package controller;

import datatransferobject.DtoVideoAnalisis;
import org.junit.Test;


public class VideoAnalisisControllerTest {

  /**
   * Video analisis algorithm test.
   */
  @Test
  public void videoAnalisisAlgorithmTest() {
    DtoVideoAnalisis dto = new DtoVideoAnalisis();
    dto.setOutVideoPath("testData/");
    dto.setVideoPath("testData/video2.mp4");
    VideoAnalisisController va = new VideoAnalisisController();
    va.algoritm(dto);
  }

}
