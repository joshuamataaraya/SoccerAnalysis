package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import DataTransferObject.DTOVideoAnalisis;

public class VideoAnalisisControllerTest {

	@Test
	public void videoAnalisisAlgorithmTest() {
		DTOVideoAnalisis dto = new DTOVideoAnalisis();
		dto.setOutVideoPath("testData/");
		dto.setVideoPath("testData/video2.mp4");
		
		VideoAnalisisController va = new VideoAnalisisController();
		va.algoritm(dto);
	}

}
