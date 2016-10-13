/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package datatransferobject;

public class DtoGroundTruth {
  private String videoPath;
  private String grundVideoPath;
  private double diceValue;
  
  /**
   * Gets the video path of the video to analyze.
   * 
   *
   * @return the videoPath
   */
  public String getVideoPath() {
    return videoPath;
  }
  
  /**
   * Sets the video path of the video to analyze.
   *
   * @param videoPath the videoPath to set
   */
  public void setVideoPath(String videoPath) {
    this.videoPath = videoPath;
  }
  
  /**
   * Gets the grund truth video path to compare with 
   * in the GroundTruthController class.
   *
   * @return the grundVideoPath
   */
  public String getGrundVideoPath() {
    return grundVideoPath;
  }
  
  /**
   * Sets the grund truth video path to compare with 
   * in the GroundTruthController class.
   *
   * @param grundVideoPath the grundVideoPath to set
   */
  public void setGrundVideoPath(String grundVideoPath) {
    this.grundVideoPath = grundVideoPath;
  }
  
  /**
   * Gets the dice value, the result of the calculation in the
   * GroundTruthController will be here.
   *
   * @return the diceValue
   */
  public double getDiceValue() {
    return diceValue;
  }
  
  /**
   * Sets the dice value, the result of the calculation in the
   * GroundTruthController will be here.
   *
   * @param diceValue the diceValue to set
   */
  public void setDiceValue(double diceValue) {
    this.diceValue = diceValue;
  }
}
