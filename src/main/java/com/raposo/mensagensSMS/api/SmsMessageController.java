package com.raposo.mensagensSMS.api;

import com.raposo.mensagensSMS.api.DTOs.SmsMessageInDTO;
import com.raposo.mensagensSMS.api.DTOs.SmsMessageOutDTO;
import com.raposo.mensagensSMS.business.service.SmsMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mensagens")
@RequiredArgsConstructor
@Tag(name = "Mensagens", description = "Cadastro e busca de mensagens")
public class SmsMessageController {

    private final SmsMessageService smsMessageService;

    @PostMapping
    @Operation(summary = "Salvar Mensagens", description = "Cria uma nova mensagem")
    @ApiResponse(responseCode = "201", description = "Mensagem salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public SmsMessageOutDTO salvarMensagem(@RequestBody SmsMessageInDTO dto){
       return smsMessageService.gravar(dto);
    }

}
