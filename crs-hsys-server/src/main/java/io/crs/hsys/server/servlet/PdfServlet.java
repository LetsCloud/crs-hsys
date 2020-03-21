/**
 * 
 */
package io.crs.hsys.server.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author robi
 *
 */
@SuppressWarnings("serial")
public class PdfServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// create output stream from byte array in session
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String token = request.getParameter("token");
		byte[] pdf = (byte[]) request.getSession().getAttribute(token);
		baos.write(pdf);

		// setting some response headers
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");

		response.setContentType("application/pdf");

		// content length is needed for MSIE
		response.setContentLength(baos.size());

		// write ByteArrayOutputStream to ServletOutputStream
		ServletOutputStream out = response.getOutputStream();
		baos.writeTo(out);
		out.flush();
		out.close();
	}
}