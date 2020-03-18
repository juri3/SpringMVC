package controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.ActionAnnotation;
import action.RequestMapping;
import action.RequestMapping.RequestMethod;
import dao.MybatisRcpDaoMysql;
import model.Rcp;
import model.RcpContent;

public class RcpController extends ActionAnnotation {

	@Override
	public void initProcess(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String rcp_list(HttpServletRequest request, HttpServletResponse res) throws Exception {
		/* HttpSession session = request.getSession();

		request.setCharacterEncoding("UTF-8");

		int pageSize = 3;
		int currentPage = 0;
		try {
			currentPage = Integer.parseInt(request.getParameter("pageNum"));
			session.setAttribute("pageNum", currentPage);
		} catch (Exception e) {
			if (session.getAttribute("pageNum") == null) {
				session.setAttribute("pageNum", 1);
			}
		}

		currentPage = (int) session.getAttribute("pageNum");

		MybatisRcpDaoMysql service = MybatisRcpDaoMysql.getInstance();
		int count = service.getArticleCount();

		int temp = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		if (currentPage > temp)
			currentPage = temp;

		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;

		List<RcpDataBean> li = service.getArticles(startRow, endRow);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int number = count - (currentPage - 1) * pageSize;

		// 페이지번호3개씩
		int bottomLine = 3;
		// 총페이지수 =총게시물수/한페이지에 나타낼게시물수 (3줄)+(총게시물%한페이지에 나타낼게시물수 == 0 ? 0:1)
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		// (123 1 456 4 )맨앞에오는 시작하는번호
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;

		request.setAttribute("currentPage", currentPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("count", count);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("number", number);
		request.setAttribute("bottomLine", bottomLine);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);

		request.setAttribute("li", li);*/

		return "/view/rcp/list.jsp";
	}

	@RequestMapping(value = "writeForm", method = RequestMethod.GET)
	public String rcp_writeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int rcpNum = 1;
		if (request.getParameter("rcpNum") != null) {
			rcpNum = Integer.parseInt(request.getParameter("rcpNum"));
		}

		request.setAttribute("rcpNum", rcpNum);

		return "/view/rcp/writeUploadForm.jsp";
	}

	@RequestMapping(value = "writePro", method = RequestMethod.POST)
	public String rcp_writePro(HttpServletRequest request, HttpServletResponse response) throws Exception {
	HttpSession session = request.getSession();	
		try {
			
			Rcp article = new Rcp();
			RcpContent rcpContent = new RcpContent();
			
			article.setRcpNum(Integer.parseInt(request.getParameter("rcpNum")));
			article.setTitle(request.getParameter("title"));
			article.setFoodName(request.getParameter("foodName"));
			article.setSubtitle(request.getParameter("subtitle"));
			article.setIngredient(request.getParameter("ingredient"));
			article.setQuantity(request.getParameter("quantity"));
			article.setCookingTime(request.getParameter("cookingTime"));
		
			article.setMemNum((int)session.getAttribute("memNum"));  //nullPointerException
			
			article.setThumbNail(request.getParameter("thumbNail"));
			article.setHashTag(request.getParameter("hashTag"));
			
			rcpContent.setStep(Integer.parseInt(request.getParameter("step")));
			rcpContent.setFileName(request.getParameter("fileName"));
			rcpContent.setContent(request.getParameter("content"));
			
			MybatisRcpDaoMysql service = MybatisRcpDaoMysql.getInstance();
			service.insertArticle(article);

			request.setAttribute("article", article);
			request.setAttribute("rcpContent", rcpContent);
			
			
			//session.setAttribute("memNum", memNum);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/view/rcp/writeUploadPro.jsp";
		// return "redirect:/rcp/list";
	}

	/*@RequestMapping(value = "content", method = RequestMethod.GET)
	public String board_content(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int rcpNum = Integer.parseInt(request.getParameter("rcpNum"));
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null || pageNum.equals("")) {
			pageNum = "1";
		}

		MybatisRcpDaoMysql dbPro = MybatisRcpDaoMysql.getInstance();
		RcpDataBean article = dbPro.getArticle(rcpNum);

		request.setAttribute("article", article);
		request.setAttribute("pageNum", pageNum);

		return "/view/rcp/content.jsp";
	}

	@RequestMapping(value = "updateForm", method = RequestMethod.GET)
	public String board_updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int rcpNum = Integer.parseInt(request.getParameter("rcpNum"));
		String pageNum = request.getParameter("pageNum");

		MybatisRcpDaoMysql dbPro = MybatisRcpDaoMysql.getInstance();
		RcpDataBean article = dbPro.getUpdateArticle(rcpNum);

		request.setAttribute("article", article);

		return "/view/rcp/updateForm.jsp";
	}

	@RequestMapping(value = "updatePro", method = RequestMethod.POST)
	public String board_updatePro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		RcpDataBean article = new RcpDataBean();
		article.setRcpNum(Integer.parseInt(request.getParameter("rcpNum")));
		article.setTitle(request.getParameter("title"));
		article.setFoodName(request.getParameter("foodName"));
		article.setSubtitle(request.getParameter("subtitle"));
		article.setFoodName(request.getParameter("ingredient"));
		article.setFoodName(request.getParameter("cookingTime"));
		article.setMemNum(Integer.parseInt(request.getParameter("memNum")));
		article.setFoodName(request.getParameter("thumbNail"));
		article.setFoodName(request.getParameter("hashTag"));

		article.setContent(request.getParameter("content"));
		// article.setPasswd(request.getParameter("passwd"));

		MybatisRcpDaoMysql dbPro = MybatisRcpDaoMysql.getInstance();
		int check = dbPro.updateBoard(article);

		request.setAttribute("check", check);

		return "/view/rcp/updatePro.jsp";
	}

	@RequestMapping(value = "deleteForm", method = RequestMethod.GET)
	public String board_deleteForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("rcpNum", request.getParameter("rcpNum"));

		return "/view/rcp/deleteForm.jsp";
	}

	@RequestMapping(value = "deletePro", method = RequestMethod.POST)
	public String board_deletePro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int rcpNum = Integer.parseInt(request.getParameter("rcpNum"));
		String passwd = request.getParameter("passwd");

		MybatisRcpDaoMysql dbPro = MybatisRcpDaoMysql.getInstance();

		request.setAttribute("check", check);

		return "/view/rcp/deletePro.jsp";
	}
*/
}
