package br.ufrj.cos.repository;


import br.ufrj.cos.domain.IoTDomain;
import br.ufrj.cos.components.chart.data.IoTDomainRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IoTDomainRepository extends JpaRepository<IoTDomain, Long> {

    @Query(value = "select d from IoTDomain d")
    List<IoTDomain> searchAll();

    @Query(value = "SELECT new br.ufrj.cos.components.chart.data.IoTDomainRecord(i.name, COUNT(i), (SELECT COUNT(*) FROM IoTDomain i2)) FROM IoTDomain i GROUP BY i.name")
    List<IoTDomainRecord> countIoTDomainsGroupedByName();

}