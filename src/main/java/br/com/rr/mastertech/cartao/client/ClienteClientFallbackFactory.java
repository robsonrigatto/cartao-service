package br.com.rr.mastertech.cartao.client;

import br.com.rr.mastertech.cartao.client.dto.ClienteDTO;
import br.com.rr.mastertech.cartao.exception.ClienteOfflineException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ClienteClientFallbackFactory implements FallbackFactory<ClienteClient> {


    @Override
    public ClienteClient create(Throwable throwable) {

        return new ClienteClient() {

            @Override
            public ClienteDTO findById(Integer id) {
                throw new ClienteOfflineException();
            }
        };
    }
}
