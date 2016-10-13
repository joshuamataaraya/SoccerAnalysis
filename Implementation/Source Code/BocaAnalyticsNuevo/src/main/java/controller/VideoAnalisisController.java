/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package controller;

import datatransferobject.DtoVideoAnalisis;
import logic.imageprocessor.Detector;
import logic.imageprocessor.FieldDetector;
import logic.imageprocessor.ImageProcessor;
import logic.imageprocessor.OpencvImageProcessor;
import logic.imageprocessor.PlayerDetector;
import logic.videoprocessor.OpenCvVideoProcessor;

import org.opencv.core.Mat;






/**
 * The Class VideoAnalisisController.
 */
public class VideoAnalisisController extends Controller {

  /* (non-Javadoc)
   * @see controller.Controller#algoritm(java.lang.Object)
   */
  @Override
  public Object algoritm(Object dto) {
    DtoVideoAnalisis input = (DtoVideoAnalisis) dto;
    OpenCvVideoProcessor vp = new OpenCvVideoProcessor(
        input.getVideoPath(),
        input.getOutVideoPath());
    int frames = vp.getFrameCount();
    int totalFrames = vp.getFrameCount();
    int percentage = 0;
    while ( frames > 0 ) {
      Mat frame = (Mat) vp.readFrame();
      if (!frame.empty()) {
        frame = detectPlayers(frame);
        vp.writeFrame(frame);
      }
      frames--;
      percentage = notifyFrames(totalFrames - frames, totalFrames, percentage);
    }
    input.setOutVideoPath(vp.saveVideo());
    return input;
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

  /**
   * Notify frames.
   *
   * @param currentFrames the current frames
   * @param totalFrames the total frames
   * @param currentPercentage the current percentage
   * @return the int
   */
  private int notifyFrames(int currentFrames, int totalFrames, int currentPercentage) {
    int newPercentage = currentFrames * 100 / totalFrames;
    System.out.println(newPercentage);
    while (newPercentage > currentPercentage) {
      setChanged();
      notifyObservers();
      currentPercentage++;
    }
    return currentPercentage;
  }

}
