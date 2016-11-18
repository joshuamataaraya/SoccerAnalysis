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
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


import java.util.stream.DoubleStream;

/**
 * The Class GroundTruthController.
 * This is the interface of the functionality for the ground controller
 * analyzer.
 * The idea of the algoritm implemented here is to compare the analysis that tha
 * program does on a video, with a ground truth test that is already known.
 */
public class GroundTruthController extends Controller {
  
  /** The dice values. */
  private double [ ] diceValues;
  
  /**
   * This method compares the analysis that the program does of an input 
   * that a user specifies as correct (Ground truth) and the same video 
   * without been procesed.
   *
   * @param dto the dto Object has to be able to be casted to the class DTOGoundTruth.
   * @return the object can be casted to the class DtoGroundTruth
   */
  @Override
  public Object algoritm(Object dto) {
    try {
      //Cast to DtoGroundTruth the parameter
      DtoGroundTruth input = (DtoGroundTruth) dto;
      //Initialize the class from openCV to process the video to compare
      OpenCvVideoProcessor vp = new OpenCvVideoProcessor(
          input.getVideoPath());
      //Initialize the class form openCV to process the Groud Truth video
      OpenCvVideoProcessor vpGroundTruth = new OpenCvVideoProcessor(
          input.getGrundVideoPath());
      
      int frames = vp.getFrameCount(); //get frames count form the input video
      int totalFrames = vp.getFrameCount(); //get frames count from ground truth video
      int percentage = 0;
      diceValues = new double[frames];
      //Each frame in the input video is been compared with each frame from the 
      //gound truth.
      while ( frames > 0) {
        //read the next frame from each video
        Mat frame = (Mat) vp.readFrame();
        Mat frameGroundTruth = (Mat) vpGroundTruth.readFrame();
        
        //check if each frame is not empty
        if ( !frame.empty() && !frameGroundTruth.empty() ) {
          //insert a new calculated value on the array of diceValues
          double diceValue =  getDiceValue( frame, frameGroundTruth,frames) + 0.01;
          System.out.println("Frame " + frames + " : " + diceValue);
          diceValues [ frames - 1 ] = diceValue;
        }
        frames--;
        //notifies the observers the status of the analysis
        if (frames % 20 == 0) {
          System.gc();//clean memory
        }
        percentage = notifyFrames(totalFrames - frames, totalFrames, percentage);
      }
      
      //The sum and the division is to obtain the average of the values that are inside the
      //diveValues ArrayList
      input.setDiceValue( DoubleStream.of( diceValues ).sum() / diceValues.length);
      return input;      
    } catch ( Exception exception) {
      System.out.println(exception.getMessage());
      return dto;
    }
  }
  
  /**
   * Gets the dice value of an image based on a ground truth.
   * Uses OpenCV function Imgproc.cvtColor():
   * http://docs.opencv.org/java/2.4.9/org/opencv/imgproc/Imgproc.html
   * 
   * @param mat The object has to be able to be casted to the class Mat from OpenCV.
   * @param matGroundTruth the object has to be from the class Mat openCV
   * @return the dice value
   */
  private double getDiceValue( Object mat, Object matGroundTruth, int frames) {
    //Initialize the image processor
    ImageProcessor processor = new OpencvImageProcessor();

    //Initialize the classes to detect elements on the images
    Detector fieldDetector = new FieldDetector((Mat)mat);
    Detector playerDetector = new PlayerDetector((Mat)mat);
    
    //opencv class to transform images to Imgproc.COLOR_BGR2GRAY type.
    Imgproc.cvtColor((Mat)matGroundTruth,(Mat) matGroundTruth,Imgproc.COLOR_BGR2GRAY);
    
    //get the dice value of the analysis of the two images
    //Imgcodecs.imwrite("testData/debug1/" + frames + ".png", (Mat) matGroundTruth);
    Object field = fieldDetector.detect();
    Object players = playerDetector.detect();
    Imgcodecs.imwrite("testData/debug2/" + frames + ".png", (Mat) processor.getPlayers(field, players));
    Double dice = processor.dice(matGroundTruth, (Mat) field, (Mat) players);
    return dice;
  }
}
