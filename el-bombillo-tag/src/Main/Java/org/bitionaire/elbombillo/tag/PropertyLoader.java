package org.bitionaire.elbombillo.tag;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {
    final private String propertiesPath = "src/Main/Config/tag.properties";
    final private Properties props = new Properties();
    private FileInputStream fis;
    @Getter private final String databaseAdapter;
    @Getter private final String databaseHost;

    public PropertyLoader() {
        try {
            this.fis = new FileInputStream(propertiesPath);
            props.load(fis);
        } catch (IOException e) {
            ElBombilloTag.logger.error("No config file found", e);
        }
        databaseAdapter = props.getProperty("database.adapter");
        databaseHost = props.getProperty("database.host");
    }
}
