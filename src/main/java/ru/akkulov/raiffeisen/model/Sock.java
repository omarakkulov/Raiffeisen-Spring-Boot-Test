package ru.akkulov.raiffeisen.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "socks")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "color",
            nullable = false)
    private String color;

    @Column(name = "cotton_part",
            nullable = false)
    private int cottonPart;

    @Column(name = "quantity",
            nullable = false)
    private int quantity;
}
