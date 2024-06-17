package br.ufrj.cos.repository;


import br.ufrj.cos.domain.IoTDomain;
import br.ufrj.cos.domain.QualityRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityRequirementRepository extends JpaRepository<QualityRequirement, Long> {
}
