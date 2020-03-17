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
import model.Cart;
import model.RcpDataBean;
import model.Sale;
import action.RequestMapping.RequestMethod;
import action.ActionAnnotation;
import action.RequestMapping;

//서블릿 그자체, 왜냐하면 서블릿을 상속받았기 때문에
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

	@RequestMapping(value = "list") // 맨끝단의 url만 가지고 옴, get방식으로 한다.
	public String reciptview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("memNum", "1");
		int rcpNum = 1;// 나중에 get값으로 받음
		
		RcpDataBean recipt = service.getIngredient(rcpNum);

		Sale sale = service.getSale(rcpNum);
		System.out.println("price : "+sale);
		
		System.out.println("ingredient : " + recipt.getIngredient());
		
		String[] array = ingredients_split(recipt.getIngredient());
		
		request.setAttribute("ingredients", array);
		request.setAttribute("recipt", recipt);
		request.setAttribute("sale", sale);

		return "/reciptview.jsp";
	}
	
	private String[] ingredients_split(String ingredients){
		String str = ingredients;
		String[] array = str.split("#");
		for (String ingredient : array) {
			System.out.println("split : " + ingredient);
		}
		return array;
	}

	@RequestMapping(value = "addcart", method=RequestMethod.POST) // 맨끝단의 url만 가지고 옴, get방식으로 한다.
	public String addcart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		Cart cart = new Cart();
		int memNo = Integer.parseInt((String)session.getAttribute("memNum"));
		cart.setMemNum(memNo);
		cart.setPrice(Integer.parseInt(request.getParameter("price")));
		cart.setQty(Integer.parseInt(request.getParameter("amount")));
		cart.setProductName(request.getParameter("productName"));
		int result = service.insertCart(cart);
		List<Cart> cartlist = service.getCart(memNo);
		System.out.println("getcart : "+cartlist);
		
		request.setAttribute("cartlist", cartlist);
		
		return "redirect:/shopping/cartview";
		
	}

	@RequestMapping(value = "cartview", method=RequestMethod.GET) // 맨끝단의 url만 가지고 옴, get방식으로 한다.
	public String cartview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		Cart cart = new Cart();
		int memNo = Integer.parseInt((String)session.getAttribute("memNum"));
		List<Cart> cartlist = service.getCart(memNo);
		System.out.println("getcart : "+cartlist);
		
		request.setAttribute("cartlist", cartlist);
		
		return "/view/shopping/shoppingcartForm.jsp";
		
	}
}
