package controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.ActionAnnotation;
import action.RequestMapping;
import action.RequestMapping.RequestMethod;
import dao.RcpDao;
import model.RcpDataBean;

public class RcpController extends ActionAnnotation {
	
	@Override
	public void initProcess(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 		

		/*if(request.getParameter("boardid")!=null && !request.getParameter("boardid").equals("")){
			session.setAttribute("boardid", request.getParameter("boardid"));
			session.setAttribute("pageNum", 1);
		}
		String boardid=(String)session.getAttribute("boardid");
		if(boardid==null){
			boardid="1";
			session.setAttribute("boardid", "1");
		}
	}*/
	
	//board/list --> board_list
	@RequestMapping(value="list", method=RequestMethod.GET)
	public String board_list(HttpServletRequest request, HttpServletResponse res) throws Exception {
		HttpSession session=request.getSession();
		
		/*int pageSize=5;
		int num=9;
		int currentPage=1;

		if(session.getAttribute("pageNum")==null){
			session.setAttribute("pageNum", 1);
		}
		
		try{
			currentPage=Integer.parseInt(request.getParameter("pageNum"));
			session.setAttribute("pageNum", currentPage);
		}catch(Exception e){
			
		}
		
		currentPage=(int)session.getAttribute("pageNum");	
		
		String boardid=(String)session.getAttribute("boardid");
		
		
		RcpDataBean service=RcpDataBean.getInstance();	
		
		if(currentPage>pageCount){
			currentPage=pageCount;
			session.setAttribute("pageNum", currentPage);
		}
		
		int startRow=(currentPage-1)*pageSize+1;
		int endRow=startRow+pageSize-1;
		//int endRow=currentPage*pageSize;
		
		if (count==0) { startRow=1; endRow=1;}
		
		List li=service.getArticles(startRow, endRow, boardid);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		int number=count-(currentPage-1)*pageSize;
		

		int bottomLine=3;
		int startPage=1+(currentPage-1)/bottomLine*bottomLine;
		int endPage=startPage+bottomLine-1;		
		if(endPage>pageCount) endPage=pageCount;
		
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
		request.setAttribute("li", li);
		*/
		
		return "/view/rcp/list.jsp";
	}
	
	@RequestMapping(value="writeForm", method=RequestMethod.GET)
	public String rcp_writeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int rcpNum = 1;
		if(request.getParameter("rcpNum")!=null){
			rcpNum =Integer.parseInt(request.getParameter("rcpNum"));
		}
		request.setAttribute("rcpNum", rcpNum);
		return "/view/rcp/writeUploadForm.jsp";
	}
	
	@RequestMapping(value="writePro", method=RequestMethod.POST)
	public String rcp_writePro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try{
		RcpDataBean article = new RcpDataBean();
		article.setRcpNum(Integer.parseInt(request.getParameter("rcpNum")));	
		article.setTitle(request.getParameter("title"));
		article.setFoodName(request.getParameter("foodName"));
		article.setSubtitle(request.getParameter("subtitle"));
		article.setIngredient(request.getParameter("ingredient"));
		article.setCookingTime(request.getParameter("cookingTime"));
		article.setMemNum(Integer.parseInt(request.getParameter("memNum")));
		article.setThumbNail(request.getParameter("thumbNail"));
		article.setHashTag(request.getParameter("hashTag"));
		
		RcpDao service=RcpDao.getInstance();
		service.insertArticle(article);
		
		request.setAttribute("article", article);

		} catch(Exception e) {
			e.printStackTrace();
		}	
		return "/view/rcp/writeUploadPro.jsp";
		//return "redirect:/rcp/list";
	}
	
	
	
	/*@RequestMapping(value="content", method=RequestMethod.GET)
	public String board_content(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null || pageNum.equals("")){
			pageNum="1";
		}
		
		MybatisBoardDaoMysql dbPro=MybatisBoardDaoMysql.getInstance();
		BoardDataBean article=dbPro.getArticle(num);
		
		request.setAttribute("article", article);
		request.setAttribute("pageNum", pageNum);
		
		return "/view/board/content.jsp";
	}
	
	@RequestMapping(value="updateForm", method=RequestMethod.GET)
	public String board_updateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		MybatisBoardDaoMysql dbPro=MybatisBoardDaoMysql.getInstance();
		BoardDataBean article=dbPro.getUpdateArticle(num);
		
		request.setAttribute("article", article);
	
		return "/view/board/updateForm.jsp";
	}	
	
	@RequestMapping(value="updatePro", method=RequestMethod.POST)
	public String board_updatePro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		BoardDataBean article=new BoardDataBean();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setWriter(request.getParameter("writer"));		
		article.setContent(request.getParameter("content"));
		article.setPasswd(request.getParameter("passwd"));
		article.setSubject(request.getParameter("subject"));
		article.setEmail(request.getParameter("email"));
	
		MybatisBoardDaoMysql dbPro=MybatisBoardDaoMysql.getInstance();
		int check=dbPro.updateBoard(article);
		
		request.setAttribute("check", check);
	

		return "/view/board/updatePro.jsp";
	}
	
	@RequestMapping(value="deleteForm", method=RequestMethod.GET)
	public String board_deleteForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("num", request.getParameter("num"));

		return "/view/board/deleteForm.jsp";
	}
	
	@RequestMapping(value="deletePro", method=RequestMethod.POST)
	public String board_deletePro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num=Integer.parseInt(request.getParameter("num"));
		String passwd=request.getParameter("passwd");
		
		MybatisBoardDaoMysql dbPro=MybatisBoardDaoMysql.getInstance();
		int check= dbPro.deleteArticle(num, passwd);
		
		request.setAttribute("check", check);

		return "/view/board/updatePro.jsp";
	}*/
	
}
