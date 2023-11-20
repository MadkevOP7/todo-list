import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
		
	private static final long serialVersionUID = 1L;

		//NEED TO FINISH
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Gson gson = new Gson();
		
		if (username == null || username.isBlank() || password == null || password.isBlank()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			String error = "Info missing";
			pw.write(gson.toJson(error));
			pw.flush();
		}
		
		try {
			if (JDBC_Connector.validate_login(username, password)) {//login info is valid, redirect logged in user
				//add the logged in user cookie
				Cookie c = new Cookie(username, "LoggedIn");
				response.addCookie(c);
			    response.getWriter().write(username);
				response.sendRedirect("home.html");
			}
			else {//login credential is incorrect
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				String error = "Username or password is incorrect";
				pw.write(gson.toJson(error));
				pw.flush();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("no class found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
