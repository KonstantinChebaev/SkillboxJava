package ru.skillbox.loanrequest.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties("storage")
@EnableConfigurationProperties(StorageProperties.class)
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private final String LOCATION = "upload-dir";

    public String getLocation() {
        return LOCATION;
    }

    public void setLocation(String location) {
        System.out.println("You can't change location");
    }


}