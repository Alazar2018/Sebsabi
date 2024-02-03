package et.com.gebeya.safaricom.sebsabi.service;

import et.com.gebeya.safaricom.sebsabi.dto.ClientRequest;
import et.com.gebeya.safaricom.sebsabi.dto.ClientResponse;
import et.com.gebeya.safaricom.sebsabi.model.Client;
import et.com.gebeya.safaricom.sebsabi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;

    public void createClients(ClientRequest clientRequest){
        Client client=new Client(clientRequest);
        clientRepository.save(client);
        log.info("Client {} is Created and saved",client.getFirstName());
    }
    public List<ClientResponse> getAllClients(){
        List<Client> clients= clientRepository.findAll();
       return clients.stream().map(this::mapToClientResponse).toList();
    }

    private ClientResponse mapToClientResponse(Client client) {
        return new ClientResponse(client);
    }
}
