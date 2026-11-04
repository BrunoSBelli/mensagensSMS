package com.raposo.mensagensSMS.api.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.raposo.mensagensSMS.infrastructure.enums.StatusEnvio;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsMessageOutDTO {

    private Long id;
    private String numeroTelefone;
    private StatusEnvio statusEnvio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime sentAt;
}
