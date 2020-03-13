package controller;

import java.io.File;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.ActionAnnotation;
import action.RequestMapping;
import action.RequestMapping.RequestMethod;
import dao.MybatisMemberDao;
import exception.DuplicateldException;
import exception.LoginFailException;
import model.Member;
import model.User;
import util.JdbcUtil;

public class MemberController extends ActionAnnotation{
    
    @Override
    public void initProcess(HttpServletRequest request,
            HttpServletResponse response)
    {   }
    
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String member_main(HttpServletRequest request, HttpServletResponse res)
            throws Exception
    {
    	HttpSession session = request.getSession();
		
		List<Member> li = null;
		MybatisMemberDao service = MybatisMemberDao.getInstance();
		
		int count = service.getProfileCount();
		li = service.getProfile();
		
		request.setAttribute("count", count);
		request.setAttribute("li", li);		
		
		return "/view/main.jsp";
    }
    
    @RequestMapping(value = "join", method = RequestMethod.GET)
    public String member_joinForm(HttpServletRequest req,
            HttpServletResponse res) throws Exception
    {
        return "/view/member/joinForm.jsp";
    }
    
    @RequestMapping(value = "join", method = RequestMethod.POST)
    public String member_joinPro(HttpServletRequest req,
            HttpServletResponse response) throws Exception
    {
    	 Member newMember = new Member(req.getParameter("email"),req.getParameter("name"),
                 req.getParameter("passwd"), 
                 req.getParameter("confirmpasswd"),"profile.png");
    	 
    	 Map<String, Boolean> errors = new HashMap<>();
         req.setAttribute("errors", errors);
         
         newMember.vaildate(errors);
         
         if (!errors.isEmpty()) return "/view/member/joinForm.jsp";
         Connection conn = null;
         try
         {
        	 MybatisMemberDao memberDao = MybatisMemberDao.getInstance();
        Member member = memberDao.selectById(newMember.getEmail());
        if (member != null)
        {
            JdbcUtil.rollback(conn);
            throw new DuplicateldException();
        }
        memberDao.insert(newMember);
        
      
         }catch (DuplicateldException e)
         {
             errors.put("duplicateId", Boolean.TRUE);
             return "/view/member/joinForm.jsp";
         }
            return "/view/member/loginForm.jsp";
            
        }
      
    @RequestMapping(value = "login")
    public String member_loginForm(HttpServletRequest req,
            HttpServletResponse res) throws Exception
    {
        return "/view/member/loginForm.jsp";
    }
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String member_loginPro(HttpServletRequest req,
            HttpServletResponse res) throws Exception
    {
    	HttpSession session = req.getSession();
    	
        String email = trim(req.getParameter("email"));
        String passwd = trim(req.getParameter("passwd"));
        
        Member member=null;
        
        Map<String, Boolean> errors = new HashMap<>();
        req.setAttribute("errors", errors);
        
        if (email == null || email.isEmpty()) errors.put("email", Boolean.TRUE);
        if (passwd == null || passwd.isEmpty())
            errors.put("password", Boolean.TRUE);
        
        if (!errors.isEmpty()) 
            return "/view/member/loginForm.jsp";
        
        try
        {
        	MybatisMemberDao memberDao = MybatisMemberDao.getInstance();
            
            member = memberDao.selectById(email);
            if (member == null) throw new LoginFailException();
            if (!member.matchPassword(passwd)) throw new LoginFailException();
            
            User user = new User(member.getEmail(), member.getName());
            req.getSession().setAttribute("authUser", user);
            
        }
        catch (LoginFailException e)
        {
            errors.put("idOrPwNotMatch", Boolean.TRUE);
            return "/view/member/loginForm.jsp";
        }
        
        session.setAttribute("memNum", member.getMemNum());
        req.setAttribute("memNum", session.getAttribute("memNum"));
        
        return "redirect:/member/main";
    }
    
    private String trim(String str)
    {
        return str == null ? null : str.trim();
    }
    
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String member_logout(HttpServletRequest req, HttpServletResponse res)
            throws Exception
    {
        HttpSession session = req.getSession(false);
        if (session != null)
        {
            session.invalidate();
        }
        return "redirect:/member/main";
    }
    
    @RequestMapping(value="mypage", method=RequestMethod.GET) 
	public String mypage(HttpServletRequest request, HttpServletResponse res) throws Exception {
		HttpSession session = request.getSession();
		
		String memNum = request.getParameter("memNum");
		
		Member member = null;
		MybatisMemberDao service = MybatisMemberDao.getInstance();
		
		member = service.getMember(memNum);
		
		request.setAttribute("member", member);
		request.setAttribute("session", session.getAttribute("memNum"));
		
		return "/view/mypage/mypage.jsp";
	}
    
    @RequestMapping(value="modifyForm", method=RequestMethod.GET)
	public String modifyForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		String memNum = request.getParameter("memNum");
		
		Member member = null;
		MybatisMemberDao service = MybatisMemberDao.getInstance();
		
		member = service.getMember(memNum);
		
		request.setAttribute("member", member);	

		return "/view/member/modifyForm.jsp";
	}
    
    @RequestMapping(value="modifyPro", method=RequestMethod.POST)
	public String modifyPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		String realFolder="";		
		String saveFolder="uploadFile";		
		String encType="UTF-8";
		int maxSize=10*1024*1024;
		
		Member member=new Member();				
		
		ServletContext context=request.getServletContext();
		realFolder=context.getRealPath(saveFolder); 
		try{
			MultipartRequest multi=new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
			
			Enumeration files=multi.getFileNames(); 
			
			if(files.hasMoreElements()){
				String name=(String)files.nextElement();
				File file=multi.getFile(name);
				if(file!=null){
					member.setProfile(file.getName());
				}else{
					member.setProfile(multi.getParameter("profile"));
				}
			}
			
			member.setMemNum(Integer.parseInt(multi.getParameter("memNum")));
			member.setPasswd(multi.getParameter("passwd"));
			member.setName(multi.getParameter("name"));
			member.setSelfIntroduction(multi.getParameter("selfIntroduction"));
			
			MybatisMemberDao service = MybatisMemberDao.getInstance();
			
			int check = service.updateMember(member);
			
			request.setAttribute("check", check);
			request.setAttribute("member", member);
		}catch(Exception e){
			e.printStackTrace();
		}		

		return "/view/member/modifyPro.jsp";
	}
    
    @RequestMapping(value="follow", method=RequestMethod.GET)
	public String follow(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		String memNum = request.getParameter("memNum");
		
		Member member = null;
		MybatisMemberDao service = MybatisMemberDao.getInstance();
		
		member = service.getMember(memNum);
		
		request.setAttribute("member", member);	
	
		return "/view/mypage/follow.jsp";
	}
  
}