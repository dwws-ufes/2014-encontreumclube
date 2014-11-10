package controller;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import dao.MapaDAO;
import domain.Igreja;
import domain.Mapa;

@ManagedBean
@SessionScoped
public class MapaBean implements Serializable{
 
	private static final long serialVersionUID = 1L;
    @EJB
    private MapaDAO mapaDAO;
    
    /* Retorna marcadores */
    public String getMarkers() {
    	List<Mapa> result = mapaDAO.listar();
    	String markes = "";
        for (Mapa m1 : result) {
        	double lat = m1.getLatitude();
        	double lng = m1.getLongitude();
        	Igreja igreja =  m1.getIgreja();
        	String titulo = m1.getDescricao();
        	String descricao = "<b>" + titulo + "</b><br>" + igreja.getLogradouro() + ", " + igreja.getBairro() + "<br>" + igreja.getCidade() + " - " + igreja.getUf() + "<br>" + igreja.getCep();
        	markes += "['"+titulo+"', "+lat+", "+lng+", '"+descricao+"'],";
        }
    	return markes;
    }
    
}