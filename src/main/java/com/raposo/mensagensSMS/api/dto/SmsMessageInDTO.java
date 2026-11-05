package com.raposo.mensagensSMS.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsMessageInDTO {

    // @NotBlank rejeita null, "" e "   " (só espaços)
    @NotBlank(message = "O número é obrigatório")
    // @Pattern exige só dígitos, com DDD (10 ou 11 números)
    @Pattern(regexp = "^\\d{10,11}$", message = "Informe apenas dígitos, com DDD (10 ou 11 números)")
    private String numeroTelefone;
}

