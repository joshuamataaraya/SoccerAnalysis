/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package datatransferobject;

// TODO: Auto-generated Javadoc
/**
 * The Class DtoVideoAnalisis.
 */
public class DtoVideoAnalysis {
  
  /** The video path. */
  private String videoPath;
  
  /** The out video path. */
  private String outVideoPath;
  
  /** The report path. */
  private String reportPath;
  
  /** The time of processing. */
  private int timeOfProcessing;
  
  /**
   * Gets the video path of the video to analyze in the class
   * VideoAnalisisController.
   *
   * @return the video path
   */
  public String getVideoPath() {
    return videoPath;
  }
  
  /**
   * Sets the video path to analyze.
   *
   * @param videoPath the videoPath to set
   */
  public void setVideoPath(String videoPath) {
    this.videoPath = videoPath;
  }
  
  /**
   * Gets the time of processing all the video.
   *
   * @return the timeOfProcessing
   */
  public int getTimeOfProcessing() {
    return timeOfProcessing;
  }
  
  /**
   * Sets the time of processing all the video.
   *
   * @param timeOfProcessing the timeOfProcessing to set
   */
  public void setTimeOfProcessing(int timeOfProcessing) {
    this.timeOfProcessing = timeOfProcessing;
  }
  
  /**
   * Gets the out video path where the result video is stored.
   *
   * @return the outVideoPath
   */
  public String getOutVideoPath() {
    return outVideoPath;
  }
  
  /**
   * Sets the out video path where the result video is stored.
   *
   * @param outVideoPath the outVideoPath to set
   */
  public void setOutVideoPath(String outVideoPath) {
    this.outVideoPath = outVideoPath;
  }
  
  /**
   * Gets the report path of the results in the analysis.
   *
   * @return the reportPath
   */
  public String getReportPath() {
    return reportPath;
  }
  
  /**
   * Sets the report path of the results in the analysis.
   *
   * @param reportPath the reportPath to set
   */
  public void setReportPath(String reportPath) {
    this.reportPath = reportPath;
  }
}
