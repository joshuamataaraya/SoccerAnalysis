package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import DataTransferObject.DTOGroundTruth;
import DataTransferObject.DTOVideoAnalisis;

public class groundTruthControllerTest {

	@Test
	public void testGroundTruth() throws Exception {
		DTOGroundTruth dto = new DTOGroundTruth();
		dto.setVideoPath("testData/1476324147909.mp4");
		dto.setGrundVideoPath("testData/1476324147909.mp4");
		
		GroundTruthController gt = new GroundTruthController();
		gt.algoritm(dto);
	}

}
