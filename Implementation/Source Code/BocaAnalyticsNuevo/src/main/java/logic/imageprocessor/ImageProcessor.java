/*
 * @author Josué Arrieta Salas
 * @version v0.1.1-alpha
 */

package logic.imageprocessor;


// TODO: Auto-generated Javadoc
/**
 * The Class ImageProcessor. Represents basic functions of a normal image processor class.
 */
public abstract class ImageProcessor {

  /**
   * Instantiates a new image processor.
   */
  public ImageProcessor() { }
  
  /**
   * Mask. Creates a binary image, detecting pixels between alpha min and alpha max.
   *
   * @param image the image. Must be in RGB format.
   * @param alphaMin the alpha min. Representes lowest values of pixels.
   * @param alphaMax the alpha max. Represents max values of pixels.
   * @return the image with the pixels detected.
   */
  public abstract Object mask(Object image, Object alphaMin, Object alphaMax);
  
  /**
   * Rgb 2 hsv. Transforms and RGB image to HSV image.
   *
   * @param image the image. Must be in RGB.
   * @return the image in hsv format.
   */
  public abstract Object rgb2hsv(Object image);
  
  /**
   * Dilate. Dilates image.
   *
   * @param image, can be in any format.
   * @return the image dilated, in the format which entered.
   */
  public abstract Object dilate(Object image);
  
  /**
   * Complement. Complements an image.
   *
   * @param image the image in binary format.
   * @return the image in binary format inverted.
   */
  public abstract Object complement(Object image);
  
  /**
   * Or. Bitwise or between images.
   * It is expected that both images are of the same size and in binary format.
   * @param image1 in binary format.
   * @param image2 in binary format.
   * @return the result image of image1 | image2. It maintains its format.
   */
  public abstract Object or(Object image1, Object image2);
  
  /**
   * Flood fill. Flood fills an image.
   *
   * @param image the image in any format. Used for RGB.
   * @param point the point where to start flood filing.
   * @param color the color which want to be painted
   * @return the image floodfilled. Maintains format.
   */
  public abstract Object floodFill(Object image, Object point,  Object color);
  
  /**
   * Draw rectangle. Draw a rectangle in an image.
   *
   * @param image the image. Can be in any format.
   * @param point1 the point 1 of the rectangle.
   * @param point2 the point 2 of the rectangle.
   * @param color the color of the rectangle.
   * @return the original image with the rectangle painted. Maintains format.
   */
  public abstract Object drawRectangle(Object image, Object point1, Object point2, Object color);
  
  /**
   * Fill contours. Fill contours of a given image.
   *
   * @param image with the contours to be filled. Can be in any format.
   * @param contours is a list with the contours to be filled.
   * @param color the color of the contours to be filled.
   * @return the image with the contours filled. Maintains format.
   */
  public abstract Object fillContours(Object image, Object contours, Object color);
  
  /**
   * Find contours. Find all contours of a given image.
   *
   * @param image with the contours to be detected. Can be in any format.
   * @return a list with the contours.
   */
  public abstract Object findContours(Object image);
  
  /**
   * Compare image. Tells if an image is the same as another image.
   *
   * @param image1 the image 1. Must be in one channeled format.
   * @param image2 the image 2. Must be in one channeled format.
   * @return the boolean telling if are the same image.
   */
  public abstract Boolean compareImage(Object image1, Object image2);
  
  /**
   * Hh. Gets the H of an HSV image.
   *
   * @param image the image in hsv format.
   * @return the h channel.
   */
  public abstract Object hh(Object image);
  
  /**
   * Paint players. Paints blobs corresponding to players in an image of a soccer match.
   *
   * @param originalImage the original image. Represents the soccer match. Must be in HSV.
   * @param field the field. Binary image of the field.
   * @param players the players. Binary image of the players.
   * @return the final image in RGB format, with the players painted.
   */
  public abstract Object paintPlayers(Object originalImage, Object field, Object players);
  
  /**
   * Gets the players, between a soccer field and blobs corresponding to players.
   *
   * @param field the field. Binary image of the field.
   * @param players the players. Binary image of the players
   * @return the final players
   */
  public abstract Object getPlayers(Object field, Object players);
  
  /**
   * Dice Metric. Gets dice metric, in this case between a ground truth and a image.
   *
   * @param groundTruth the ground truth, of the expected image blobs.
   * @param field the field. Binary image of the field.
   * @param players the players. Binary image of the players.
   * @return the value obtained, expected to be between 0.0 and 1.
   */
  public abstract double dice(Object groundTruth, Object field, Object players);
}
