package com.example.producuploadfile;

import com.example.producuploadfile.service.CategoryService;
import com.example.producuploadfile.service.CategoryServiceImpl;
import com.example.producuploadfile.service.ProductService;
import com.example.producuploadfile.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ProducUploadFileApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(ProducUploadFileApplication.class, args);
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }

    @Bean
    public CategoryService categoryService() {
        return new CategoryServiceImpl();
    }

    @Autowired
    Environment environment;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String fileUpload = environment.getProperty("file_upload").toString();

        // Image resource9.
        registry.addResourceHandler("/i/**") //
                .addResourceLocations("file:" + fileUpload);
    }
}
