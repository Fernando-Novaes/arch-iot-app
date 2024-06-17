package br.ufrj.cos.service;

import br.ufrj.cos.components.treeview.TreeBuilder;
import br.ufrj.cos.domain.IoTDomain;
import br.ufrj.cos.repository.IoTDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufrj.cos.components.treeview.TreeNode;

import java.util.*;

@Service
public class IoTDomainService {

    @Autowired
    private IoTDomainRepository ioTDomainRepository;
    private TreeBuilder treeBuilder;

    public List<IoTDomain> getAllIoTDomains() {
        return ioTDomainRepository.searchAll();
    }

    public TreeNode<Object> getTree() {
        List<IoTDomain> domains = ioTDomainRepository.findAll();
        this.treeBuilder = new TreeBuilder();
        return treeBuilder.buildTree(domains);
    }

//    public TreeNode<IoTDomain> buildIoTDomainsTree() {
//        List<IoTDomain> ioTDomains = getAllIoTDomains();
//
//        TreeNode<IoTDomain> rootNode = new TreeNode<>(null);
//
//        for (IoTDomain domain : ioTDomains) {
//            TreeNode<IoTDomain> domainNode = new TreeNode<>(domain);
//            rootNode.addChild(domainNode);
//
//            for (ArchitectureSolution solution : domain.getArchs()) {
//                TreeNode<IoTDomain> solutionNode = new TreeNode<>(new IoTDomain(solution.getId(), solution.getName(), null));
//                domainNode.addChild(solutionNode);
//
//                for (QualityRequirement qr : solution.getQrs()) {
//                    TreeNode<IoTDomain> qrNode = new TreeNode<>(new IoTDomain(qr.getId(), qr.getName(), null));
//                    solutionNode.addChild(qrNode);
//
//                    for (Technology tech : qr.getTechs()) {
//                        TreeNode<IoTDomain> techNode = new TreeNode<>(new IoTDomain(tech.getId(), tech.getDescription(), null));
//                        qrNode.addChild(techNode);
//                    }
//                }
//            }
//        }
//
//
//        return rootNode;
//    }
}
