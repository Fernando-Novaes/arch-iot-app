package br.ufrj.cos.domain;

import lombok.Data;

import java.util.List;

@Data
public class QualityRequirement {
    private String name;
    private List<Technology> techs;
}
