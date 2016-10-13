package controller;

import java.util.ArrayList;
import java.util.stream.DoubleStream;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import DataTransferObject.DTOGroundTruth;
import DataTransferObject.DTOVideoAnalisis;
import logic.imageprocessor.Detector;
import logic.imageprocessor.FieldDetector;
import logic.imageprocessor.ImageProcessor;
import logic.imageprocessor.OpencvImageProcessor;
import logic.imageprocessor.PlayerDetector;
import logic.videoprocessor.OpenCVVideoProcessor;

public class groundTruthController extends Controller{

	private ImageProcessor processor;
    private double[] diceValues;
	
	@Override
	public Object algoritm(Object dto) throws Exception {
		DTOGroundTruth input = (DTOGroundTruth) dto;
		OpenCVVideoProcessor vp = new OpenCVVideoProcessor(
				input.getVideoPath(),null);
		OpenCVVideoProcessor vpGroundTruth = new OpenCVVideoProcessor(
				input.getGrundVideoPath(),null);
		
		int frames = vp.getFrameCount();
		int framesGoundTruth = vpGroundTruth.getFrameCount();
		if (frames!= framesGoundTruth){
			throw new Exception("The ground truth video doesn't "
					+ "belong to the original video");
		}
		diceValues = new double[frames];
		while(frames>0){
			Mat frame = (Mat) vp.readFrame();
			Mat frameGroundTruth = (Mat) vpGroundTruth.readFrame();
			if (!frame.empty()){
				diceValues[frames-1] = getDiceValue(frame, frameGroundTruth);
			}
			frames--;
		}
		input.setDiceValue(DoubleStream.of(diceValues).sum()/diceValues.length);		
		return input;
	}
	private double getDiceValue(Object mat, Object matGroundTruth){
		Detector fieldDetector = new FieldDetector((Mat)mat);
	    Detector playerDetector = new PlayerDetector((Mat)mat);
		Double dice = processor.dice(matGroundTruth, fieldDetector.detect(), playerDetector.detect());
		return dice;
	}

}
