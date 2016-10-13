package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import DataTransferObject.DTOGroundTruth;
import DataTransferObject.DTOVideoAnalisis;

public class groundTruthControllerTest {

	@Test
	public void testGroundTruth() throws Exception {
		DTOGroundTruth dto = new DTOGroundTruth();
		dto.setVideoPath("testData/inputGroundTruth.avi");
		dto.setGrundVideoPath("testData/groundTruth.mpeg");
		
		GroundTruthController gt = new GroundTruthController();
		dto = (DTOGroundTruth)gt.algoritm(dto);
		double dice = dto.getDiceValue();
		System.out.println("Dice: " + dice);
	}

}
