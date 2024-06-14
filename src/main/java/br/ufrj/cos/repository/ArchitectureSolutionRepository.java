package br.ufrj.cos.repository;


import br.ufrj.cos.domain.IoTDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchitectureSolutionRepository extends JpaRepository<IoTDomain, Long> {
}
