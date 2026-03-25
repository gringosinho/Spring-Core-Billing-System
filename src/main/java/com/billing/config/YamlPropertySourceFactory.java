package com.billing.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.util.Properties;

/**
 * Custom factory to allow @PropertySource to read .yml files.
 * Spring Core natively only understands .properties files. By implementing
 * PropertySourceFactory, we hook into Spring's environment loading phase
 * and translate the YAML structure into flat Java Properties.
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) {
        // 1. Create Spring's built-in YAML to Properties converter
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();

        // 2. Feed it our application.yml file
        factory.setResources(encodedResource.getResource());

        // 3. Command it to parse the YAML into a standard Java Properties object
        Properties properties = factory.getObject();

        // 4. Return it to the Spring Environment with a fallback name
        String sourceName = (name != null) ? name : encodedResource.getResource().getFilename();
        return new PropertiesPropertySource(sourceName, properties);
    }
}
