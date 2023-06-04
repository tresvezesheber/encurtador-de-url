package br.com.hebio.encurtadordeurl.model;


import br.com.hebio.encurtadordeurl.utils.Converters;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

@MappedSuperclass
public class AbstractEntity implements Serializable {

    //@JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataCadastro;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataAlteracao;

    @PrePersist
    public void dadosDefault() {
        dataCadastro = Calendar.getInstance();
    }

    @PreUpdate
    public void alterDataAlteracao() {
        dataAlteracao = Calendar.getInstance();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Calendar dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDataAlteracao() {
        return Converters.formatDateCalendar(dataAlteracao, "dd/MM/YYYY");
    }

    public void setDataAlteracao(Calendar dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}