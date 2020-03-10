package br.com.rr.mastertech.cartoes.controller;

import br.com.rr.mastertech.cartoes.domain.Cliente;
import br.com.rr.mastertech.cartoes.dto.request.CreateClienteDTO;
import br.com.rr.mastertech.cartoes.dto.response.ClienteDTO;
import br.com.rr.mastertech.cartoes.mapper.ClienteMapper;
import br.com.rr.mastertech.cartoes.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClienteControllerTest {

    @InjectMocks
    private ClienteController controller;

    @Mock
    private ClienteService clienteService;

    @Mock
    private ClienteMapper clienteMapper;

    @Test
    public void findById_notFound() {
        when(clienteService.findById(any())).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> controller.findById(1));
    }

    @Test
    public void findById_found() {
        when(clienteService.findById(1)).thenReturn(Cliente.builder().id(1).name("Robson Rigatto").build());
        when(clienteMapper.toDTO(any())).thenReturn(new ClienteDTO(1, "Robson Rigatto"));
        ResponseEntity<ClienteDTO> response = controller.findById(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().getId());
    }

    @Test
    public void createTest() {
        CreateClienteDTO createDTO = new CreateClienteDTO();
        createDTO.setName("Robson Rigatto");

        when(clienteService.create(any())).thenReturn(Cliente.builder().id(5).name("Robson Rigatto").build());
        when(clienteMapper.toDTO(any())).thenReturn(new ClienteDTO(5, "Robson Rigatto"));

        ResponseEntity<ClienteDTO> response = controller.create(createDTO);

        verify(clienteService, times(1)).create(any());
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(5, response.getBody().getId());
    }
}
