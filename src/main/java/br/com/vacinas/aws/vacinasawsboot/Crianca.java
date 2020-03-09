package br.com.vacinas.aws.vacinasawsboot;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crianca {
    private long id;
    private String nome;
    private LocalDate dataNascimento;
}
