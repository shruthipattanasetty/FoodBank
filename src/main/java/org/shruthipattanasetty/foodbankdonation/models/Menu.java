package org.shruthipattanasetty.foodbankdonation.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@Setter
@Slf4j
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 48,name="name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private User user;
}
