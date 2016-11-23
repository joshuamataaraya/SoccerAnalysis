/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package controller;

import datatransferobject.DtoGroundTruth;

import static org.junit.Assert.*;

import org.junit.Test;

  



/**
 * The Class GroundTruthControllerTest.
 * The class is used as a debug rather than a test
 */
public class groundTruthControllerTest {
  
  /**
   * Test ground truth.
   *
   * @throws Exception the exception
   */
  @Test
  public void testGroundTruth() throws Exception {
    DtoGroundTruth dto = new DtoGroundTruth();
    dto.setVideoPath("testData/video2.avi");
    dto.setGrundVideoPath("testData/binarias.mpeg");
  
    GroundTruthController gt = new GroundTruthController();
    dto = (DtoGroundTruth)gt.algoritm(dto);
    double dice = dto.getDiceValue();
    System.out.println("Dice: " + dice);
    
    assertTrue(dice >= 0.75);
  }

}