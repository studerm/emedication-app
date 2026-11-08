package com.cistec.emed.service;

import com.cistec.emed.model.PatientInfo;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

// FIXME: dummy implementation offering a fixed set of test patients
@ApplicationScoped
public class PatientService {

    private static final List<PatientInfo> TEST_PATIENTS = List.of(
            new PatientInfo("T001", "Gregori", "Benno"),
            new PatientInfo("T007", "Bond", "James")
    );

    public List<PatientInfo> listPatients() {
        return TEST_PATIENTS;
    }

    // TODO: add method to find a specific patient

}
