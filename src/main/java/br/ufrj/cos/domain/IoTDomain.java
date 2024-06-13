package br.ufrj.cos.domain;

import lombok.Data;

import java.util.List;

@Data
public class IoTDomain {
    private String name;
    private List<ArchitectureSolution> archs;
}
