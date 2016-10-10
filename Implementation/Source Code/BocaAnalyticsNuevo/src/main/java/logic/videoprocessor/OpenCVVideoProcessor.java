/**
 * 
 */
package logic.videoprocessor;

import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;

public class OpenCVVideoProcessor extends VideoProcessor {

	public OpenCVVideoProcessor(String filePath) {
		super(filePath);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);	
	}
	

	@Override
	public ArrayList<Object> getVideoFrames() {
		ArrayList frames = new ArrayList<>();
		VideoCapture videoCapture = new VideoCapture(filePath);
		return null;
	}
	

	@Override
	public int countFrames() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String createVideo(ArrayList<Object> frames) {
		// TODO Auto-generated method stub
		return null;
	}

}
