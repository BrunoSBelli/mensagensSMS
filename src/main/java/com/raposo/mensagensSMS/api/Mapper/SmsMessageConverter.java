package com.raposo.mensagensSMS.api.Mapper;

import com.raposo.mensagensSMS.api.DTOs.SmsMessageInDTO;
import com.raposo.mensagensSMS.api.DTOs.SmsMessageOutDTO;
import com.raposo.mensagensSMS.infrastructure.entity.SmsMessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SmsMessageConverter {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sentAt", ignore = true)
    @Mapping(target = "statusEnvio", ignore = true)
    SmsMessageEntity paraEntity(SmsMessageInDTO dto);

    SmsMessageOutDTO paraDTO(SmsMessageEntity entity);
}
