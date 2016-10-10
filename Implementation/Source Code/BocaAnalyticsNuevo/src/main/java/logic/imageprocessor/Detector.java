/*
 * @author Josué Arrieta Salas
 * @version v0.1.1-alpha
 */

package logic.imageprocessor;

/**
 * The Class Detector.
 */
public abstract class Detector {
  protected Object image;
  protected ImageProcessor processor;
  
  /**
   * Instantiates a new detector.
   *
   * @param image: must be an image in RGB format
   */
  public Detector(Object image) {
    this.image = image;
  }
  
  /**
   * Detects an object in an image
   *
   * @return the object which was detected. In binary format.
   */
  public abstract Object detect();
  
  public void setImage(Object image) {
    this.image = image;
  }

}