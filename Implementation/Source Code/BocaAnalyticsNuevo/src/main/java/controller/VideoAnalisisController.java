package controller;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import DataTransferObject.DTOVideoAnalisis;
import logic.imageprocessor.Detector;
import logic.imageprocessor.FieldDetector;
import logic.imageprocessor.ImageProcessor;
import logic.imageprocessor.OpencvImageProcessor;
import logic.imageprocessor.PlayerDetector;
import logic.videoprocessor.OpenCVVideoProcessor;

public class VideoAnalisisController extends Controller {

	@Override
	public Object algoritm(Object dto) {
		DTOVideoAnalisis input = (DTOVideoAnalisis) dto;
		OpenCVVideoProcessor vp = new OpenCVVideoProcessor(
				input.getVideoPath(),
				input.getOutVideoPath());
		int frames = vp.getFrameCount();
		while(frames>0){
			Mat frame = (Mat) vp.readFrame();
			if (!frame.empty()){
				frame = detectPlayers(frame);
				vp.writeFrame(frame);
			}
			frames--;
		}
		input.setOutVideoPath(vp.saveVideo());
		return input;
	}
	private Mat detectPlayers(Mat image){
		ImageProcessor processor = new OpencvImageProcessor();
	    Detector fieldDetector = new FieldDetector(image);
	    Detector playerDetector = new PlayerDetector(image);
	    return (Mat) processor.paintPlayers(image, fieldDetector.detect(), playerDetector.detect());
	}

}
