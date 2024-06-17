package br.ufrj.cos;

import br.ufrj.cos.domain.ArchitectureSolution;
import br.ufrj.cos.domain.IoTDomain;
import br.ufrj.cos.domain.QualityRequirement;
import br.ufrj.cos.domain.Technology;
import br.ufrj.cos.repository.ArchitectureSolutionRepository;
import br.ufrj.cos.repository.IoTDomainRepository;
import br.ufrj.cos.repository.QualityRequirementRepository;
import br.ufrj.cos.repository.TechnologyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Configuration
public class LoadDatabase {

//    @Bean
//    CommandLineRunner initDatabase(IoTDomainRepository domainRepository) {
//        return args -> {
//            domainRepository.save(createIoTDomain("IoT Domain 1"));
//            domainRepository.save(createIoTDomain("IoT Domain 2"));
//        };
//    }
//
//    @Transactional
//    IoTDomain createIoTDomain(String domainName) {
//        Technology tech1 = new Technology(null, "Tech 1 Description", "Tech 1 Reference");
//        Technology tech2 = new Technology(null, "Tech 2 Description", "Tech 2 Reference");
//
//        QualityRequirement qr1 = new QualityRequirement(null, "Quality Requirement 1", List.of(tech1));
//        QualityRequirement qr2 = new QualityRequirement(null, "Quality Requirement 2", List.of(tech2));
//
//        ArchitectureSolution solution1 = new ArchitectureSolution(null, "Architecture Solution 1", List.of(qr1));
//        ArchitectureSolution solution2 = new ArchitectureSolution(null, "Architecture Solution 2", List.of(qr2));
//
//        IoTDomain domain = new IoTDomain(null, domainName, List.of(solution1, solution2));
//        return domain;
//    }
}
