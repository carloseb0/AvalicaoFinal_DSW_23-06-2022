/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.cineminhafinal.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos
 */

@NamedNativeQueries({
    @NamedNativeQuery(name = "Produtora.findByPartTitulo",
            query = "SELECT * FROM produtora AS p WHERE p.nome ~* ?",
            resultClass = Produtora.class)
})
@Entity
@Table(name = "produtora")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Produtora.findAll", query = "SELECT p FROM Produtora p"),
    @NamedQuery(name = "Produtora.findByIdProdutora", query = "SELECT p FROM Produtora p WHERE p.idProdutora = :idProdutora"),
    @NamedQuery(name = "Produtora.findByNome", query = "SELECT p FROM Produtora p WHERE p.nome = :nome"),
    @NamedQuery(name = "Produtora.findByDaFundacao", query = "SELECT p FROM Produtora p WHERE p.daFundacao = :daFundacao"),
    @NamedQuery(name = "Produtora.findByNomefundador", query = "SELECT p FROM Produtora p WHERE p.nomefundador = :nomefundador")})
public class Produtora implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_produtora")
    private Integer idProdutora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    private String nome;
    @Column(name = "da_fundacao")
    @Temporal(TemporalType.DATE)
    private Date daFundacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nomefundador")
    private String nomefundador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProdutora")
    private Collection<Filme> filmeCollection;

    public Produtora() {
    }

    public Produtora(Integer idProdutora) {
        this.idProdutora = idProdutora;
    }

    public Produtora(Integer idProdutora, String nome, String nomefundador) {
        this.idProdutora = idProdutora;
        this.nome = nome;
        this.nomefundador = nomefundador;
    }

    public Integer getIdProdutora() {
        return idProdutora;
    }

    public void setIdProdutora(Integer idProdutora) {
        this.idProdutora = idProdutora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDaFundacao() {
        return daFundacao;
    }

    public void setDaFundacao(Date daFundacao) {
        this.daFundacao = daFundacao;
    }

    public String getNomefundador() {
        return nomefundador;
    }

    public void setNomefundador(String nomefundador) {
        this.nomefundador = nomefundador;
    }

    @XmlTransient
    public Collection<Filme> getFilmeCollection() {
        return filmeCollection;
    }

    public void setFilmeCollection(Collection<Filme> filmeCollection) {
        this.filmeCollection = filmeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProdutora != null ? idProdutora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produtora)) {
            return false;
        }
        Produtora other = (Produtora) object;
        if ((this.idProdutora == null && other.idProdutora != null) || (this.idProdutora != null && !this.idProdutora.equals(other.idProdutora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.upf.cineminhafinal.entity.Produtora[ idProdutora=" + idProdutora + " ]";
    }
    
}
