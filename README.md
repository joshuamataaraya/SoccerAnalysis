# SoccerAnalysis
This is an analysis of soccer videos

## Table of Contents
1. [Requirements](#requeriments)
2. [OpenCV Installation](#opencv-installation)
3. [Web Server Installation](#web-server-installation)

### Requeriments

- JDK 8.0 or above
- Tomcat 8.0 (Included inside Git)
- Eclipse Neon EE
- OpenCV-3.1

### OpenCv Installation
1. Download the library opencv-3.1 from the followin link: https://sourceforge.net/projects/opencvlibrary/files/opencv-win/3.1.0/opencv-3.1.0.exe/download
2. Extract on the root of your HardDrive when you're installing it.
3. Follow the instructions on the following link: http://docs.opencv.org/2.4/doc/tutorials/introduction/java_eclipse/java_eclipse.html#java-eclipse
4. While you are installing opencv in your Eclipse IDE, it´s important to name it "opencv-3.1".
5. Copy the file opencv\build\bin\opencv_ffmpeg310_64.dll into opencv\build\java\x64 


### Web Server Installation

This is used to deploy the UI interface, when it's deployed it can be accesed through "localhost:8080/WebInterface/".
We decided to use TomCat 8.0, and we use some PATH variables from TomCat in the API package, so it's of great importance
to use the version included inside the Git. 

1. Right Click on the project -> Run as -> Run on Server

	![Alt text](ImagesReadme/Tutorial1.png?raw=true "Tutorial1")
2. Select Apache Folder

	![Alt text](ImagesReadme/Tutorial2.png?raw=true "Tutorial2")

3. Select TomCat 8.0 Server

	![Alt text](ImagesReadme/Tutorial3.png?raw=true "Tutorial3")

4. In the Tomcat Installation Server option, browse and select the following path: ...\SoccerAnalysis\server\apache-tomcat-8.0.36 

	**Note** the dots refer to the path where the project is located.

	![Alt text](ImagesReadme/Tutorial4.png?raw=true "Tutorial4")

	TomCat should execute and you should be ready to access the web page.
