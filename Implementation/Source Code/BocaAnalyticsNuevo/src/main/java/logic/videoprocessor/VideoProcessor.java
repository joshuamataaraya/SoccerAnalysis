/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package logic.videoprocessor;

// TODO: Auto-generated Javadoc
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
   * @param filePath the file path
   * @param outFilePath the out file path
   */
  public VideoProcessor( String filePath, String outFilePath ) {
    this.filePath = filePath;
    this.outFilePath = outFilePath;
    openVideo();
    setFrameCount();
  }
  
  /**
   * Instantiates a new video processor.
   *
   * @param filePath the file path
   */
  public VideoProcessor(String filePath) {
    this.filePath = filePath;
    openVideo();
    setFrameCount();
  }
  
  /**
   * Open video.
   */
  protected abstract void openVideo();
  
  /**
   * Sets the frame count.
   */
  protected abstract void setFrameCount();
  
  /**
   * Read frame.
   *
   * @return the object
   */
  public abstract Object readFrame();
  
  /**
   * Write frame.
   *
   * @param frame the frame
   */
  public abstract void writeFrame(Object frame);
  
  /**
   * Save video.
   *
   * @return the string
   */
  public abstract String saveVideo();
  
  /**
   * Gets the frame count.
   *
   * @return the frame count
   */
  public int getFrameCount() {
    return framesCount;
  }

}
