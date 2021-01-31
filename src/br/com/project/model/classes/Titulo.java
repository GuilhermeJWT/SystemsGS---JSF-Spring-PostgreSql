package br.com.project.model.classes;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "titulo")
@SequenceGenerator(name = "titulo_seq", sequenceName = "titulo_seq", initialValue = 1, allocationSize = 1)
public class Titulo {

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "tit_codigo")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "titulo_seq")
	private Long tit_codigo;

	@IdentificaCampoPesquisa(descricaoCampo = "Origem", campoConsulta = "tit_origem")
	private String tit_origem; /* Comissão ou Origem */

	@IdentificaCampoPesquisa(descricaoCampo = "Valor R$", campoConsulta = "tit_valor")
	@Column(scale = 4, precision = 15)
	private BigDecimal tit_valor = BigDecimal.ZERO;

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date tit_datahora = new Date();

	private Boolean tit_baixado = Boolean.FALSE;

	@IdentificaCampoPesquisa(descricaoCampo = "Tipo", campoConsulta = "tit_tipo")
	private String tit_tipo; /* Receber ou Pagar */

	@IdentificaCampoPesquisa(descricaoCampo = "Valor Pago", campoConsulta = "tit_valorpago")
	@Column(scale = 4, precision = 15)
	private BigDecimal tit_valorpago = BigDecimal.ZERO;

	@IdentificaCampoPesquisa(descricaoCampo = "Usuário Abertura", campoConsulta = "ent_codigoabertura.ent_nomeFantasia", principal = 1)
	@Basic
	@ManyToOne
	@JoinColumn(nullable = false, name = "ent_codigoabertura", updatable = false)
	@ForeignKey(name = "ent_codigoabertura_fk")
	private Entidade ent_codigoabertura = new Entidade();

	@IdentificaCampoPesquisa(descricaoCampo = "Entidade Responsável", campoConsulta = "ent_codigo.ent_nomeFantasia")
	@Basic
	@ManyToOne
	@JoinColumn(nullable = false, name = "ent_codigo")
	@ForeignKey(name = "ent_codigo_fk")
	private Entidade ent_codigo = new Entidade();

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getTit_codigo() {
		return tit_codigo;
	}

	public void setTit_codigo(Long tit_codigo) {
		this.tit_codigo = tit_codigo;
	}

	public String getTit_origem() {
		return tit_origem;
	}

	public void setTit_origem(String tit_origem) {
		this.tit_origem = tit_origem;
	}

	public BigDecimal getTit_valor() {
		return tit_valor;
	}

	public void setTit_valor(BigDecimal tit_valor) {
		this.tit_valor = tit_valor;
	}

	public Date getTit_datahora() {
		return tit_datahora;
	}

	public void setTit_datahora(Date tit_datahora) {
		this.tit_datahora = tit_datahora;
	}

	public Boolean getTit_baixado() {
		return tit_baixado;
	}

	public void setTit_baixado(Boolean tit_baixado) {
		this.tit_baixado = tit_baixado;
	}

	public String getTit_tipo() {
		return tit_tipo;
	}

	public void setTit_tipo(String tit_tipo) {
		this.tit_tipo = tit_tipo;
	}

	public BigDecimal getTit_valorpago() {
		return tit_valorpago;
	}

	public void setTit_valorpago(BigDecimal tit_valorpago) {
		this.tit_valorpago = tit_valorpago;
	}

	public Entidade getEnt_codigoabertura() {
		return ent_codigoabertura;
	}

	public void setEnt_codigoabertura(Entidade ent_codigoabertura) {
		this.ent_codigoabertura = ent_codigoabertura;
	}

	public Entidade getEnt_codigo() {
		return ent_codigo;
	}

	public void setEnt_codigo(Entidade ent_codigo) {
		this.ent_codigo = ent_codigo;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

}
