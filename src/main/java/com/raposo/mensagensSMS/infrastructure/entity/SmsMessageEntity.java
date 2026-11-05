package com.raposo.mensagensSMS.infrastructure.entity;
import com.raposo.mensagensSMS.infrastructure.enums.StatusEnvio;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "mensagem")

public class SmsMessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "numeroTelefone", length = 100)
    private String numeroTelefone;

    @Column(name = "statusEnvio")
    private StatusEnvio statusEnvio;

    @Column(name = "sentAt")
    private LocalDateTime sentAt;

}
