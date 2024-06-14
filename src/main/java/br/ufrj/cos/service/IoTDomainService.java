package br.ufrj.cos.service;

import br.ufrj.cos.domain.ArchitectureSolution;
import br.ufrj.cos.domain.IoTDomain;
import br.ufrj.cos.domain.QualityRequirement;
import br.ufrj.cos.domain.Technology;
import br.ufrj.cos.repository.IoTDomainRepository;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import br.ufrj.cos.components.treeview.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class IoTDomainService {

    @Autowired
    private IoTDomainRepository ioTDomainRepository;

    public List<IoTDomain> getAllIoTDomains() {
        return ioTDomainRepository.findAll();
    }

    public TreeNode<IoTDomain> buildIoTDomainsTree() {
        List<IoTDomain> ioTDomains = getAllIoTDomains();
        TreeNode<IoTDomain> rootNode = new TreeNode<>(null); // Assuming you have a TreeNode class

        // Logic to build the tree structure
        for (IoTDomain ioTDomain : ioTDomains) {
            TreeNode<IoTDomain> domainNode = new TreeNode<>(ioTDomain);
            rootNode.addChild(domainNode);

            // Build ArchitectureSolution nodes
            for (ArchitectureSolution solution : ioTDomain.getArchs()) {
                TreeNode<ArchitectureSolution> solutionNode = new TreeNode<>(solution);
                domainNode.addChild(solutionNode);

                // Build QualityRequirement nodes
                for (QualityRequirement requirement : solution.getQrs()) {
                    TreeNode<QualityRequirement> requirementNode = new TreeNode<>(requirement);
                    solutionNode.addChild(requirementNode);

                    // Build Technology nodes
                    for (Technology technology : requirement.getTechs()) {
                        TreeNode<Technology> technologyNode = new TreeNode<>(technology);
                        requirementNode.addChild(technologyNode);
                    }
                }
            }
        }

        return rootNode;
    }
}
