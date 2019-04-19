package br.com.caelum.ingresso.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Compra {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Ingresso> ingressos;

    /**
     * @deprecated hibernate only
     */
    public Compra() {
    }


    public Compra(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
    }


}
