package action;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.ant.ThreaddumpTask;

public abstract class ActionAnnotation extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}

	@Override
	public void init() throws ServletException {

	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		initProcess(req, resp);
		String command = req.getRequestURI();
		System.out.println("1:" + command);
		if (command.lastIndexOf("/") != -1) {
			command = command.substring(command.lastIndexOf("/") + 1);
		}
		System.out.println("2:" + command);

		Method[] mathods = this.getClass().getMethods();
		String viewPage = null;
		String tempMethod = null;
		try {
			for (Method method : mathods) {
				RequestMapping re = method.getAnnotation(RequestMapping.class);
				tempMethod = req.getMethod();
				tempMethod = tempMethod.toUpperCase();
				if (re != null && re.value().equals(command) && re.method().name().equals(tempMethod))
					viewPage = (String) method.invoke(this, req, resp);

			}
		} catch (Throwable e) {
			throw new ServletException(e);

		}
		if (viewPage != null) {
			if (viewPage.startsWith("redirect:")) {
				viewPage = viewPage.replace("redirect:", "");
				resp.sendRedirect(req.getContextPath() + viewPage);
			} else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			}
		} else {
			try {
				throw new RequestNotMatch(
						command + "not requestMapping in " + getClass().getName() + "for" + tempMethod);

			} catch (RequestNotMatch e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}
	}

	public abstract void initProcess(HttpServletRequest req, HttpServletResponse resp);

}

class RequestNotMatch extends Exception {
	public RequestNotMatch(String name) {
		super(name);
	}
}