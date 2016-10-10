/**
 * 
 */
package logic.videoprocessor;

import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

public class OpenCVVideoProcessor extends VideoProcessor {
	
	private int countFrames;
	private VideoCapture video;
	public OpenCVVideoProcessor(String filePath) {
		super(filePath);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public ArrayList<Object> getVideoFrames() {
		ArrayList<Object> frames = new ArrayList<>();
		openVideo();
		Mat currentMat =  new Mat();
		while (video.read(currentMat)){
			Mat newMat = new Mat();
			newMat = currentMat;
			frames.add(newMat);
			countFrames++;
		}
		return frames;
	}

	@Override
	public int countFrames() {
		if (countFrames==0){
			Mat mat = new Mat();
			openVideo();
			while (video.read(mat)){
				countFrames++;
			}			
		}
		return countFrames;
	}

	@Override
	public String createVideo(ArrayList<Object> frames) {
		// TODO Auto-generated method stub
		return null;
	}
	private void openVideo (){
		System.loadLibrary("opencv_ffmpeg310_64");
		video = new VideoCapture(filePath);
	}

}
