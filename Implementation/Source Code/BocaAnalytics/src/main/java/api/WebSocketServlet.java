/*
 * @author Adrian Lopez Quesada
 * @version v0.1.1-alpha
 */

package api;

import controller.Controller;
import controller.GroundTruthController;
import controller.VideoAnalisisController;
import datatransferobject.DtoGroundTruth;
import datatransferobject.DtoVideoAnalisis;

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
  private String path = "src/main/webapp/VideoFiles/";

  /** The session of the client. */
  private Session session = null;
  
  /** Boolean to specify which file of the Groundtruth has entered. */
  private boolean firstFilein = false;
  
  /** String of the groundtruth file name */
  private String groundFileName;
  
  
  /**
   * Open.
   * Method executed when a session is opened. 
   * @param session the current user's session
   * @param conf the configuration sent by the client
   */
  @OnOpen
  public void openSession(Session session, EndpointConfig conf) {
    this.session = session;
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
    if (!msg.equals("end") && !msg.equals("groundTruth")) {
      fileName = msg.substring(msg.indexOf(':') + 1);
      uploadedFile = new File(path + fileName);
      try {
        fileStream = new FileOutputStream(uploadedFile);
      } catch (FileNotFoundException exc) {     
        exc.printStackTrace();
      }
    } else {
      System.out.println(msg);
      try {
        fileStream.flush();
        fileStream.close();
      } catch (IOException exc) {       
        exc.printStackTrace();
      }
      if (msg.equals("end")) { 
        processingActivity(session);
      } else {
        if (!firstFilein) {
          firstFilein = true;
          groundFileName = path + fileName; 
          try {
            session.getBasicRemote().sendText("groundTruth");
          } catch (IOException exc) {
            exc.printStackTrace();
          }
        } else {
          processingGroundTruth(session);
        }
      }
    }
  }

  /**
   * Close.
   * Method executed when a client's session is closed, needed for standard
   * @param session the client's closed session
   * @param reason the reason why it closed, normally just disconnected.
   */
  @OnClose
  public void close(Session session, CloseReason reason) {
    firstFilein = false;
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
   * @param ses the current session of the client 
   */
  private DtoVideoAnalisis processVideo(Session ses) {
    DtoVideoAnalisis vid = new DtoVideoAnalisis();
    vid.setVideoPath(path + fileName);
    vid.setOutVideoPath(path);
    Controller videoProcessor = new VideoAnalisisController();
    videoProcessor.addObserver(this);
    return (DtoVideoAnalisis)videoProcessor.algoritm(vid);
  }
    
  /**
   * Process download.
   * method that sends the new url for the client to download
   * @param ses the current session of the client
   */
  private void processDownload(Session ses, DtoVideoAnalisis path) {
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
    DtoVideoAnalisis path = processVideo(session);
    processDownload(session, path);
  }
  
  /**
   * Processing ground truth.
   * Method to process the groundtruth when its called.
   * @param ses the current client's session
   */
  private void processingGroundTruth(Session ses) {
    DtoGroundTruth vid = new DtoGroundTruth();
    vid.setVideoPath(path + fileName);
    vid.setGrundVideoPath(groundFileName);
    Controller truthProcessor = new GroundTruthController();
    truthProcessor.addObserver(this);
    DtoGroundTruth dto = (DtoGroundTruth) truthProcessor.algoritm(vid);
    try {
      ses.getBasicRemote().sendText(dto.getDiceValue() + "");
    } catch (IOException exc) {
      // TODO Auto-generated catch block
      exc.printStackTrace();
    }
  }
  
  @Override
  //Este metodo no puede cumplir el estandar de checkstyle 
  //debido a que la interface de Java Observer establece ese formato
  
  /**
   * Update on notify.
   * Method to send progress of the video beign processed.
   * @param o the object beign observed
   * @param arg the array sent by the observable with parameters
   */  
  public void update(Observable obse, Object arg) {
    try {
      session.getBasicRemote().sendText("transfer");
    } catch (IOException exc) {
      // TODO Auto-generated catch block
      exc.printStackTrace();
    }
  }
}
