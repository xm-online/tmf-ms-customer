package com.icthh.xm.tmf.ms.customer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_characteristic")
public class CustomerCharacteristicEntity {

    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column
    private String key;

    @Column
    private String value;
}
