package ru.akkulov.raiffeisen.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * Сущность носков.
 */
@Table(name = "socks")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sock {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
