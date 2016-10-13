/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package datatransferobject;

public class Dtogroundtruth {
  private String videoPath;
  private String grundVideoPath;
  private double diceValue;
  
  /**
   * Gets the video path.
   *
   * @return the videoPath
   */
  public String getVideoPath() {
    return videoPath;
  }
  
  /**
   * Sets the video path.
   *
   * @param videoPath the videoPath to set
   */
  public void setVideoPath(String videoPath) {
    this.videoPath = videoPath;
  }
  
  /**
   * Gets the grund video path.
   *
   * @return the grundVideoPath
   */
  public String getGrundVideoPath() {
    return grundVideoPath;
  }
  
  /**
   * Sets the grund video path.
   *
   * @param grundVideoPath the grundVideoPath to set
   */
  public void setGrundVideoPath(String grundVideoPath) {
    this.grundVideoPath = grundVideoPath;
  }
  
  /**
   * Gets the dice value.
   *
   * @return the diceValue
   */
  public double getDiceValue() {
    return diceValue;
  }
  
  /**
   * Sets the dice value.
   *
   * @param diceValue the diceValue to set
   */
  public void setDiceValue(double diceValue) {
    this.diceValue = diceValue;
  }
}
