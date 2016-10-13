/**
 * 
 */
package logic.videoprocessor;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.opencv.core.Core;
import org.opencv.*;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;


public class OpenCVVideoProcessor extends VideoProcessor {
	
	private VideoCapture video;
	private Size size;
	private Mat currentFrame;
	private VideoWriter videoWriter;
	private double fps;
	public OpenCVVideoProcessor(String filePath, String outFilePath) {
		super(filePath, outFilePath);
		setVideoSize();
		setVideoFps();
		initializeVideoWriter();
		initializeCurrentFrame();
	}
	public OpenCVVideoProcessor(String filePath) {
		super(filePath);
		setVideoSize();
		setVideoFps();
		initializeCurrentFrame();
	}
	private void setVideoSize(){
		size = new Size((int)video.get(Videoio.CAP_PROP_FRAME_WIDTH),(int)video.get(Videoio.CAP_PROP_FRAME_HEIGHT));
	}
	private void setVideoFps(){
		fps = video.get(Videoio.CAP_PROP_FPS);
	}
	private void initializeVideoWriter(){
		videoWriter=new VideoWriter(this.outFilePath,
				VideoWriter.fourcc('H','2','6','4'),
				fps,this.size,true);
	}
	private void initializeCurrentFrame(){
		currentFrame = new Mat();
	}

	@Override
	protected void openVideo() {
		String libPath = System.getProperty("java.library.path");
		System.out.println(libPath);
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.loadLibrary("opencv_ffmpeg310_64");
		System.loadLibrary("openh264-1.4.0-win64msvc");
		video = new VideoCapture(filePath);
		this.outFilePath= this.outFilePath+System.currentTimeMillis()+".mp4";
		
	}


	@Override
	protected void setFrameCount() {
		this.framesCount = (int)video.get(Videoio.CAP_PROP_FRAME_COUNT);
	}


	@Override
	public String saveVideo() {
		this.video.release();
		videoWriter.release();
		this.currentFrame.release();
		return this.outFilePath;
	}
	@Override
	public Object readFrame() {
		this.video.read(this.currentFrame);
		return this.currentFrame;
	}
	@Override
	public void writeFrame(Object frame) {
		this.videoWriter.write((Mat) frame);
	}
}
