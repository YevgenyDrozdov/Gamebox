package com.epam.jmp.gamebox.impl;

import com.epam.jmp.gamebox.deploy.DeploymentRepository;
import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;
import com.epam.jmp.gamebox.services.DeploymentService;

import java.util.List;

public class DeploymentServiceImpl implements DeploymentService {

    private DeploymentRepository deploymentRepository;

    public DeploymentServiceImpl(DeploymentRepository deploymentRepository) {
        this.deploymentRepository = deploymentRepository;
    }

    @Override
    public List<DeploymentDescriptor> getAllDeployments() {
        return deploymentRepository.getAllDeployments();
    }

}
