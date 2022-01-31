package it.rubrica.data;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Table(name="numtelefono")
@Entity
public class NumTelefono implements Serializable {

	private static final long serialVersionUID = 1L;

	private String numero;
	private Contatto contatto;

	public NumTelefono() {}
	
	@Id
	@Column(name = "numero")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	@ManyToOne
	@JoinColumn(name = "id_contatto")
	public Contatto getContatto() {
		return contatto;
	}

	public void setContatto(Contatto contatto) {
		this.contatto = contatto;
	}
}
