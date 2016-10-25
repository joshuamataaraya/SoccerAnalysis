/*
 * @author Adrian Lopez Quesada
 * @version v0.1.1-alpha
 */

package api;

import controller.VideoAnalisisController;
import datatransferobject.DtoVideoAnalysis;
import org.junit.Test;

/**
 * The Class LargeFileUploadTest.
 * Test Class for JUnit
 */
public class LargeFileUploadTest {
  
  /**
   * Video analisis algorithm test.
   * A test to evaluate the algorithm with the best frame rate and with large files
   */
  @Test
  public void videoAnalisisAlgorithmTest() {
    DtoVideoAnalysis dto = new DtoVideoAnalysis();
    dto.setOutVideoPath("testData/");
    dto.setVideoPath("testData/inputGroundTruth.avi");
    VideoAnalisisController va = new VideoAnalisisController();
    va.algoritm(dto);
    System.out.println("Revisar el nuevo archivo generado"); 
    //print para revisar el archivo, la prueba se cae si algo pasa mal en todo el proceso
  }
}
