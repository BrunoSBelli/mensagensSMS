package com.raposo.mensagensSMS.business.service;

import com.raposo.mensagensSMS.api.DTOs.SmsMessageInDTO;
import com.raposo.mensagensSMS.api.DTOs.SmsMessageOutDTO;
import com.raposo.mensagensSMS.api.Mapper.SmsMessageConverter;
import com.raposo.mensagensSMS.infrastructure.entity.SmsMessageEntity;
import com.raposo.mensagensSMS.infrastructure.enums.StatusEnvio;
import com.raposo.mensagensSMS.infrastructure.repositories.SmsMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SmsMessageService {

    private final SmsMessageRepository smsMessageRepository;
    private final SmsMessageConverter smsMessageConverter;

    public SmsMessageOutDTO gravar(SmsMessageInDTO dto){

        SmsMessageEntity entity = smsMessageConverter.paraEntity(dto);

        entity.setSentAt(LocalDateTime.now());
        entity.setStatusEnvio(StatusEnvio.RECEBIDO);

        return smsMessageConverter.paraDTO(smsMessageRepository.save(entity));
    }

}
