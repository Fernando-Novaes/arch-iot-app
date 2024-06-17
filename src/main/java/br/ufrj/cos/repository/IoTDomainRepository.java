package br.ufrj.cos.repository;


import br.ufrj.cos.domain.IoTDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IoTDomainRepository extends JpaRepository<IoTDomain, Long> {

    @Query(value = "select d from IoTDomain d")
    List<IoTDomain> searchAll();

}