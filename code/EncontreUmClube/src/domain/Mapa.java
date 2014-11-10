package domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="mapa")
public class Mapa implements Serializable{
     
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="codigo", nullable=false, unique=true)
    private Long codigo;
    @Column(name="descricao")
    private String descricao;
    @Column(name="latitude")
    private double latitude;
    @Column(name="longitude")
    private double longitude;
    @OneToOne(fetch=FetchType.EAGER)
	@PrimaryKeyJoinColumn
    private Igreja igreja;
    
    // Construtor vazio
    public Mapa () {
    }
    
    // Construtor com parâmetros
    public Mapa (double lat, double lng, String desc) {
    	this.latitude = lat;
    	this.longitude = lng;
    	this.descricao = desc;
    }
    
	
    /* Setters */
    public void setCodigo(Long cod) { this.codigo = cod; }
    
    public void setDescricao(String desc) { this.descricao = desc; }
    
    public void setLatitude(double lat) { this.latitude = lat; }

	public void setLongitude(double lng) { this.longitude = lng; }
	
	public void setIgreja(Igreja igreja) { this.igreja = igreja; }

	/* Getters */
	
	public Long getCodigo() { return this.codigo; } 
	
	public String getDescricao() { return this.descricao; }

	public double getLatitude() { return this.latitude; }

	public double getLongitude() { return this.longitude; }
	
	public Igreja getIgreja() {return this.igreja; }

}
