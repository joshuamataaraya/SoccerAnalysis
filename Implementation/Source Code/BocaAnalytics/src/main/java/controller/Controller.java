/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package controller;

import java.util.Observable;

/**
 * The Class Controller is the interface of all the logic of the program.
 */
public abstract class Controller extends Observable {
  private Object input ;

  /**
   * Algoritm.
   * Method that does the purpose of the class
   * @param dto the dto sent by the websocket
   * @return the object, mostly a dto
   */
  public abstract Object algoritm(Object dto);

  /**
   * Gets the input.
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
   * Method that calculate the percentage and notifies if it changed
   * @param currentFrames the current processed frames
   * @param totalFrames the total frames of the full video
   * @param currentPercentage the current percentage of completness
   * @return the int the percentage after the calculation
   */
  protected int notifyFrames(int currentFrames, int totalFrames, int currentPercentage) {
    int newPercentage = currentFrames * 100 / totalFrames;
    while (newPercentage > currentPercentage) {
      setChanged();
      notifyObservers();
      currentPercentage++;
    }
    return currentPercentage;
  }
}
