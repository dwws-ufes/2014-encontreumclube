package dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.Mapa;

@Stateless
public class MapaDAO {
     
	@PersistenceContext(unitName="EncontreUmClube")
    private EntityManager em;
       
	@SuppressWarnings("unchecked")
	public List<Mapa> listar(){
        List<Mapa> mapas = new ArrayList<Mapa>();      
        try {
        	Query query = em.createQuery("SELECT m FROM Mapa m");
			mapas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapas;
    }
     
}