package jsmp.is.phasebook.web;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsmp.is.phasebook.db.Board;
import jsmp.is.phasebook.db.User;
import jsmp.is.phasebook.ejb.MessageBoard;
import jsmp.is.phasebook.ejb.Notifier;
import jsmp.is.phasebook.ejb.Users;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/Boards")
public class BoardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB MessageBoard boardsBean;
	@EJB Users usersBean;
	@EJB Notifier notifierBean;
       
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
			
			if (current_user.getId() != board.getOwner().getId() && board.isPrivate() && !usersBean.isFriendsWith(current_user.getId(), board.getOwner().getId())) {
				response.sendRedirect("/PhasebookWeb/Users?oops=true");
			} else {
				request.setAttribute("board", board);
				request.getRequestDispatcher("board.jsp").forward(request, response);
			}	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int board_id = 0;
		String title = null, body = null;
		String fileName = null;
		User current_user = (User) request.getSession().getAttribute("user");
		
		if (ServletFileUpload.isMultipartContent(request)){
			// Create a factory for disk-based file items
			FileItemFactory factory = new DiskFileItemFactory();

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				// Parse the request
				List items = upload.parseRequest(request);
				
				// Process the uploaded items
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
				    FileItem item = (FileItem) iter.next();

				    if (item.isFormField()) {
				    	
				    	if (item.getFieldName().equalsIgnoreCase("title")) {
				    		title = item.getString();
				    	} else if (item.getFieldName().equalsIgnoreCase("body")) {
				    		body = item.getString();
				    	} else {
				    		board_id = Integer.parseInt(item.getString());
				    	}

				    } else {
				    	if (item.getSize() != 0) {
				    		fileName = item.getName(); 
				    		File saveFile = new File("/usr/local/jboss/server/default/deploy/ROOT.war/images/"+fileName);          
				    		item.write(saveFile);
				    	}
				    }
				}
				
				int topic_id = boardsBean.createTopic(board_id, title, body, fileName, current_user.getId());
				notifierBean.boardNotification(board_id, topic_id);

			} catch(FileUploadException ex) {  
	            log("Error encountered while parsing the request",ex);  
	        } catch(Exception ex) {  
	            log("Error encountered while uploading file",ex);  
	        }
		}/* else {
			boardsBean.createTopic(board_id, request.getParameter("title"), request.getParameter("body"), fileName, current_user.getId());
		}*/
		
		response.sendRedirect("/PhasebookWeb/Boards?id="+board_id);
	}

}
