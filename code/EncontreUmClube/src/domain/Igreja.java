package domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="igreja")
public class Igreja implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long codigo;
	@Column(name="logradouro")
	private String logradouro; // Logradouro + Número
	@Column(name="bairro")
	private String bairro;
	@Column(name="cidade")
	private String cidade;
	@Column(name="uf")
	private String uf;
	@Column(name="cep")
	private String cep;
	@OneToOne(mappedBy="igreja",fetch=FetchType.EAGER)
    private Mapa mapa;
	@OneToMany(mappedBy="igreja",fetch=FetchType.LAZY)
    private List<Clube> listaClube;
	
	/* Setters */
	public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
	
	public void setBairro(String bairro) { this.bairro = bairro; }
	
	public void setCidade(String cidade) { this.cidade = cidade; }
	
	public void setUf(String uf) { this.uf = uf; }
	
	public void setCep(String cep) { this.cep = cep; }
	
	public void setClube(List<Clube> listaClube) { this.listaClube = listaClube; }
	
	
	/* Getters */
	public String getLogradouro() { return logradouro; }
	
	public String getBairro() { return bairro; }
	
	public String getCidade() { return cidade; }
	
	public String getUf() { return uf; }
	
	public String getCep() { return cep; }
	
	public List<Clube> getClube() { return listaClube; }
	
	public Mapa getMapa() { return mapa; }
}
