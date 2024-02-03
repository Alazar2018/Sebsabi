package et.com.gebeya.safaricom.sebsabi.repository;

import et.com.gebeya.safaricom.sebsabi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
