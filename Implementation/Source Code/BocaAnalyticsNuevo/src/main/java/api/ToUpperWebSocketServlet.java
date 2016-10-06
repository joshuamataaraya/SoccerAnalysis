package api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/toUpper")
public class ToUpperWebSocketServlet {
	

	static File uploadedFile = null;
    static String fileName = null;
    static FileOutputStream fos = null;
    final static String filePath="";

    @OnOpen
    public void open(Session session, EndpointConfig conf) {
        System.out.println("chat ws server open");
    }

    @OnMessage(maxMessageSize = 2048 * 2048)
    public void processUpload(ByteBuffer msg, boolean last, Session session) {
        System.out.println("Binary Data");      

        while(msg.hasRemaining()) {         
            try {
                fos.write(msg.get());
            } catch (IOException e) {               
                e.printStackTrace();
            }
        }

    }

    @OnMessage(maxMessageSize = 2048 * 2048)
    public void message(Session session, String msg) {
        System.out.println("got msg: " + msg);
        if(!msg.equals("end")) {
            fileName=msg.substring(msg.indexOf(':')+1);
            uploadedFile = new File(filePath+fileName);
            try {
                fos = new FileOutputStream(uploadedFile);
            } catch (FileNotFoundException e) {     
                e.printStackTrace();
            }
        }else {
            try {
                fos.flush();
                fos.close();
                System.out.println("End of transfer");
                session.getBasicRemote().sendText("sup");
            } catch (IOException e) {       
                e.printStackTrace();
            }
        }
    }

    @OnClose
    public void close(Session session, CloseReason reason) {
        System.out.println("socket closed: "+ reason.getReasonPhrase());
    }

    @OnError
    public void error(Session session, Throwable t) {
        t.printStackTrace();

    }
}
