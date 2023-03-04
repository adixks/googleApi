package pl.ksiezak.adrian.java.googleApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoogleApiApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(GoogleApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GoogleApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("The application started working.");
    }
}