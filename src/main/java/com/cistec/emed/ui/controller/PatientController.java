package com.cistec.emed.ui.controller;

import com.cistec.emed.model.PatientInfo;
import com.cistec.emed.service.PatientService;
import io.quarkiverse.renarde.Controller;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

import java.io.IOException;
import java.util.List;

public class PatientController extends Controller {

    @Inject
    PatientService patientService;

    @CheckedTemplate(basePath = "patient")
    public static class Templates {
        public static native TemplateInstance search(List<PatientInfo> patients);
    }

    @Path("/search")
    public TemplateInstance search() throws IOException {
        List<PatientInfo> patients = patientService.listPatients();
        return Templates.search(patients);
    }

}
