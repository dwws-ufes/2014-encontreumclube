package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;
import dao.ClubeDAO;
import domain.Clube;

@WebServlet("/rdf")
public class RDFServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	private ClubeDAO clubeDAO;
       
    public RDFServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model model = ModelFactory.createDefaultModel();
		List<Clube> result = clubeDAO.listar();
		
		for (Clube c1 : result) {
			String nomeClube = c1.getNome();
			String clubeURI = "http://localhost/encontreumclube/" + nomeClube.replace(" ", "");
						
			Resource clube = model.createResource(clubeURI);
			clube.addProperty(VCARD.FN, nomeClube);
			clube.addProperty(VCARD.ADR, model.createResource()
											.addProperty(VCARD.Street, c1.getIgreja().getLogradouro())
											//Falta o bairro
											.addProperty(VCARD.Locality, c1.getIgreja().getCidade())
											.addProperty(VCARD.Region, c1.getIgreja().getUf())
											.addProperty(VCARD.Country, "Brasil")
											.addProperty(VCARD.Pcode, c1.getIgreja().getCep()));
			//String geo = c1.getIgreja().getMapa().getLatitude() + ";" + c1.getIgreja().getMapa().getLongitude();
			clube.addProperty(VCARD.GEO, c1.getIgreja().getMapa().getLatitude() + ";" + c1.getIgreja().getMapa().getLongitude());
		}
						
		FileWriter out;
		try {
			out = new FileWriter("C:\\Users\\Juliana\\workspace\\EncontreUmClube\\WebContent\\clubes.rdf");
			model.write(out, "RDF/XML"); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("clubes.rdf");
	}


}
