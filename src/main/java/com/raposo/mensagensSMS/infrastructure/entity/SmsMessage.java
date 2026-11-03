package com.raposo.mensagensSMS.infrastructure.entity;


import com.raposo.mensagensSMS.infrastructure.enums.statusEnvio;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SmsMessage {

    @Id
    private Long id;


    private String numero;
    private statusEnvio statusEnvio;
    private LocalDateTime sentAt;

}
