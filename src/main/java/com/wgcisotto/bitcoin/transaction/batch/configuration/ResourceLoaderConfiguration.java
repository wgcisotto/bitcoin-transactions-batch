package com.wgcisotto.bitcoin.transaction.batch.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class ResourceLoaderConfiguration {

    public static final String FILE = "file:";
    @Autowired
    ResourceLoader resourceLoader;

    @Value("${transaction.files.location}")
    private String pathLocation;

    @Value("${transaction.files.pattern}")
    private String patternName;

    @Bean
    public Resource[] loadResources(){
        File folder = new File(pathLocation);
        File[] listOfFiles = folder.listFiles();
        List<Resource> resources = new ArrayList<>();
        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().matches(patternName)) {
                Resource resource = resourceLoader.getResource(String.format("%s%s", FILE, file.getAbsolutePath()));
                resources.add(resource);
            }
        }
        return resources.toArray(Resource[]::new);
    }


}
