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
 * Servlet implementation class PhotosServlet
 */
@WebServlet("/Photos")
public class PhotosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB MessageBoard boardsBean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("photos", boardsBean.getAssets());
		request.getRequestDispatcher("photos.jsp").forward(request, response);
	}

}
