/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package controller;

import datatransferobject.Dtogroundtruth;
import logic.imageprocessor.Detector;
import logic.imageprocessor.FieldDetector;
import logic.imageprocessor.ImageProcessor;
import logic.imageprocessor.OpencvImageProcessor;
import logic.imageprocessor.PlayerDetector;
import logic.videoprocessor.OpenCvVideoProcessor;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;


import java.util.stream.DoubleStream;

public class GroundTruthController extends Controller {

  private double [ ] diceValues;
  
  /* (non-Javadoc)
   * @see controller.Controller#algoritm(java.lang.Object)
   */
  @Override
  public Object algoritm(Object dto) {
    Dtogroundtruth input = (Dtogroundtruth) dto;
    OpenCvVideoProcessor vp = new OpenCvVideoProcessor(
        input.getVideoPath());
    OpenCvVideoProcessor vpGroundTruth = new OpenCvVideoProcessor(
        input.getGrundVideoPath());

    int frames = vp.getFrameCount();
    int totalFrames = vp.getFrameCount();
    int percentage = 0;
    diceValues = new double[frames];
    while ( frames > 0) {
      Mat frame = (Mat) vp.readFrame();
      Mat frameGroundTruth = (Mat) vpGroundTruth.readFrame();
      if ( !frame.empty() && !frameGroundTruth.empty() ) {
        diceValues [ frames - 1 ] = getDiceValue( frame, frameGroundTruth ) ;
      }
      frames--;
      percentage = notifyFrames(totalFrames - frames, totalFrames, percentage);
    }
    input.setDiceValue( DoubleStream.of( diceValues ).sum() / diceValues.length);
    return input;
  }
  
  /**
   * Gets the dice value.
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
