package com.raposo.mensagensSMS.infrastructure.repositories;

import com.raposo.mensagensSMS.infrastructure.entity.SmsMessageEntity;
import com.raposo.mensagensSMS.infrastructure.enums.StatusEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SmsMessageRepository extends JpaRepository<SmsMessageEntity, Long> {

     Optional<SmsMessageEntity> findById(Long id);

     List<SmsMessageEntity> findBySentAtBetweenAndStatusEnvio(LocalDateTime dataInicio,
                                                              LocalDateTime dataFinal,
                                                              StatusEnvio statusEnvio);
}
