package it.rubrica.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import it.rubrica.business.RubricaEjb;
import it.rubrica.data.Contatto;
import it.rubrica.data.NumTelefono;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
   RubricaEjb rubricaEjb;
    public MyServlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operazione = request.getParameter("operazione");
		
		if(operazione.equals("inserisci")) {
			
			Contatto cont1 = new Contatto();
			String nome = request.getParameter("nome");
			String cognome = request.getParameter("cognome");
			String email = request.getParameter("email");
			String numero1 = request.getParameter("numero1");
			String numero2 = request.getParameter("numero2");
			
			

			
			if(numero1.isEmpty() && numero2.isEmpty()) {
				PrintWriter out = response.getWriter();
				out.println("<div>*** ATTENZIONE! INSERIRE ALMENO UN NUMERO AL CONTATTO CHE VUOI INSERIRE! ***</div>");
				return;
			}
			
			
			if(rubricaEjb.inserimentoCorretto(numero1, 10) == false || rubricaEjb.inserimentoCorretto(numero2, 10) == false) {
				PrintWriter out = response.getWriter();
				out.println("<div>*** ATTENZIONE! INSERIRE SOLO NUMERI ! ***</div>");

				return;
			}
			
			if(rubricaEjb.lunghezzaNumero(numero1) == false || rubricaEjb.lunghezzaNumero(numero1) == false) {
				PrintWriter out = response.getWriter();
				out.println("<div>*** ATTENZIONE! INSERIRE UN NUMERO DI 10 CIFRE! ***</div>");

				return;
			}
			
			
			if(numero2.isEmpty()) {
				String nome2= request.getParameter("nome");
				String cognome2 = request.getParameter("cognome");
				String email2 = request.getParameter("email");
				String numero3 = request.getParameter("numero1");
				
				
				cont1.setNome(nome2);
				cont1.setCognome(cognome2);
				cont1.setEmail(email2);
				
				NumTelefono num1 = new NumTelefono();
				
				
				num1.setNumero(numero3);
				num1.setContatto(cont1);
				
				List<NumTelefono> lista = new ArrayList<NumTelefono>();
				lista.add(num1);
	
				cont1.setNumTelefoni(lista);
				rubricaEjb.inserisciContatto(cont1);
				PrintWriter out = response.getWriter();
				out.println("<div>*** IL CONTATTO E' STATO INSERITO CORRETTAMENTE ***");
				
				return;
			}
			
			cont1.setNome(nome);
			cont1.setCognome(cognome);
			cont1.setEmail(email);
			
			NumTelefono num1 = new NumTelefono();
			NumTelefono num2 = new NumTelefono();
			
			num1.setNumero(numero1);
			num2.setNumero(numero2);
			
			num1.setContatto(cont1);
			num2.setContatto(cont1);
			
			List<NumTelefono> lista = new ArrayList<NumTelefono>();
			lista.add(num1);
			lista.add(num2);
			cont1.setNumTelefoni(lista);
			rubricaEjb.inserisciContatto(cont1);
			PrintWriter out = response.getWriter();
			out.println("<div>*** IL CONTATTO E' STATO INSERITO CORRETTAMENTE ***");
			}
			
		if(operazione.equals("cercaCognome")) {
			
			String cognome = request.getParameter("cognome");
			
			PrintWriter out = response.getWriter();
			List<Object[]> lista = rubricaEjb.getContattoByCognome(cognome);
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
		
		if(operazione.equals("cercaNumero")) {
			
			String numero = request.getParameter("numero");
			
			PrintWriter out = response.getWriter();
			List<Object[]> lista = rubricaEjb.getContattoByNumero(numero);
			out.println("<h1>Ricerca del contatto</h1>");
			for (Object[] object : lista) {
				
				 out.println(" <table border = 1px solid black;>\r\n"
				 		+ "				<tr>\r\n"
				 		+ "					<th>Nome</th>\r\n"
				 		+ "					<th>Cognome</th>\r\n"
				 		+ "					<th>Email</th>\r\n"
				 		+ "				</tr>\r\n"
				 		+ "				<tr>\r\n"
				 		+ "					<td>" + object[0] +"</td>\r\n"
				 		+ "					<td>" + object[1] + "</td>\r\n"
				 		+ "					<td>" + object[2] + "</td>\r\n"
				 		+ "				</tr>\r\n"
				 		+ "			</table>\r\n"
				 		+ "			 ");
				 
				 }
		}
			
		if (operazione.equals("modifica")) {
				
			
				Integer id = Integer.parseInt(request.getParameter("id"));
				
				
				
				String numero1 = request.getParameter("numero1");
				String numero2 = request.getParameter("numero2");
				
				
				rubricaEjb.aggiornaContatto(id, numero1, numero2);
			}


		if(operazione.equals("elimina")) {
			
			Integer id = Integer.parseInt(request.getParameter("id"));
			
			
			Contatto c = new Contatto();
			c.setId(id);
			
			rubricaEjb.deleteContatto(c);
		}
	}
	
}


