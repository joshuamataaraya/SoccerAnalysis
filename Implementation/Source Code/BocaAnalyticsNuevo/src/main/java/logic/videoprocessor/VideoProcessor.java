package logic.videoprocessor;

import java.util.ArrayList;

public abstract class VideoProcessor {
	protected String filePath;
	protected String outFilePath;
	protected int framesCount;
	
	public VideoProcessor(String filePath, String outFilePath){
		this.filePath = filePath;
		this.outFilePath = outFilePath;
		openVideo();
		setFrameCount();
	}
	protected abstract void openVideo();
	protected abstract void setFrameCount();
	public abstract Object readFrame();
	public abstract void writeFrame(Object frame);
	public abstract String saveVideo();
	public int getFrameCount(){
		return framesCount;
	}
	
}
