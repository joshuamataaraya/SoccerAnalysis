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
 * The Class VideoAnalisisController is to analize a video looking for each
 * of the players and classify them on each frame.
 */
public class VideoAnalisisController extends Controller {

  /**
   * Is in charge of use all the logic classes needed to ensure the
   * analisis of a video.
   *
   * @param dto the dto
   * @return the object corresponding to a DtoVideoProcessor 
   */
  @Override
  public Object algoritm(Object dto) {
    try {
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
        Mat frame = (Mat) vp.readFrame();
        if (!frame.empty()) {
          //detect all the players in the current frame that is been analyzed
          frame = detectPlayers(frame);
          //Write the frame analyzed on the result video
          vp.writeFrame(frame);
        }
        frames--;
        
        //Notify the observers the current status of the analysis
        percentage = notifyFrames(totalFrames - frames, totalFrames, percentage);
      }
      //Save the video on a path and stores the path on the result DTO
      input.setOutVideoPath(vp.saveVideo());
      return input;      
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      return dto;
    }
  }
  
  /**
   * Detect players.
   *
   * @param image the image
   * @return the mat
   */
  private Mat detectPlayers(Mat image) {
    ImageProcessor processor = new OpencvImageProcessor();
    Detector fieldDetector = new FieldDetector(image);
    Detector playerDetector = new PlayerDetector(image);
    return (Mat) processor.paintPlayers(image, fieldDetector.detect(), playerDetector.detect());
  }

}
