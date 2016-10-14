/*
 * @author Joshua Mata Araya
 * @version v0.1.1-alpha
 */

package logic.videoprocessor;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;


/**
 * The Class OpenCVVideoProcessor.
 */
public class OpenCvVideoProcessor extends VideoProcessor {

  /** The video. */
  private VideoCapture video;

  /** The size. */
  private Size size;
  
  /** The current frame. */
  private Mat currentFrame;

  /** The video writer. */
  private VideoWriter videoWriter;
  
  /** The fps. */
  private double fps;

  /**
   * Instantiates a new open CV video processor.
   *
   * @param filePath the file to be analyzed.
   * @param outFilePath the out file path where the result video has to be.
   */
  public OpenCvVideoProcessor(String filePath, String outFilePath) {
    super(filePath, outFilePath);
    setVideoSize();
    setVideoFps();
    initializeVideoWriter();
    initializeCurrentFrame();
  }
  
  /**
   * Instantiates a new open CV video processor.
   *
   * @param filePath the file path to be analyzed.
   */
  public OpenCvVideoProcessor(String filePath) {
    super(filePath);
    setVideoSize();
    setVideoFps();
    initializeCurrentFrame();
  }
  
  /**
   * Sets the video size property from the opened video.
   * 
   */
  private void setVideoSize() {
    size = new Size((int)video.get(Videoio.CAP_PROP_FRAME_WIDTH),
        (int)video.get(Videoio.CAP_PROP_FRAME_HEIGHT));
  }
  
  /**
   * Sets the video fps property from the opened video.
   * Uses the get method from the VideoWriter class
   * http://docs.opencv.org/java/3.1.0/org/opencv/videoio/VideoWriter.html
   
   */
  private void setVideoFps() {
    fps = video.get(Videoio.CAP_PROP_FPS);
  }

  /**
   * Initialize video writer attribute.
   * Uses the VideoWriter class
   * http://docs.opencv.org/java/3.1.0/org/opencv/videoio/VideoWriter.html
   */
  private void initializeVideoWriter() {
    videoWriter = new VideoWriter(this.outFilePath,
        VideoWriter.fourcc('H','2','6','4'),
        fps,this.size,true);
  }

  /**
   * Initialize current frame when analysing the video.
   */
  private void initializeCurrentFrame() {
    currentFrame = new Mat();
  }

  /* (non-Javadoc)
   * @see logic.videoprocessor.VideoProcessor#openVideo()
   */
  @Override
  protected void openVideo() {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    System.loadLibrary("opencv_ffmpeg310_64");
    System.loadLibrary("openh264-1.4.0-win64msvc");
    video = new VideoCapture(filePath);
    this.outFilePath = this.outFilePath + System.currentTimeMillis() + ".mp4";  
  } 


  /* (non-Javadoc)
   * @see logic.videoprocessor.VideoProcessor#setFrameCount()
   */
  @Override
  protected void setFrameCount() {
    this.framesCount = (int)video.get(Videoio.CAP_PROP_FRAME_COUNT);
  }


  /* (non-Javadoc)
   * @see logic.videoprocessor.VideoProcessor#saveVideo()
   */
  @Override
  public String saveVideo() {
    this.video.release();
    videoWriter.release();
    this.currentFrame.release();
    return this.outFilePath;
  }

  /* (non-Javadoc)
   * @see logic.videoprocessor.VideoProcessor#readFrame()
   */
  @Override
  public Object readFrame() {
    this.video.read(this.currentFrame);
    return this.currentFrame;
  }
  
  /* (non-Javadoc)
   * @see logic.videoprocessor.VideoProcessor#writeFrame(java.lang.Object)
   */
  @Override
  public void writeFrame(Object frame) {
    this.videoWriter.write((Mat) frame);
  }
}
