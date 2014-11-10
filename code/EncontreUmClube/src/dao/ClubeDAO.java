package dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import domain.Clube;

@Stateless
public class ClubeDAO {
     
	@PersistenceContext(unitName="EncontreUmClube")
    private EntityManager em;
       
	@SuppressWarnings("unchecked")
	public List<Clube> listar(){
        List<Clube> clubes = new ArrayList<Clube>();      
        try {
        	Query query = em.createQuery("SELECT c FROM Clube c");
			clubes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubes;
    }
     
}