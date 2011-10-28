package jsmp.is.phasebook.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsmp.is.phasebook.ejb.Users;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/Users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	@EJB Users usersBean;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if (request.getParameter("user_search") != null) {
			// search users
			request.setAttribute("users", usersBean.findUsers(request.getParameter("user_search")));
		} else {
			// list users
			request.setAttribute("users", usersBean.getUsers());
		}
		
		request.getRequestDispatcher("users.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO search users
		
		// friendship request
		int user_id = (Integer) request.getSession().getAttribute("user_id");
		
		if (request.getParameter("accept_friend_id") != null) {
			int friend_id = Integer.parseInt(request.getParameter("accept_friend_id"));
			usersBean.acceptFriendship(friend_id, user_id);
		}
		else {
			 int friend_id = Integer.parseInt(request.getParameter("request_friend_id"));
			 usersBean.requestFriendShip(user_id, friend_id);
		}
		
		response.sendRedirect("/PhasebookWeb/Users");
	}

}
