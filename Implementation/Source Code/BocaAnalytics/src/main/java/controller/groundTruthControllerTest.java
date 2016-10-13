///*
// * @author Joshua Mata Araya
// * @version v0.1.1-alpha
// */
//
//package controller;
//
//import datatransferobject.DtoGroundTruth;
//import org.junit.Test;
//
//  
//
//
//
///**
// * The Class GroundTruthControllerTest.
// * The class is used as a debug rather than a test
// */
//public class GroundTruthControllerTest {
//  
//  /**
//   * Test ground truth.
//   *
//   * @throws Exception the exception
//   */
//  @Test
//  public void testGroundTruth() throws Exception {
//    DtoGroundTruth dto = new DtoGroundTruth();
//    dto.setVideoPath("testData/inputGroundTruth.avi");
//    dto.setGrundVideoPath("testData/groundTruth.mpeg");
//  
//    GroundTruthController gt = new GroundTruthController();
//    dto = (DtoGroundTruth)gt.algoritm(dto);
//    double dice = dto.getDiceValue();
//    System.out.println("Dice: " + dice);
//  }
//
//}