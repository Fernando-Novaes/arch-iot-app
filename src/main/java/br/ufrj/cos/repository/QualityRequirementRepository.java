package br.ufrj.cos.repository;


import br.ufrj.cos.domain.IoTDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualityRequirementRepository extends JpaRepository<IoTDomain, Long> {
}