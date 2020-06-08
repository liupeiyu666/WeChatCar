package tangjia;

import java.io.Console;
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/Yu")
public class TestServlet implements Servlet
{

	private ServletConfig m_config;
	@Override
	public void destroy()
	{
		// TODO Auto-generated method stub
		System.out.println("destroy");
	}

	@Override
	public ServletConfig getServletConfig()
	{
		// TODO Auto-generated method stub
		return m_config;
	}

	@Override
	public String getServletInfo()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException
	{
		// TODO Auto-generated method stub
        System.out.println("我加载了--init");
        m_config=config;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		res.getWriter().write("EEEEEEEEEE");
	}

}
