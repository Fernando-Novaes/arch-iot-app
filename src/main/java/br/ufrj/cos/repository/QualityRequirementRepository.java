package br.ufrj.cos.repository;


import br.ufrj.cos.domain.ArchitectureSolution;
import br.ufrj.cos.domain.IoTDomain;
import br.ufrj.cos.domain.QualityRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualityRequirementRepository extends JpaRepository<QualityRequirement, Long> {

    @Query(value = "select q from QualityRequirement as q")
    List<QualityRequirement> searchAll();

}
