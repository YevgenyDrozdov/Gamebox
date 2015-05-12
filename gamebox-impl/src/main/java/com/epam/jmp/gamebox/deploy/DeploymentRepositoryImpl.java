package com.epam.jmp.gamebox.deploy;

import com.epam.jmp.gamebox.deploy.DeploymentRepository;
import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;

import java.util.ArrayList;
import java.util.List;

public class DeploymentRepositoryImpl implements DeploymentRepository {

    private List<DeploymentDescriptor> deploymentDescriptors;

    public DeploymentRepositoryImpl() {
        deploymentDescriptors = new ArrayList<>();
    }


    @Override
    public void addDeployment(DeploymentDescriptor deploymentDescriptor) {
        deploymentDescriptors.add(deploymentDescriptor);
    }

    @Override
    public List<DeploymentDescriptor> getAllDeployments() {
        return deploymentDescriptors;
    }

}
