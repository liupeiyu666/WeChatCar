package tangjia;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/rand")
public class TestHttp extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		Random t_Random=new Random();
		resp.getWriter().print(t_Random.nextInt(3));
		// TODO Auto-generated method stub
		//super.doGet(req, resp);
	}
}
