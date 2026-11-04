package com.raposo.mensagensSMS.api.DTOs;

import com.raposo.mensagensSMS.infrastructure.enums.StatusEnvio;
import lombok.*;

import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsMessageInDTO {

    private String numeroTelefone;
}

