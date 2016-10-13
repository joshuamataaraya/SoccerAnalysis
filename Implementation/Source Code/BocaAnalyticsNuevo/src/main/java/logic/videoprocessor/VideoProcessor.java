/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package logic.videoprocessor;

/**
 * The Class VideoProcessor.
 */
public abstract class VideoProcessor {
  
  /** The file path. */
  protected String filePath;
  
  /** The out file path. */
  protected String outFilePath;
  
  /** The frames count. */
  protected int framesCount;
  
  /**
   * Instantiates a new video processor.
   *
   * @param filePath the file path of the video to analyze.
   * @param outFilePath the out file path where the result has to be stored.
   */
  public VideoProcessor( String filePath, String outFilePath ) {
    this.filePath = filePath;
    this.outFilePath = outFilePath;
    openVideo();
    setFrameCount();
  }
  
  /**
   * @param filePath the file path of the video to analyze. 
   */
  public VideoProcessor(String filePath) {
    this.filePath = filePath;
    openVideo();
    setFrameCount();
  }
  
  /**
   * Open video.
   * Does all the necessary steps to open a video using the chosen library.
   */
  protected abstract void openVideo();
  
  /**
   * Sets the frame count.
   * Stores on the framesCount variable the number of frames in the video
   */
  protected abstract void setFrameCount();
  
  /**
   * Reads a frame form the video.
   *
   * @return the object
   */
  public abstract Object readFrame();
  
  /**
   * Writes frame on the video that is being created.
   *
   * @param frame the frame
   */
  public abstract void writeFrame(Object frame);
  
  /**
   * Save video on the output Video path.
   *
   * @return the string
   */
  public abstract String saveVideo();
  
  /**
   * Gets the frame count attribute.
   *
   * @return the frame count
   */
  public int getFrameCount() {
    return framesCount;
  }

}
