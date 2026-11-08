package com.cistec.emed.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "application.storage")
public interface StorageConfig {

    @WithName("base-directory")
    String getBaseDirectory();
}