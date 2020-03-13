package br.com.rr.mastertech.cartao.client;

import br.com.rr.mastertech.cartao.client.dto.ClienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cliente", fallbackFactory = ClienteClientFallbackFactory.class)
public interface ClienteClient {

    @GetMapping("/cliente/{id}")
    ClienteDTO findById(@PathVariable Integer id);
}
