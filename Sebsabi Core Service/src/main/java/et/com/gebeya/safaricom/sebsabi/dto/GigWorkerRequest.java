package et.com.gebeya.safaricom.sebsabi.dto;

import et.com.gebeya.safaricom.sebsabi.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GigWorkerRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String qualification;
    private Date dob;
    private int age;
    private Status isActive;
}
