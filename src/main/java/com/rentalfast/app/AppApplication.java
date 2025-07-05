package com.rentalfast.app;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;


    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /*
        CREATE VIEW viewPayWithCash AS
        SELECT id, user_reference, car_reference, date_start, payment, status from entity_history_rent
        where  CURRENT_DATE >= entity_history_rent.date_start
        and    entity_history_rent.status = 'PENDING'
        and    entity_history_rent.payment = 1;
        */

        /*jobLauncher.run(
                job,
                new JobParametersBuilder()
                        .addLong("time", System.currentTimeMillis())
                        .toJobParameters()
        );*/


    }
}
