package com.geradordearquivo.servicepro.models;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.time.LocalDateTime;


@Data
@Document(collection = "Ordem_De_Servico_Sala")
public class OrdemDeServicoSalaMongo {

    @Field("id")
    private String id;

    @Field("diaFechamento")
    private LocalDateTime diaFechamento;

    @Field("nomeFuncionarioResponsavel")
    private String nomeFuncionarioResponsavel;

    @Field("matriculaFuncionario")
    private String matriculaFuncionario;

    @Field("requerenteNome")
    private String requerenteNome;

    @Field("requerenteMatricula")
    private String requerenteMatricula;

    public OrdemDeServicoSalaMongo(LocalDateTime diaFechamento, String matriculaFuncionario, String nomeFuncionarioResponsavel,
                                   String requerenteMatricula, String requerenteNome) {
        this.diaFechamento = diaFechamento;
        this.nomeFuncionarioResponsavel = nomeFuncionarioResponsavel;
        this.matriculaFuncionario = matriculaFuncionario;
        this.requerenteNome = requerenteNome;
        this.requerenteMatricula = requerenteMatricula;
    }
}
