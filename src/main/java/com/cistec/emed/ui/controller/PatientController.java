package com.cistec.emed.ui.controller;

import com.cistec.emed.model.PatientInfo;
import com.cistec.emed.service.FileStorageService;
import com.cistec.emed.service.PatientService;
import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestQuery;

import java.io.IOException;
import java.util.List;

public class PatientController extends Controller {

    @Inject
    PatientService patientService;

    @Inject
    FileStorageService storageService;

    @CheckedTemplate(basePath = "patient")
    public static class Templates {
        public static native TemplateInstance search(List<PatientInfo> patients);
        public static native TemplateInstance medicationList(PatientInfo patient, List<String> documents);
    }

    @Path("/search")
    public TemplateInstance search() {
        List<PatientInfo> patients = patientService.listPatients();
        return Templates.search(patients);
    }

    @Path("/medication/list")
    public TemplateInstance medicationList(@RestQuery String pid) throws IOException {
        // TODO: validation of param

        PatientInfo patientInfo = patientService.getPatient(pid);
        List<String> emedDocs = storageService.loadEmedDocuments(pid);
        return Templates.medicationList(patientInfo, emedDocs);
    }

}
