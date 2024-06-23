package br.ufrj.cos.service;

import br.ufrj.cos.components.treeview.TreeBuilder;
import br.ufrj.cos.domain.IoTDomain;
import br.ufrj.cos.repository.IoTDomainRepository;
import br.ufrj.cos.views.home.IoTDomainRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.ufrj.cos.components.treeview.TreeNode;

import java.util.*;

@Service
public class IoTDomainService {

    private final IoTDomainRepository ioTDomainRepository;
    private final TreeBuilder treeBuilder;

    @Autowired
    public IoTDomainService(IoTDomainRepository ioTDomainRepository) {
        this.ioTDomainRepository = ioTDomainRepository;
        this.treeBuilder = new TreeBuilder();
    }

    public TreeNode<Object> getTree() {
        List<IoTDomain> domains = ioTDomainRepository.findAll();
        return treeBuilder.buildTree(domains);
    }

    public List<IoTDomainRecord> getIoTDomainCountGroupedByName() {
        return ioTDomainRepository.countIoTDomainsGroupedByName();
    }
}
