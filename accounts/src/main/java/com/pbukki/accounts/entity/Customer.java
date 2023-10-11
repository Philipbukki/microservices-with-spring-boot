package com.pbukki.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "native")
    @GenericGenerator(name="native",strategy = "native")
    private Integer customerId;
    private String name;
    private String email;
    private String mobileNumber;

}
