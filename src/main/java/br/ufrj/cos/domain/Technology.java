package br.ufrj.cos.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor
@NoArgsConstructor @EqualsAndHashCode
public class Technology {
    @Column(columnDefinition = "VARCHAR(255)")
    private String description;
    @Column(columnDefinition = "VARCHAR(255)")
    private String reference;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
