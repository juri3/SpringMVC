package controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ShoppingRepository;
import model.RcpDataBean;
import action.RequestMapping.RequestMethod;
import action.ActionAnnotation;
import action.RequestMapping;

//서블릿 그자체, 왜냐하면 서블릿을 상속받았기 때문에
public class ShoppingController extends ActionAnnotation {

	public void initProcess(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("===============");
		HttpSession session = request.getSession();

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int currentPage = 1;
		try {
			currentPage = Integer.parseInt(request.getParameter("num"));
			session.setAttribute("num", currentPage);
			System.out.println(session.getAttribute("num"));
		} catch (Exception e) {
			if (session.getAttribute("num") == null) {
				session.setAttribute("num", 1);
			}
		}
	}

	ShoppingRepository service = ShoppingRepository.getInstance();

	@RequestMapping(value = "list") // 맨끝단의 url만 가지고 옴, get방식으로 한다.
	public String reciptview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.getAttribute("memId");
		RcpDataBean recipt = service.getIngredient();
		System.out.println("ingredient : " + recipt.getIngredient());
		String str = recipt.getIngredient();
		String[] array = str.split("#");
		for (int i = 0; i < array.length; i++) {
			System.out.println("split : " + array[i]);
		}
		request.setAttribute("ingredients", array);
		request.setAttribute("recipt", recipt);

		return "/reciptview.jsp";
	}

	@RequestMapping(value = "cart", method=RequestMethod.POST) // 맨끝단의 url만 가지고 옴, get방식으로 한다.
	public String shoppingCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.getAttribute("memId");
		RcpDataBean recipt = service.getIngredient();
		
		String[] selected = request.getParameterValues("select");
		System.out.println(selected);
		for(String str : selected){
			System.out.println(str);
		}
		request.setAttribute("selected", selected);

		return "/view/shopping/shoppingcartForm.jsp";

	}

}
