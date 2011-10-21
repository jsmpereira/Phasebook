package jsmp.is.phasebook.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsmp.is.phasebook.ejb.MessageBoard;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/Board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB MessageBoard messageboard;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int board_id = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("topics", messageboard.getTopics(board_id));
		
		request.getRequestDispatcher("board.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int user_id = (Integer) request.getSession().getAttribute("user_id");
		
		messageboard.createTopic(Integer.parseInt(request.getParameter("board_id")), request.getParameter("title"), request.getParameter("body"), user_id);
		
		request.getRequestDispatcher("/board.jsp").forward(request, response);
	}

}
