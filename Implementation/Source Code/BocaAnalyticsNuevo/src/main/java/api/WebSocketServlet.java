package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/vidUpload")
public class WebSocketServlet {

  static File uploadedFile = null;
  static FileOutputStream fos = null;
  private String fileName = null;
  static final String path = "src/main/webapp/videoFiles/";

  @OnOpen
  public void open(Session session, EndpointConfig conf) {
    System.out.println("Socket opened");
  }

  @OnMessage(maxMessageSize = 2048 * 2048)
  public void processUpload(ByteBuffer msg, boolean last, Session session) {
    System.out.println("Binary Data");      

    while (msg.hasRemaining()) {         
      try {
        fos.write(msg.get());
      } catch (IOException exc) {               
        exc.printStackTrace();
      }
    }

  }

  @OnMessage(maxMessageSize = 2048 * 2048)
  public void onMessage(Session session, String msg) {
    System.out.println("here!");
    if (!msg.equals("end") && !msg.equals("groundTruth")) {
      fileName = msg.substring(msg.indexOf(':') + 1);
      uploadedFile = new File(path + fileName);
      try {
        fos = new FileOutputStream(uploadedFile);
      } catch (FileNotFoundException exc) {     
        exc.printStackTrace();
      }
    } else {
      try {
        fos.flush();
        fos.close();
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

  @OnClose
  public void close(Session session, CloseReason reason) {
    System.out.println("socket closed: " + reason.getReasonPhrase());
  }

  @OnError
  public void error(Session session, Throwable throwable) {
    throwable.printStackTrace();

  }
  
  private void processVideo(Session ses) {
    int progress = 0;
    while (progress != 100) {
      progress++;
      try {
        Thread.sleep(200);
      } catch (InterruptedException exc) {
        // TODO Auto-generated catch block
        exc.printStackTrace();
      }
      try {
        ses.getBasicRemote().sendText("transfer");
      } catch (IOException exc) {
        // TODO Auto-generated catch block
        exc.printStackTrace();
      }
    }
  }
    
  private void processDownload(Session ses) {
    String modFileName = fileName.substring(0, fileName.length() - 4);
    String extension = fileName.substring(fileName.length() - 4);
    String path = "videoFiles/" + modFileName + "Edited" + extension ;
    try {
      ses.getBasicRemote().sendText(path);
    } catch (IOException exc) {
      // TODO Auto-generated catch block
      exc.printStackTrace();
    }
  }
    
  private void processingActivity(Session session) {
    processVideo(session);
    processDownload(session);
  }
  
  private void processingGroundTruth(Session ses) {
    try {
      ses.getBasicRemote().sendText("groundTruth");
    } catch (IOException exc) {
      // TODO Auto-generated catch block
      exc.printStackTrace();
    }
  }
}
