package logic.imageprocessor;


abstract class ImageProcessor {

  public ImageProcessor() { }
  
  public abstract Object mask(Object image, Object alphaMin, Object alphaMax);
  
  public abstract Object rgb2hsv(Object image);
  
  public abstract Object dilate(Object image);
  
  public abstract Object complement(Object image);
  
  public abstract Object or(Object image1, Object image2);
  
  public abstract Object floodFill(Object image, Object point,  Object color);
  
  public abstract Object drawRectangle(Object image, Object point1, Object point2, Object color);
  
  public abstract Object fillContours(Object image, Object contours, Object color);
  
  public abstract Object findContours(Object image);
  
  public abstract Boolean compareImage(Object image1, Object image2);
  
  public abstract Object hh(Object image);
}
