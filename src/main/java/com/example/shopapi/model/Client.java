package com.example.shopapi.model;


import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "client")
public class Client {

    @Id
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

    @Column(name = "address_id")
    private UUID addressId;

}
