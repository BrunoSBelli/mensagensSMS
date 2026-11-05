package com.raposo.mensagensSMS.business.service;

import com.raposo.mensagensSMS.api.dto.SmsMessageInDTO;
import com.raposo.mensagensSMS.api.dto.SmsMessageOutDTO;
import com.raposo.mensagensSMS.api.mapper.SmsMessageConverter;
import com.raposo.mensagensSMS.infrastructure.entity.SmsMessageEntity;
import com.raposo.mensagensSMS.infrastructure.enums.StatusEnvio;
import com.raposo.mensagensSMS.infrastructure.exceptions.ResourceNotFoundException;
import com.raposo.mensagensSMS.infrastructure.repositories.SmsMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public SmsMessageOutDTO atualizarStatus(Long idMessage, StatusEnvio statusEnvio){
        SmsMessageEntity entity = smsMessageRepository.findById(idMessage).orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada! " + idMessage));
        entity.setStatusEnvio(statusEnvio);

        return smsMessageConverter.paraDTO(smsMessageRepository.save(entity));

    }

    public List<SmsMessageOutDTO> buscarMensagemPorPeriodoStatus( StatusEnvio statusEnvio){
        List<SmsMessageEntity> entities = smsMessageRepository.findBySentAtBetweenAndStatusEnvio(LocalDateTime.now().minusHours(24),LocalDateTime.now(), statusEnvio);

        return smsMessageConverter.paraListaDTO(entities);
    }

}
