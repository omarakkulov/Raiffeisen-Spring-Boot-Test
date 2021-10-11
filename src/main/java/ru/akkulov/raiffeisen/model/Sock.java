package ru.akkulov.raiffeisen.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Table(name = "socks")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "color")
    @JsonProperty("color")
    private String color;

    @Column(name = "cotton_part")
    @JsonProperty("cottonPart")
    private int cottonPart;

    @Column(name = "quantity")
    @JsonProperty("quantity")
    private int quantity;
}
