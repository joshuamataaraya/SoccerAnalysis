package logic.imageprocessor;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

class PlayerDetector extends OpencvDetector {

  public PlayerDetector(Mat image) {
     super(image);
  }

  @Override
  public Object detect() {
    //detects the players
    Mat imageMat = (Mat) image;
    Mat rgb = (Mat) imageMat.clone();
    Mat hsv = (Mat) processor.rgb2hsv(rgb);
    Imgcodecs.imwrite("testData/2-hsv.png", hsv);
    Mat std = stdfilt(hsv);
    Imgcodecs.imwrite("testData/prueba.png", std);
    return null;
  }
  
  private Mat normalizeImage(Mat image) {
    return null;
  }
  
  private Mat stdfilt(Mat image) {
    Mat h = (Mat) processor.h(image);
    Core.normalize(h, h, 0, 255, Core.NORM_MINMAX);
    Mat out;
    out = h.clone();
    // find out the mean image
    Imgproc.blur(h, out, new Size(5,5));
    // substract mean image
    Core.absdiff(out,h,out);
    // square to get std matrix
    Core.pow(out,2.0,out);
    // square to get mean matrix
    Core.pow(out,2.0,out);
    Core.normalize(out, out, 0, 255, Core.NORM_MINMAX);
    Imgproc.blur(h, h, new Size(5,5));
    MatOfDouble mean = new MatOfDouble();
    MatOfDouble stddev = new MatOfDouble();
    Core.meanStdDev(h, mean, stddev);

    
    return h;
  }
  
  private Mat truncate(Mat image) {
    return null;
  }
  
  private Mat graythresh(Mat image) {
    return null;
  }
  
  private Mat im2bw(Mat image) {
    return null;
  }
  
  /*public static Mat detectPlayers(Mat pImage){
  //detects posible regions that could be players
  Mat rgb = pImage.clone();
  Mat hsv = rgb2hsv(rgb);
  Mat normalizedImage = new Mat();
  MatOfInt histSize = new MatOfInt(256);
  Mat hist = new Mat();
  
  /*List<Mat> channel = new ArrayList<>();
  Core.split(hsv, channel);
  
  final MatOfFloat histRange = new MatOfFloat(0f, 180f);
  List<Mat> temp = new ArrayList<>();
  temp.add(channel.get(0));
  Imgproc.calcHist(temp, new MatOfInt(0), new Mat(), hist, histSize, histRange, false);
  
  int hist_w = 1434;
  int hist_h = 617;
  long bin_w = Math.round((double) hist_w / 256);
  //bin_w = Math.round((double) (hist_w / 256));

  Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC1);
  Core.normalize(hist, hist, 1, histImage.rows(), Core.NORM_MINMAX);
  
  for (int i = 1; i < 256; i++) {
      Point p1 = new Point(bin_w * (i - 1), hist_h - Math.round(hist.get(i - 1, 0)[0]));
      Point p2 = new Point(bin_w * (i), hist_h - Math.round(hist.get(i, 0)[0]));
      Imgproc.line(histImage, p1, p2, new Scalar(255, 0, 0), 2, 8, 0);
  }

  
  Imgcodecs.imwrite("prueba.png", histImage);*/
  /*Imgproc.cvtColor(rgb, rgb, Imgproc.COLOR_RGB2HSV);
  List<Mat> channel = new ArrayList<>();
  Core.split(rgb, channel);
  
  
  /*Mat h = channel.get(0);
  
  h.convertTo(h, CvType.CV_32F,255);
  //Core.normalize(h, h, 0, 1, Core.NORM_MINMAX,CvType.CV_32F);

  Mat mu = new Mat();     
  Size ksize = new Size(5,5);
  Imgproc.blur(h, mu,ksize);

  Mat mu2 = new Mat();
  Imgproc.blur(h.mul(h), mu2, ksize);

  Mat sigma = new Mat();
  Mat x = new Mat();
  Mat y = new Mat();
  Core.multiply(mu, mu, x);
  Core.subtract(mu2, x, y);
  //Core.sqrt(y, sigma);
  
  Mat gry = new Mat();
  Core.normalize(y, gry, 0.0, 1, Core.NORM_MINMAX);
  
  gry.convertTo(gry, CvType.CV_8U, 255);*/
  
  
  
  
  
  /*Mat src = channel.get(0);//get the H of HSV
  Core.normalize(src, src, 0, 255, Core.NORM_MINMAX);
  Mat out;
  out = src.clone();
  // find out the mean image
  Imgproc.blur(src, out, new Size(5,5));
  // substract mean image
  Core.absdiff(out,src,out);
  // square to get std matrix
  Core.pow(out,2.0,out);
  // square to get mean matrix
  Core.pow(out,2.0,out);
  Core.normalize(out, out, 0, 255, Core.NORM_MINMAX);
  
  out = dilate(out);
  
  
  double umbral = Imgproc.threshold(out, out, 0, 255, Imgproc.THRESH_BINARY | Imgproc.THRESH_OTSU);
  //Imgproc.threshold(out, out, umbral, 255, Imgproc.THRESH_BINARY);
  
  //System.out.println(umbral);
  
  Mat finale = imfill(out, new Point(0,333));
  Imgcodecs.imwrite("prueba.png", out);
  finale = bwareaopen(finale);
  Imgcodecs.imwrite("a ver si acaso.png", finale);
 
  
  
  return null;
}*/

}
