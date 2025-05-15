package org.example.alphasolutions.services;

import org.example.alphasolutions.models.Subproject;
import org.example.alphasolutions.repositories.SubprojectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubprojectService {

    private final SubprojectRepository subprojectRepository;

    public SubprojectService(SubprojectRepository subprojectRepository) {
        this.subprojectRepository = subprojectRepository;
    }

    public void addSubproject(Subproject subproject) {
        subprojectRepository.addSubproject(subproject);
    }

    public void deleteSubproject(int subprojectId) {
        subprojectRepository.deleteSubproject(subprojectId);
    }

    public void editSubproject(int subprojectId, Subproject subproject) {
        subprojectRepository.editSubproject(subprojectId, subproject);
    }

    public List<Subproject> getSubprojectsByProjectId(int projectId) {
        return subprojectRepository.getSubsByProjectId(projectId);
    }

    public Subproject getSubprojectBySubId(int subprojectId) {
        return subprojectRepository.getSubprojectBySubId(subprojectId);
    }


}
