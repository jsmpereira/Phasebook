package jsmp.is.phasebook.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsmp.is.phasebook.db.User;
import jsmp.is.phasebook.ejb.Users;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/Profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB Users usersBean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("id") != null) {
			User user = (User) usersBean.getUser(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("user", user);
			request.setAttribute("friends", usersBean.getFriends(user.getId()));
		} else {
			User current_user = (User) request.getSession().getAttribute("current_user");
			request.setAttribute("user", current_user);
			request.setAttribute("friends", usersBean.getFriends(current_user.getId()));
		}
		request.getRequestDispatcher("profile.jsp").forward(request, response);
	}

}
