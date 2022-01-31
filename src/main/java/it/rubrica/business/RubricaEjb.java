package it.rubrica.business;

import java.util.ArrayList;
import java.util.List;

import it.rubrica.data.Contatto;
import it.rubrica.data.NumTelefono;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


/**
 * Session Bean implementation class RubricaEjb
 */
@Stateless
@LocalBean
public class RubricaEjb implements RubricaEjbLocal {

	@PersistenceContext(unitName="rubricaPS")
	EntityManager em;
    public RubricaEjb() {
        // TODO Auto-generated constructor stub
    }

    public Contatto inserisciContatto(Contatto c) {
    	em.persist(c);
    	return c;
    }
    
    public boolean inserimentoCorretto(String numero, int lunghezzaNumero) {
    	lunghezzaNumero = 10;
    	for (int i = 0; i < numero.length(); i++) {
    		if(!Character.isDigit(numero.charAt(i)))
    			return false;
    		
    		
    	}
    	return true;
    	
    }
    
    public boolean lunghezzaNumero(String numero ) {
    	
    	if(numero.length() == 10) {
    		return true;
    	}
    	
    	return false;
    }
    
    public List<Object[]> getContattoNumTelefono() {
    	String sql = "SELECT c.nome, c.cognome, c.email, n.numero FROM Contatto c JOIN c.numTelefoni n";
    	Query q = em.createQuery(sql);
    	List<Object[]> lista = q.getResultList();
    	return lista;
    }
    
    public List<Object[]> getContattoByCognome(String cognome) {
    	Query q = em.createQuery("SELECT c.nome, c.cognome, c.email, n.numero FROM Contatto c JOIN c.numTelefoni n where c.cognome like :cognome");
    	q.setParameter("cognome", "%"+cognome+"%");
    	List<Object[]> lista = q.getResultList();
    	return lista;
    }
    
    public List<Object[]> getContattoByNumero(String numero) {
    	Query q = em.createQuery("SELECT c.nome, c.cognome, c.email FROM Contatto c JOIN c.numTelefoni n where n.numero like :numero");
    	q.setParameter("numero", "%"+numero+"%");
    	List<Object[]> lista = q.getResultList();
    	return lista;
    }
    
    //IL METODO NON MODIFICA MA AGGIUNGE DUE NUMERI NUOVI! CI HO PROVATO MA NIENTE DA FARE!
    public void aggiornaContatto (int id, String numero1, String numero2 ) {
        Contatto c = em.find(Contatto.class, id);
       
        if (!numero1.isEmpty() ) {
            NumTelefono n1 = new NumTelefono();
            n1.setContatto(c);
            n1.setNumero(numero2);
            c.getNumTelefoni().add(n1);
        }
       
        if (!numero2.isEmpty() ) {
            NumTelefono n2 = new NumTelefono();
            n2.setContatto(c);
            n2.setNumero(numero2);
            c.getNumTelefoni().add(n2);
        }
            em.merge(c);
    }
    
    public void deleteContatto(Contatto contatto) {
    	em.remove(em.find(Contatto.class, contatto.getId()));
    }
   }

