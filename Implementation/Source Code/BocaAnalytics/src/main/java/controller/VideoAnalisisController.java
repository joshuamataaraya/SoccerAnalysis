/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package controller;

import datatransferobject.DtoVideoAnalysis;
import logic.imageprocessor.Detector;
import logic.imageprocessor.FieldDetector;
import logic.imageprocessor.ImageProcessor;
import logic.imageprocessor.OpencvImageProcessor;
import logic.imageprocessor.PlayerDetector;
import logic.videoprocessor.OpenCvVideoProcessor;

import org.opencv.core.Mat;

/**
 * The Class VideoAnalisisController is to analyze a video looking for each
 * of the players and classify them on each frame. In the current version 
 * it just looks for each player on the video.
 */
public class VideoAnalisisController extends Controller {

  /**
   * Is in charge of use all the logic classes needed to ensure the
   * analysis of a video.
   * @param dto the dto Object has to be able to be casted to the class DtoVideoAnalysis.
   * @return the object corresponding to a DtoVideoProcessor, in case of an error the input object 
   */
  @Override
  public Object algoritm(Object dto) {
    
    // Cast to DtoVideoAnalysis class
    DtoVideoAnalysis input = (DtoVideoAnalysis) dto;
    
    //Initialize the class to analyze the video
    OpenCvVideoProcessor vp = new OpenCvVideoProcessor(
        input.getVideoPath(),
        input.getOutVideoPath());
    
    int frames = vp.getFrameCount();
    int totalFrames = vp.getFrameCount();
    int percentage = 0;
    
    //detects the players on each frame of the video.
    while ( frames > 0 ) {
      Mat frame = new Mat();
      frame = (Mat) vp.readFrame();
      if (!frame.empty()) {
        //detect all the players in the current frame that is been analyzed
        
        frame = detectPlayers(frame);
        //Write the frame analyzed on the result video
        vp.writeFrame(frame);
      }
      frames--;
      if (frames % 20 == 0) {
        System.gc();//clean memory
      }
      
      //Notify the observers the current status of the analysis
      percentage = notifyFrames(totalFrames - frames, totalFrames, percentage);
    }
    
    //Save the video on a path and stores the path on the result DTO
    input.setOutVideoPath(vp.saveVideo());
    return input;      

  }
  
  /**
   * Detect players uses the needed classes from the package logic.imageprocessor
   * to detect the possible players in a OpenCV.Mat class
   *
   * @param image the image, must be an opencv mat. In RGB format.
   * @return the mat opencv mat with the players in red color and the background in black
   */
  private Mat detectPlayers(Mat image) {
    ImageProcessor processor = new OpencvImageProcessor();
    Detector fieldDetector = new FieldDetector(image);
    Detector playerDetector = new PlayerDetector(image);
    Object field = fieldDetector.detect();
    Object players = playerDetector.detect();
    //Imgcodecs.imwrite("testData/realFrames/"+ frames + ".png", (Mat) field);
    //Imgcodecs.imwrite("testData/realFrames2/"+ frames + ".png", (Mat) players);
    return (Mat) processor.paintPlayers(image, field, players);
  }

}
