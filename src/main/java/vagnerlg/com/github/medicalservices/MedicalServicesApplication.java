package vagnerlg.com.github.medicalservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;

import java.util.Locale;

@SpringBootApplication(exclude = {
    JmxAutoConfiguration.class,
})
public class MedicalServicesApplication {

    public static void main(String[] args) {
        Locale.setDefault(new Locale ("pt", "BR"));
        SpringApplication.run(MedicalServicesApplication.class, args);
    }
}
