package br.ufrj.cos.repository;


import br.ufrj.cos.domain.IoTDomain;
import br.ufrj.cos.domain.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {
}
