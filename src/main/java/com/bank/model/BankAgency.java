package com.bank.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data   //cria implicitamente getters e setters dos atributos da classe
@Table(name = "bank_agency")
public class BankAgency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "number_agency")
    private long agencyNumber;

    @JoinColumn(name = "bank_number")
    @ManyToOne
    private Bank bankNumber;

}
