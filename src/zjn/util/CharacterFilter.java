package zjn.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterFilter implements Filter {
	
	 protected String encoding=null;
	 protected FilterConfig filterConfig=null;

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		 if(encoding!=null)
		  {
		   //ÉèÖÃrequest×Ö·û±àÂë
		   request.setCharacterEncoding(encoding);
		         //ÉèÖÃrespongse×Ö·û±àÂë
		   response.setContentType("text/html;charset="+encoding);
		  }
		 
		  try {
			   chain.doFilter(request, response);
			  } catch (IOException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  } catch (ServletException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  }

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Character À¹½ØÆ÷Æô¶¯");
		 this.filterConfig=filterConfig;
		 this.encoding=filterConfig.getInitParameter("encoding");

	}

}
