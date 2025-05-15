package org.example.alphasolutions.services;

import org.example.alphasolutions.models.Admin;
import org.example.alphasolutions.models.Employee;
import org.example.alphasolutions.models.ProjectManager;
import org.example.alphasolutions.repositories.CredentialsRepository;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService {

    private final CredentialsRepository credentialsRepository;

    public CredentialsService(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    public Admin checkAdminCredentials(String username, String password) {
        return credentialsRepository.checkAdminCredentials(username, password);
    }

    public Employee checkEmployeeCredentials(String username, String password) {
        return credentialsRepository.checkEmployeeCredentials(username, password);
    }

    public ProjectManager checkProjectManagerCredentials(String username, String password) {
        return credentialsRepository.checkProjectManagerCredentials(username, password);
    }


}
