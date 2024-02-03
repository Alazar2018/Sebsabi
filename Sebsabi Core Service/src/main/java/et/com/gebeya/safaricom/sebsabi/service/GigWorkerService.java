package et.com.gebeya.safaricom.sebsabi.service;

import et.com.gebeya.safaricom.sebsabi.dto.ClientRequest;
import et.com.gebeya.safaricom.sebsabi.dto.ClientResponse;
import et.com.gebeya.safaricom.sebsabi.dto.GigWorkerRequest;
import et.com.gebeya.safaricom.sebsabi.dto.GigwWorkerResponse;
import et.com.gebeya.safaricom.sebsabi.model.Client;
import et.com.gebeya.safaricom.sebsabi.model.GigWorker;
import et.com.gebeya.safaricom.sebsabi.repository.ClientRepository;
import et.com.gebeya.safaricom.sebsabi.repository.GigWorkerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GigWorkerService {
    private final GigWorkerRepository gigWorkerRepository;

    public void createGigWorkers(GigWorkerRequest gigWorkerRequest){
        GigWorker gigWorker=new GigWorker(gigWorkerRequest);
        gigWorkerRepository.save(gigWorker);
        log.info("Gig-Worker {} is Created and saved",gigWorkerRequest.getFirstName());
    }
    public List<GigwWorkerResponse> getAllGigWorker(){
        List<GigWorker> gigWorkers= gigWorkerRepository.findAll();
        return gigWorkers.stream().map(this::mapToClientResponse).toList();
    }

    private GigwWorkerResponse mapToClientResponse(GigWorker gigWorker) {
        return new GigwWorkerResponse(gigWorker);
    }

    public GigWorker getGigWorkerById(Long gigWorkerId) {
        return gigWorkerRepository.findById(gigWorkerId)
                .orElseThrow(() -> new RuntimeException("GigWorker not found with id: " + gigWorkerId));

    }
}
