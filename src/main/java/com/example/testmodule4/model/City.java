package com.example.testmodule4.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Entity
@Table(name = "cities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;
    @NotNull
    private Long area;
    @NotNull
    private Long population;
    @NotNull
    private Long GPD;
    @NotEmpty
    private String description;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Country country;
}
