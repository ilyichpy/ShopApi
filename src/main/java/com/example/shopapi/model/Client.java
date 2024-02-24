package com.example.shopapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "client")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_surname")
    private String clientSurname;

    private Date birthday;

    private String gender;

    @Column(name = "registration_date")
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address addressId;

   public boolean notEnoughInfo() {
	   return this.clientName.isEmpty() || this.clientSurname.isEmpty()
			   || this.birthday == null || this.gender.isEmpty()
			   || addressId.notEnoughInfo();
   }
}
