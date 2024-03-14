package com.geradordearquivo.servicepro.models;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Document(collection = "Ordem_De_Servico_Wifi")
public class OrdemDeServicoMongo {

    @Id
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

    // Construtor padrão para a deserialização
    public OrdemDeServicoMongo() {
    }

    public OrdemDeServicoMongo(LocalDateTime diaFechamento, String matriculaFuncionario, String nomeFuncionarioResponsavel,
                               String requerenteMatricula, String requerenteNome) {
        this.diaFechamento = diaFechamento;
        this.nomeFuncionarioResponsavel = nomeFuncionarioResponsavel;
        this.matriculaFuncionario = matriculaFuncionario;
        this.requerenteNome = requerenteNome;
        this.requerenteMatricula = requerenteMatricula;
    }
}
