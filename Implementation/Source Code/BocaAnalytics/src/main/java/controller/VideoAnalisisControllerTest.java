/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package controller;

import datatransferobject.DtoVideoAnalysis;

import static org.junit.Assert.*;

import org.junit.Test;


public class VideoAnalisisControllerTest {

  /**
   * Video analisis algorithm test.
   * The class is used as a debug rather than a test
   */
  @Test
  public void videoAnalisisAlgorithmTest() {
    DtoVideoAnalysis dto = new DtoVideoAnalysis();
    dto.setOutVideoPath("testData/");
    dto.setVideoPath("testData/video2.avi");
    VideoAnalisisController va = new VideoAnalisisController();
    va.algoritm(dto);
    assertTrue(dto.getOutVideoPath() != "testData/" );
    
  }

}
