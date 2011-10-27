package jsmp.is.phasebook.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsmp.is.phasebook.db.Board;
import jsmp.is.phasebook.db.User;
import jsmp.is.phasebook.ejb.MessageBoard;
import jsmp.is.phasebook.ejb.Users;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/Boards")
public class BoardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB MessageBoard boardsBean;
	@EJB Users usersBean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("id") != null) {
			
			User current_user = (User) request.getSession().getAttribute("user");
			int board_id = Integer.parseInt(request.getParameter("id"));
			Board board = boardsBean.getBoard(board_id);
			
			if (board.isPrivate() && usersBean.isFriendsWith(current_user.getId(), board.getOwner().getId())) {
				
			}
		
			request.setAttribute("board", board);
		}
		request.getRequestDispatcher("board.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User current_user = (User) request.getSession().getAttribute("user");
		int board_id = Integer.parseInt(request.getParameter("board_id"));
		
		boardsBean.createTopic(board_id, request.getParameter("title"), request.getParameter("body"), current_user.getId());
		
		response.sendRedirect("/PhasebookWeb/Boards?id="+board_id);
	}

}
