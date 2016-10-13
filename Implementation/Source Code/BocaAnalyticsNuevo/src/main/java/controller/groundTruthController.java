/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package controller;

import datatransferobject.DtoGroundTruth;
import logic.imageprocessor.Detector;
import logic.imageprocessor.FieldDetector;
import logic.imageprocessor.ImageProcessor;
import logic.imageprocessor.OpencvImageProcessor;
import logic.imageprocessor.PlayerDetector;
import logic.videoprocessor.OpenCvVideoProcessor;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


import java.util.stream.DoubleStream;

/**
 * The Class GroundTruthController.
 * This is the interface of the functionality for the ground controller
 * analizer.
 */
public class GroundTruthController extends Controller {
  
  /** The dice values. */
  private double [ ] diceValues;
  
  /* (non-Javadoc)
   * It is expected to receive an Object able to be 
   * casted to the class DTOGoundTruth.
   * The return Object contains the results according to the dto DTOGoundTruth
   * @see controller.Controller#algoritm(java.lang.Object)
   */
  @Override
  public Object algoritm(Object dto) throws Exception  {
    DtoGroundTruth input = (DtoGroundTruth) dto;
    OpenCvVideoProcessor vp = new OpenCvVideoProcessor(
        input.getVideoPath());
    OpenCvVideoProcessor vpGroundTruth = new OpenCvVideoProcessor(
        input.getGrundVideoPath());
    
    int frames = vp.getFrameCount();
    diceValues = new double[frames];
    while ( frames > 0) {
      Mat frame = (Mat) vp.readFrame();
      Mat frameGroundTruth = (Mat) vpGroundTruth.readFrame();
      if ( !frame.empty() && !frameGroundTruth.empty() ) {
        //insert a new calculated value on the array of diceValues
        diceValues [ frames - 1 ] = getDiceValue( frame, frameGroundTruth ) ;
      }
      frames--;
    }
    
    //The sum and the division is to obtain the average of the values that are inside the
    //diveValues ArrayList
    input.setDiceValue( DoubleStream.of( diceValues ).sum() / diceValues.length);
    return input;
  }
  
  /**
   * Gets the dice value of an image based on a ground truth.
   * Uses OpenCV function Imgproc.cvtColor():
   * http://docs.opencv.org/java/2.4.9/org/opencv/imgproc/Imgproc.html
   * 
   * @param mat the mat
   * @param matGroundTruth the mat ground truth
   * @return the dice value
   */
  private double getDiceValue( Object mat, Object matGroundTruth ) {
    ImageProcessor processor = new OpencvImageProcessor();
    Detector fieldDetector = new FieldDetector((Mat)mat);
    Detector playerDetector = new PlayerDetector((Mat)mat);
    Imgproc.cvtColor((Mat)matGroundTruth,(Mat) matGroundTruth,Imgproc.COLOR_BGR2GRAY);
    Double dice = processor.dice(matGroundTruth, fieldDetector.detect(), playerDetector.detect());
    return dice;
  }
}
