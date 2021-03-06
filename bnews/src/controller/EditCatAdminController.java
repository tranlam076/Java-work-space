package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckLoginLibrary;
import model.bean.Category;
import model.dao.CatDAO;

/**
 * Servlet implementation class EditCatAdminController
 */
public class EditCatAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCatAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!CheckLoginLibrary.checkLogin(request, response)){
			return;
		}
		
		CatDAO catDAO = new CatDAO();
		int idCat = Integer.parseInt(request.getParameter("cid"));
		String name = request.getParameter("name");
		String nameCat = new String(name.getBytes("ISO-8859-1"), "UTF-8");
		
		ArrayList<Category> listCat = catDAO.getItems();
		request.setAttribute("listCat", listCat);
		
		if(catDAO.editItem(idCat, nameCat)>0){
			response.sendRedirect(request.getContextPath()+"/admin/indexCat?msg=2");
			return;
		}
		else {
			response.sendRedirect(request.getContextPath()+"/admin/indexCat?msg=0");
			return;
		}
		
	}

}
