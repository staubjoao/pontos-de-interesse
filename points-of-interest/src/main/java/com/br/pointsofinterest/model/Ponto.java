package com.br.pointsofinterest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "ponto")
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "descricao", nullable = false)
    @NotNull(message = "Descrição não pode ser vazio")
    private String descricao;

    @Column(name = "ponto_x", nullable = false)
    @NotNull(message = "Ponto x não pode ser vazio")
    private Integer x;

    @Column(name = "ponto_y", nullable = false)
    @NotNull(message = "Ponto y não pode ser vazio")
    private Integer y;

    public Ponto(UUID id, String descricao, Integer x, Integer y) {
        this.setId(id);
        this.setDescricao(descricao);
        this.setX(x);
        this.setY(y);
    }

    public Ponto() {

    }
}
