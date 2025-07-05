package com.rentalfast.app.adapters.rest;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Batch {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @PostMapping("/run-job") // Define la URL para lanzar el job
    public String runJob() {
        try {
            // Se usan parámetros únicos para permitir que el job se ejecute más de una vez
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();

            jobLauncher.run(job, jobParameters);
            return "Batch job ha sido invocado";
        } catch (Exception e) {
            return "Error al invocar el job: " + e.getMessage();
        }
    }

}
