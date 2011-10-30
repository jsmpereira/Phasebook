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
 * Servlet implementation class FriendsServlet
 */
@WebServlet("/Friends")
public class FriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB Users usersBean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// list friends
		User current_user = (User) request.getSession().getAttribute("current_user");
		request.setAttribute("friends", usersBean.getFriends(current_user.getId()));
		
		request.getRequestDispatcher("friends.jsp").forward(request, response);
	}

}
