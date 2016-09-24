package logic.imageprocessor;

public abstract class Detector {
  protected Object image;
  protected ImageProcessor processor;
  
  public Detector(Object image) {
    this.image = image;
  }
  
  public abstract Object detect();
  
  public void setImage(Object image) {
    this.image = image;
  }

}