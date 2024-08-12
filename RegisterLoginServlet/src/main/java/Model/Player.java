package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private int id;
    private String playerName ;
    private String gender;
    private String fatherName ;
    private String mobileNo;
    private String email;
    private String playerAge ;
    private String address ;
    private String batchTime ;
    private String howSoon;

}
