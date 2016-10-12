/*
 * @author Adrian Lopez Quesada
 * @version v0.1.1-alpha
 */

package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Observable;
import java.util.Observer;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import DataTransferObject.DTOVideoAnalisis;
import controller.Controller;
import controller.VideoAnalisisController;

// TODO: Auto-generated Javadoc
/**
 * The Class WebSocketServlet.
 * The purpose is to communicate the Java Logic for processing a video with an html UI.
 * It uses a Jetty's JSR-356 WebSocket to establish a connection with one User.
 * For every new user, a new instance of the class is generated. 
 */
@ServerEndpoint("/vidUpload")
public class WebSocketServlet implements Observer {

  /** The uploaded file. */
  private static File uploadedFile = null;
  
  /** The file stream. */
  
  private FileOutputStream fileStream = null;
  
  /** The file name of the uploadFile. */
  private String fileName = null;
  
  /** The path of the uploadFile. */
  private String path = "src/main/webapp/videoFiles/";

  /** The session of the client. */
  private Session session = null;
  
  /**
   * Open.
   * Method executed when a session is opened. 
   * @param session the current user's session
   * @param conf the configuration sent by the client
   */
  @OnOpen
  public void openSession(Session session, EndpointConfig conf) {
    this.session = session;
    System.out.println("Socket opened");
  }

  /**
   * Process upload.
   * Method executed when a client sends an buffer of bytes. 
   * It writes the chunks sent by the client. 
   * @param msg the buffer of bytes sent by the client
   * @param last the parameter needed for the standard defined by the Jetty JSR-356
   * @param session the clients current session
   */
  @OnMessage(maxMessageSize = 2048 * 2048)
  public void processUpload(ByteBuffer msg, boolean last, Session session) {
    System.out.println("Binary Data");      

    while (msg.hasRemaining()) {         
      try {
        fileStream.write(msg.get());
      } catch (IOException exc) {               
        exc.printStackTrace();
      }
    }

  }

  /**
   * On message.
   * Method executed when a message comes from the client
   * @param session the clients current session
   * @param msg the message sent by the client
   */
  @OnMessage(maxMessageSize = 2048 * 2048)
  public void onMessage(Session session, String msg) {
    System.out.println("here!");
    if (!msg.equals("end") && !msg.equals("groundTruth")) {
      fileName = msg.substring(msg.indexOf(':') + 1);
      uploadedFile = new File(path + fileName);
      try {
        fileStream = new FileOutputStream(uploadedFile);
      } catch (FileNotFoundException exc) {     
        exc.printStackTrace();
      }
    } else {
      try {
        fileStream.flush();
        fileStream.close();
        System.out.println("End of transfer");
      } catch (IOException exc) {       
        exc.printStackTrace();
      }
      if (msg.equals("end")) { 
        processingActivity(session);
      } else {
        processingGroundTruth(session); //Momentaneo mientra se programa el groundtruth
      }
    }
  }

  /**
   * Close.
   * Method executed when a client's session is closed
   * @param session the client's closed session
   * @param reason the reason why it closed, normally just disconnected.
   */
  @OnClose
  public void close(Session session, CloseReason reason) {
    System.out.println("socket closed: " + reason.getReasonPhrase());
  }

  /**
   * Error.
   * A type of catch within a socket's connection
   * @param session the client's session with 
   * @param throwable the java's throwable error
   */
  @OnError
  public void error(Session session, Throwable throwable) {
    throwable.printStackTrace();

  }
  
  /**
   * Process video.
   * Private method to process the video, it executes a thread
   * @param ses the ses
   */
  private DTOVideoAnalisis processVideo(Session ses) {
    DTOVideoAnalisis vid = new DTOVideoAnalisis();
    vid.setVideoPath(path + fileName);
    vid.setOutVideoPath(path);
    Controller videoProcessor = new VideoAnalisisController();
    videoProcessor.addObserver(this);
    return (DTOVideoAnalisis)videoProcessor.algoritm(vid);
  }
    
  /**
   * Process download.
   * method that sends the new url for the client to download
   * @param ses the current session of the client
   */
  private void processDownload(Session ses, DTOVideoAnalisis path) {
    String pathStr = path.getOutVideoPath();
    pathStr = pathStr.substring(16);
    try {
      ses.getBasicRemote().sendText(pathStr);
    } catch (IOException exc) {
      // TODO Auto-generated catch block
      exc.printStackTrace();
    }
  }
    
  /**
   * Processing activity.
   * Method to encapsulate all the process activities.
   * @param session the current client's session
   */
  private void processingActivity(Session session) {
    DTOVideoAnalisis path = processVideo(session);
    processDownload(session, path);
  }
  
  /**
   * Processing ground truth.
   * Method to process the groundtruth when its called.
   * Not Working currently
   * @param ses the current client's session
   */
  private void processingGroundTruth(Session ses) {
    try {
      ses.getBasicRemote().sendText("groundTruth");
    } catch (IOException exc) {
      // TODO Auto-generated catch block
      exc.printStackTrace();
    }
  }
  
  @Override
  public void update(Observable o, Object arg) {
    try {
      session.getBasicRemote().sendText("transfer");
    } catch (IOException exc) {
      // TODO Auto-generated catch block
      exc.printStackTrace();
    }
  }
}
