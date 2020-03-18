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
import model.Rcp;
import action.RequestMapping.RequestMethod;
import action.ActionAnnotation;
import action.RequestMapping;

//���� ����ü, �ֳ��ϸ� ������ ��ӹ޾ұ� ������
public class ShoppingController extends ActionAnnotation {

	public void initProcess(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("=====dd==========");
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

	@RequestMapping(value = "list") // �ǳ����� url�� ������ ��, get������� �Ѵ�.
	public String reciptview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.getAttribute("memId");
		Rcp recipt = service.getIngredient();
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

	@RequestMapping(value = "cart", method=RequestMethod.POST) // �ǳ����� url�� ������ ��, get������� �Ѵ�.
	public String shoppingCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.getAttribute("memId");
		Rcp recipt = service.getIngredient();
		
		String[] selected = request.getParameterValues("select");
		System.out.println(selected);
		for(String str : selected){
			System.out.println(str);
		}
		request.setAttribute("selected", selected);

		return "/view/shopping/shoppingcartForm.jsp";

	}

}
