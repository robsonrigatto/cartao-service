package br.com.rr.mastertech.cartoes.controller;

import br.com.rr.mastertech.cartoes.domain.Cliente;
import br.com.rr.mastertech.cartoes.dto.request.CreateClienteDTO;
import br.com.rr.mastertech.cartoes.dto.response.ClienteDTO;
import br.com.rr.mastertech.cartoes.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClienteControllerTest {

    @InjectMocks
    private ClienteController controller;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    public void findById_notFound() {
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> controller.findById(1));
    }

    @Test
    public void findById_found() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(Cliente.builder().id(1).name("Robson Rigatto").build()));
        ResponseEntity<ClienteDTO> response = controller.findById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getId());
//        verify(clienteRepository.save(any()), times(1));
    }

    @Test
    public void createTest() {
        CreateClienteDTO createDTO = new CreateClienteDTO();
        createDTO.setName("Robson Rigatto");

        when(clienteRepository.save(any())).thenReturn(Cliente.builder().id(5).name("Robson Rigatto").build());

        ResponseEntity<ClienteDTO> response = controller.create(createDTO);

        verify(clienteRepository, times(1)).save(any());
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(5, response.getBody().getId());
    }
}
