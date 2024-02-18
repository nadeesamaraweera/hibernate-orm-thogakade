package lk.ijse.thogakde.entity;


import lk.ijse.thogakde.embeddad.NameIdentifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id //table hibarnate thiss is the primaty key of the
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Customer_id")  // table colum name
    private int id;

    @Column(name = "customer_name")
    private NameIdentifier Name;

    @Column(name = "customer_address")
    private String Address;

    @Column(name = "customer_tel")
    private String Tel;

    @Column(name = "customer_salary")
    private String Salary;

}
