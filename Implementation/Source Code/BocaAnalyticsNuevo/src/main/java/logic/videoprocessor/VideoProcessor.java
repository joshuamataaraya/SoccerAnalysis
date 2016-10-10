package logic.videoprocessor;

import java.util.ArrayList;

public abstract class VideoProcessor {
	protected String filePath;
	public VideoProcessor(String filePath){
		this.filePath = filePath;
	}
	public abstract ArrayList<Object> getVideoFrames();
	public abstract int countFrames();
	public abstract String createVideo(ArrayList<Object> frames);
}
