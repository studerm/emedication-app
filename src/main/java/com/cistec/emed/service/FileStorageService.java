package com.cistec.emed.service;

import com.cistec.emed.config.StorageConfig;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@ApplicationScoped
public class FileStorageService {

    @Inject
    StorageConfig config;

    // FIXME: dummy method returning only the file names
    public List<String> loadEmedDocuments(String pid) throws IOException {
        Path patientDirectory = Paths.get(config.getBaseDirectory(), pid.toUpperCase());
        if (!Files.exists(patientDirectory) || !Files.isDirectory(patientDirectory)) {
            Log.infov("PID={}: storage directory missing", pid);
            return Collections.emptyList();
        }

        try (Stream<Path> stream = Files.list(patientDirectory)) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .toList();
        }
    }
}
