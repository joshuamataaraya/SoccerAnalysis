/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package controller;

import java.util.Observable;

/**
 * The Class Controller is the interface of all the logic of the program.
 * It extends the Java.util.Observable to notify the UI of the video process.
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
   * Method that calculate the percentage of frames processed
   *     by a simple rule of third, if the value changes it also notifies
   *     its observers about it.
   * @param currentFrames the int containing the amount of frames processed
   * @param totalFrames the int containing the total frames of the video
   * @param currentPercentage the int containing the current percentage of frames
   *     processed
   * @return the int of the percentage, it can be the same as the parameter currentPercentage
   *     if the value didnt change or a new one if it changed.
   */
  protected int notifyFrames(int currentFrames, int totalFrames, int currentPercentage) {
    int newPercentage = currentFrames * 100 / totalFrames; 
    // sets the new percentage of the processed frames
    while (newPercentage > currentPercentage) { //compares new percentage with old one
      //if the percentage changed
      setChanged(); // observable class method
      notifyObservers(); //observable notification to observers
      currentPercentage++; //changes value for return
    }
    return currentPercentage;
  }
}
