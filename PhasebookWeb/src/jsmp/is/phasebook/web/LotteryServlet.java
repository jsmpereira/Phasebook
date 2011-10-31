package jsmp.is.phasebook.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsmp.is.phasebook.db.User;
import jsmp.is.phasebook.lottery.TheLottery;

/**
 * Servlet implementation class LotteryServlet
 */
@WebServlet("/Lottery")
public class LotteryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@EJB TheLottery lotteryBean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LotteryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("lotteryBean", lotteryBean);
		request.getRequestDispatcher("lottery.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		User current_user = (User) request.getSession().getAttribute("current_user");
		int number = Integer.parseInt(request.getParameter("number"));

		lotteryBean.purchase(number, current_user.getId());
		
		response.sendRedirect("/PhasebookWeb/Lottery");
	}

}
