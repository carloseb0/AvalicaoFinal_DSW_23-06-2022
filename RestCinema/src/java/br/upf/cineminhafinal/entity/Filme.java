/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.cineminhafinal.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */

@NamedNativeQueries({
    @NamedNativeQuery(name = "Filme.findByPartTitulo",
            query = "SELECT * FROM filme AS f WHERE f.titulo ~* ?",
            resultClass = Filme.class)
})
@Entity
@Table(name = "filme")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Filme.findAll", query = "SELECT f FROM Filme f"),
    @NamedQuery(name = "Filme.findByIdFilme", query = "SELECT f FROM Filme f WHERE f.idFilme = :idFilme"),
    @NamedQuery(name = "Filme.findByTitulo", query = "SELECT f FROM Filme f WHERE f.titulo = :titulo"),
    @NamedQuery(name = "Filme.findByDescricao", query = "SELECT f FROM Filme f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "Filme.findByDaEstreia", query = "SELECT f FROM Filme f WHERE f.daEstreia = :daEstreia"),
    @NamedQuery(name = "Filme.findByNrFaixaetaria", query = "SELECT f FROM Filme f WHERE f.nrFaixaetaria = :nrFaixaetaria")})
public class Filme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_filme")
    private Integer idFilme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "da_estreia")
    @Temporal(TemporalType.DATE)
    private Date daEstreia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nr_faixaetaria")
    private int nrFaixaetaria;
    @JoinColumn(name = "id_produtora", referencedColumnName = "id_produtora")
    @ManyToOne(optional = false)
    private Produtora idProdutora;

    public Filme() {
    }

    public Filme(Integer idFilme) {
        this.idFilme = idFilme;
    }

    public Filme(Integer idFilme, String titulo, String descricao, int nrFaixaetaria) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.descricao = descricao;
        this.nrFaixaetaria = nrFaixaetaria;
    }

    public Integer getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(Integer idFilme) {
        this.idFilme = idFilme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDaEstreia() {
        return daEstreia;
    }

    public void setDaEstreia(Date daEstreia) {
        this.daEstreia = daEstreia;
    }

    public int getNrFaixaetaria() {
        return nrFaixaetaria;
    }

    public void setNrFaixaetaria(int nrFaixaetaria) {
        this.nrFaixaetaria = nrFaixaetaria;
    }

    public Produtora getIdProdutora() {
        return idProdutora;
    }

    public void setIdProdutora(Produtora idProdutora) {
        this.idProdutora = idProdutora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFilme != null ? idFilme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Filme)) {
            return false;
        }
        Filme other = (Filme) object;
        if ((this.idFilme == null && other.idFilme != null) || (this.idFilme != null && !this.idFilme.equals(other.idFilme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.upf.cineminhafinal.entity.Filme[ idFilme=" + idFilme + " ]";
    }
    
}
