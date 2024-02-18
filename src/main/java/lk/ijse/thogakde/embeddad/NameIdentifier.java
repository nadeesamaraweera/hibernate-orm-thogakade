package lk.ijse.thogakde.embeddad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class NameIdentifier {
    private  String FistName ;
    private  String LastName;
}
