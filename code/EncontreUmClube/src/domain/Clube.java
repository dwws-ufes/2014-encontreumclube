package domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="clube")
public class Clube implements Serializable{
     
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="codigo", nullable=false, unique=true)
    private Long codigo;
    @Column(name="nome")
    private String nome;
    @Column(name="tipo")
    private String tipo;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="codIgreja")
    private Igreja igreja;
    
    /* Setters */
    public void setCodigo(Long cod) { this.codigo = cod; }
    
    public void setNome(String nome) { this.nome = nome; }
    
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public void setIgreja(Igreja igreja) { this.igreja = igreja; }
     
    /* Getters */
    
    public Long getCodigo() { return this.codigo; }
    
    public String getNome() { return this.nome; }
    
    public String getTipo() { return this.tipo; }

    public Igreja getIgreja() { return this.igreja; }
}
