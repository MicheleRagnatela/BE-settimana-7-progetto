package it.rubrica.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import it.rubrica.business.RubricaEjb;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowAllServlet
 */
public class ShowAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
    RubricaEjb rubricaEjb;
    public ShowAllServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		List<Object[]> lista = rubricaEjb.getContattoNumTelefono();
		out.println("<h1>Lista Dei Contatti</h1>");
		for (Object[] object : lista) {
			
			 out.println(" <table border = 1px solid black;>\r\n"
			 		+ "				<tr>\r\n"
			 		+ "					<th>Nome</th>\r\n"
			 		+ "					<th>Cognome</th>\r\n"
			 		+ "					<th>Email</th>\r\n"
			 		+ "					<th>Numero</th>\r\n"
			 		+ "				</tr>\r\n"
			 		+ "				<tr>\r\n"
			 		+ "					<td>" + object[0] +"</td>\r\n"
			 		+ "					<td>" + object[1] + "</td>\r\n"
			 		+ "					<td>" + object[2] + "</td>\r\n"
			 		+ "					<td>" + object[3] + "</td>\r\n"
			 		+ "				</tr>\r\n"
			 		+ "			</table>\r\n"
			 		+ "			 ");
			 
			 }
		} 
			
		
	}

	


