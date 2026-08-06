package br.furb.resapifurb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "equipamentos")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do equipamento é obrigatório")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "tipo_id", nullable = false)
    private EquipmentType tipo;

    public Equipment() {
    }

    public Equipment(Long id, String nome, EquipmentType tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EquipmentType getTipo() {
        return tipo;
    }

    public void setTipo(EquipmentType tipo) {
        this.tipo = tipo;
    }
}
