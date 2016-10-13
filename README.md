# SoccerAnalysis
This is an analysis of soccer videos

## Table of Contents
1. [Requirements](#requeriments)
2. [OpenCV Installation](#opencv-installation)
3. [DLLs Installation](#dlls-installation)
3. [Web Server Installation](#web-server-installation)

### Requeriments

- JDK 8.1 or above
- Eclipse Neon Jee
- OpenCV-3.1
- DLLs

### OpenCv Installation
1. Download the library opencv-3.1 from the followin link: https://sourceforge.net/projects/opencvlibrary/files/opencv-win/3.1.0/opencv-3.1.0.exe/download
2. Extract on the root of your HardDrive when you're installing it.
3. Follow the instructions on the following link: http://docs.opencv.org/2.4/doc/tutorials/introduction/java_eclipse/java_eclipse.html#java-eclipse
4. While you are installing opencv in your Eclipse IDE, it´s important to name it "opencv-3.1".
5. Copy the file opencv\build\bin\opencv_ffmpeg310_64.dll into opencv\build\java\x64 

### DLLs Installation

DLLs requiered for running the tests or/and the server full functionality.
Copy the DLLs in the folder Dependencies and paste them on: C:\opencv\build\java\x64
The project wont work unless you do this last step. 


### Web Server Installation

This is used to deploy the UI interface, when it's deployed it can be accesed through "localhost:8080".
We decided to use Jetty's and Maven integrated server, so here is the basic configuration 

1. Right Click on the project -> Run as -> Run on Server

	![Alt text](ImagesReadme/Tutorial1.png?raw=true "Tutorial1")

2. Select Maven Build - New 

	![Alt text](ImagesReadme/Tutorial2.png?raw=true "Tutorial2")

3. Fill in the next three boxes highlited by the red boxes.

	![Alt text](ImagesReadme/Tutorial3.png?raw=true "Tutorial3")

4. In the JRE Tab make sure its selected the workspace defualt JRE and it sshould be a JDK one as seen on the image, not a JRE

		![Alt text](ImagesReadme/Tutorial4.png?raw=true "Tutorial4")

	**Note** if your default is on JRE, close the run configurations, go to Window -> Preferences -> Java -> Installed JRE's and add the jdk folder and/or check the jdk box and apply. 

5. In the same tab, add the next VM arguments: -Djava.library.path=C:\opencv\build\java\x64 hit apply and run.

			![Alt text](ImagesReadme/Tutorial5.png?raw=true "Tutorial5")

***Note: since the WebSocket simulates the upload of your internet speed, its highly recommended to use files with low sizes. We recommend two files located in our drive: https://drive.google.com/drive/folders/0B1b_MSFDPh5ZRXcxMF9zUmhvLXM?usp=sharing one for running a JUnit test, which uses a big size file with more resolution (more on dependencies) and the second is a small size file .mp4 which generates fairly good results and can be used for the upload on the WebBrowser.