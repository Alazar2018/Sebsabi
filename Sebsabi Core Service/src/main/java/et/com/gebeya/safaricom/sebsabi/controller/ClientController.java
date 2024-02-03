package et.com.gebeya.safaricom.sebsabi.controller;

import et.com.gebeya.safaricom.sebsabi.dto.ClientRequest;
import et.com.gebeya.safaricom.sebsabi.dto.ClientResponse;
import et.com.gebeya.safaricom.sebsabi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/core")
public class ClientController {
    private final ClientService clientService;
   @PostMapping("/signup/clients")
   @ResponseStatus(HttpStatus.CREATED)
    public void createClients(@RequestBody ClientRequest clientRequest){
        clientService.createClients(clientRequest);
   }
    @GetMapping("/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientResponse> getAllClients(){
        return clientService.getAllClients();
    }
}
