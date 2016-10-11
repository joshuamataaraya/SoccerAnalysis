package logic.videoprocessor;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.*;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

public class Writter {
	public static void main(final String[] args){
		
		 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		 System.loadLibrary("opencv_ffmpeg310_64");
			
		 

		  String url=null;
		  final String outputFile="testData/writer-java.mp4";
		  if (args.length == 0) {
		    url="testData/video.mp4";
		  }
		 else {
		    url=args[0];
		  }
		  Path filePath = Paths.get("file.txt");
		  try {
			Scanner scanner = new Scanner(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  System.out.println(String.format("OpenCV %s",Core.VERSION));
		  System.out.println(String.format("Input file: %s",url));
		  System.out.println(String.format("Output file: %s",outputFile));
		  System.loadLibrary("opencv_ffmpeg310_64");
		  VideoCapture videoCapture=new VideoCapture(url);
		  final Size frameSize=new Size((int)videoCapture.get(Videoio.CAP_PROP_FRAME_WIDTH),(int)videoCapture.get(Videoio.CAP_PROP_FRAME_HEIGHT));
		  System.out.println(String.format("Resolution: %s",frameSize));
		  VideoWriter videoWriter=new VideoWriter(outputFile,VideoWriter.fourcc('X','V','I','D'),videoCapture.get(Videoio.CAP_PROP_FPS),frameSize,true);
		  final Mat mat=new Mat();
		  int frames=0;
		  final long startTime=System.currentTimeMillis();
		  while (videoCapture.read(mat)) {
		    videoWriter.write(mat);
		    frames++;
		  }
		  final long estimatedTime=System.currentTimeMillis() - startTime;
		  System.out.println(String.format("%d frames",frames));
		  System.out.println(String.format("Elapsed time: %4.2f seconds",(double)estimatedTime / 1000));
		  videoCapture.release();
		  videoWriter.release();
		  mat.release();
		}
}
