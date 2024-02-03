package et.com.gebeya.safaricom.sebsabi.model;

import et.com.gebeya.safaricom.sebsabi.dto.ClientRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigInteger;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "clients")
public class Client extends Person{
   private String firstName;
   private String lastName;
   private String email;
   private String companyName;
   private String companyType;
   private String occupation;


   public Client(ClientRequest clientRequest){
      this.setFirstName(clientRequest.getFirstName());
      this.setLastName(clientRequest.getLastName());
      this.setEmail(clientRequest.getEmail());
      this.setOccupation(clientRequest.getOccupation());
      this.setCompanyName(clientRequest.getCompanyName());
      this.setCompanyType(clientRequest.getCompanyType());
      this.setIsActive(clientRequest.getIsActive());
   }
}
