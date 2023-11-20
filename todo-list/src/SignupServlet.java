import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// Build JSON string
		// Read the request body data
		StringBuilder requestJson = new StringBuilder();
		String line = null;
		try (BufferedReader reader = request.getReader()) {
			while ((line = reader.readLine()) != null) {
				requestJson.append(line);
			}
		}
		
		Gson gson = new Gson();
		
		JsonObject jsonObject = gson.fromJson(requestJson.toString(), JsonObject.class);

		//Retrieve JSON Data
		String email = jsonObject.has("email") ? jsonObject.get("email").getAsString() : null;
		String password = jsonObject.has("password") ? jsonObject.get("password").getAsString() : null;
		String fname = jsonObject.has("fname") ? jsonObject.get("password").getAsString() : null;
		String lname = jsonObject.has("lname") ? jsonObject.get("password").getAsString() : null;


		if (fname == null || fname.trim().isBlank() || password == null || password.trim().isBlank()
				|| email == null || email.trim().isBlank() || lname == null || lname.trim().isBlank()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			String error = "Missing user info";
			pw.write(gson.toJson(error));
			pw.flush();
			return;
		}
		
		//Firebase stuff
	}

}
