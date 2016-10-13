/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package controller;

import java.util.Observable;

public abstract class Controller extends Observable {
  private Object input ;

  /**
   * Algoritm.
   *
   * @param dto the dto
   * @return the object
   * @throws Exception the exception
   */
  public abstract Object algoritm(Object dto);

  /**
   * Gets the input.
   *
   * @return the input
   */
  public Object getInput() {
    return input;
  }

  /**
   * Sets the input.
   *
   * @param input the input to set
   */
  public void setInput(Object input) {
    this.input = input;
  }
  
  /**
   * Notify frames.
   *
   * @param currentFrames the current frames
   * @param totalFrames the total frames
   * @param currentPercentage the current percentage
   * @return the int
   */
  protected int notifyFrames(int currentFrames, int totalFrames, int currentPercentage) {
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
