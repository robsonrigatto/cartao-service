package br.com.rr.mastertech.cartao.configuration;

import br.com.rr.mastertech.cartao.client.decoder.ClienteErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class ClienteClientConfiguration {
//
//    @Bean
//    public ErrorDecoder getClienteErrorDecoder() {
//        return new ClienteErrorDecoder();
//    }
//}