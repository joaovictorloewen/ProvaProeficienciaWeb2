package br.furb.resapifurb.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EquipmentRequest {

    @NotBlank(message = "O nome do equipamento é obrigatório")
    private String nome;

    @NotNull(message = "O tipo de equipamento é obrigatório")
    @Valid
    private EquipmentTypeRequest tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EquipmentTypeRequest getTipo() {
        return tipo;
    }

    public void setTipo(EquipmentTypeRequest tipo) {
        this.tipo = tipo;
    }

    public static class EquipmentTypeRequest {

        @NotBlank(message = "O nome do tipo é obrigatório")
        private String nome;

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }
}
