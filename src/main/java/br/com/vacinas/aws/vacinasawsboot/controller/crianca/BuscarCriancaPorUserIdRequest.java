package br.com.vacinas.aws.vacinasawsboot.controller.crianca;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BuscarCriancaPorUserIdRequest {
    private String userId;
}
