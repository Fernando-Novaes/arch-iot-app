package br.ufrj.cos.domain;

import lombok.Data;

import java.util.List;

@Data
public class ArchitectureSolution {
    private String id;
    private String name;
    private List<QualityRequirement> qrs;
}
