package api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import com.google.gson.Gson;



@Path("/UserService")
public class UserService {
   

   @GET
   @Path("/users")
   @Produces(MediaType.APPLICATION_JSON)
   public String getUsers(){
      String json = new Gson().toJson(new UserEx());
      System.out.println(json);
      System.out.println("sup!");
      return json;
   }
   
   @GET
   @Path("/test")
   @Produces(MediaType.APPLICATION_OCTET_STREAM)
   public Response getFile() {
	   System.out.print("The working dir: " +System.getProperty("wtp.deploy"));
	 String url = System.getProperty("wtp.deploy") + "\\BocaAnalytics\\video.mp4";
     File file = new File(url);
     return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM)
         .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" ) //optional
         .build();
   }
   
	private static final String SERVER_UPLOAD_LOCATION_FOLDER = "C://Users/nikos/Desktop/Upload_Files/";

	/**
	 * Upload a File
	 */

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {

		String filePath = contentDispositionHeader.getFileName();

		// save the file to the server
		//saveFile(fileInputStream, filePath);

		String output = "File saved to server location : " + filePath;
		System.out.print(filePath);

		return Response.status(200).entity(output).build();

	}

	// save uploaded file to a defined location on the server
	private void saveFile(InputStream uploadedInputStream,
			String serverLocation) {

		try {
			OutputStream outpuStream = new FileOutputStream(new File(serverLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			outpuStream = new FileOutputStream(new File(serverLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
