package API;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;



@Path("/UserService")
public class UserService {

   UserDao userDao = new UserDao();

   @GET
   @Path("/users")
   @Produces(MediaType.APPLICATION_JSON)
   public String getUsers(){
      String json = new Gson().toJson(userDao.getAllUsers());
      System.out.println(json);
      return json;
   }
   
   @GET
   @Path("/test")
   @Produces(MediaType.APPLICATION_OCTET_STREAM)
   public Response getFile() {
	   System.out.println("Working Directory/Video = " +
	             System.getProperty("user.dir"));
	   
     File file = new File("video.mp4");
     return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
         .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" ) //optional
         .build();
   }
}
