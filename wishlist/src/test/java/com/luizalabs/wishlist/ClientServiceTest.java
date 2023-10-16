package com.luizalabs.wishlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.luizalabs.wishlist.model.Client;
import com.luizalabs.wishlist.repository.ClientRepository;
import com.luizalabs.wishlist.service.ClientService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        clientService = new ClientService(clientRepository);
    }

    @Test
    public void testGetClientById() {
        String clientId = "1";
        Client client = new Client(clientId, "Lucas Brito");

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        Optional<Client> result = clientService.getClientById(clientId);

        assertTrue(result.isPresent());
        assertEquals(client, result.get());
    }

    @Test
    public void testGetClients() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("1", "Lucas"));
        clients.add(new Client("2", "Leandro"));

        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientService.getClients();

        assertNotNull(result);
        assertEquals(clients.size(), result.size());
        assertEquals(clients, result);
    }

    @Test
    public void testGetClientByName() {
        String clientName = "Luiza";
        Client client = new Client("1", clientName);

        when(clientRepository.findByName(clientName)).thenReturn(client);

        Client result = clientService.getClientByName(clientName);

        assertNotNull(result);
        assertEquals(client, result);
    }
}
