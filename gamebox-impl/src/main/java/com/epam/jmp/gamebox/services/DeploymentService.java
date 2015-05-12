package com.epam.jmp.gamebox.services;

import com.epam.jmp.gamebox.deploy.DeploymentDescriptor;

import java.util.List;

public interface DeploymentService {
    List<DeploymentDescriptor> getAllDeployments();
}
