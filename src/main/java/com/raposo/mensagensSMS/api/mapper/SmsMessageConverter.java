package com.raposo.mensagensSMS.api.mapper;

import com.raposo.mensagensSMS.api.dto.SmsMessageInDTO;
import com.raposo.mensagensSMS.api.dto.SmsMessageOutDTO;
import com.raposo.mensagensSMS.infrastructure.entity.SmsMessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SmsMessageConverter {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sentAt", ignore = true)
    @Mapping(target = "statusEnvio", ignore = true)
    SmsMessageEntity paraEntity(SmsMessageInDTO dto);

    SmsMessageOutDTO paraDTO(SmsMessageEntity entity);

    List<SmsMessageOutDTO> paraListaDTO(List<SmsMessageEntity> entities);
}
