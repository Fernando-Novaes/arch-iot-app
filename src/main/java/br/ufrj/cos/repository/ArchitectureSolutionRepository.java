package br.ufrj.cos.repository;


import br.ufrj.cos.domain.ArchitectureSolution;
import br.ufrj.cos.domain.IoTDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchitectureSolutionRepository extends JpaRepository<ArchitectureSolution, Long> {
}
