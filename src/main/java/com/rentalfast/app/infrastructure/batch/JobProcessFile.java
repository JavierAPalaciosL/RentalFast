package com.rentalfast.app.infrastructure.batch;

import com.rentalfast.app.domain.utils.CsvReaderManual;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@Configuration
@EnableBatchProcessing
@DependsOn("dataSource")
public class JobProcessFile {

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager, DataSource dataSource) throws IOException {
        return new StepBuilder("step", jobRepository)
                .<List<String>, String[]> chunk(5, transactionManager).
                reader(new ItemReader<List<String>>() {

                    List<List<String>> datos = CsvReaderManual.leerCsv("src/main/resources/data/cars_us_2022.csv");
                    int index = 0;

                    @Override
                    public List<String> read() {
                        if (index < datos.size()) {
                            return datos.get(index++);
                        }
                        return null;
                    }

                }).
                processor(new ItemProcessor<List<String>, String[]>() {
                    int i=0;
                    int counter = 0;

                    @Override
                    public String[] process(List<String> item) throws Exception {
                        List<String> fila = (List<String>) item;
                        String[] data = new String[19];

                        for(i = 0; i < fila.size(); i++){
                            if((i >= 0 && i<=9) || (i>=11 && i<=13) || (i>=16 && i<=21) || i==23){
                                if(i == 23){
                                    counter = 0;
                                    return data;
                                }
                                data[counter] = fila.get(i);

                                counter = counter + 1;

                            }
                        }

                        return null;
                    }
                }).
                writer(writer(dataSource)).

                build();
    }


    public JdbcBatchItemWriter<String[]> writer(DataSource ds) {
        // 27 columnas → 27 “?”
        String sql = "INSERT INTO entity_car (" +
                "tuition, car_color, created_date, descriptio_car, hot_price, image_src," +
                " name_car, price_per_day, price_per_hour, price_per_month, price_per_week," +
                " price_per_year, airaconditioner, body_type, brand, cylinders, drive_train," +
                " engine_type, fuel_type, gear_box_type, height, hp, lenght, seats," +
                " torque_lbft, wheel_base, width" +
                ") VALUES (" +
                "?, 'BLUE', '2025-05-12 18:44:00.000000', 'car', ?, ?, ?, 0, 0, 0, 0, 0," +
                " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
                ")";

        return new JdbcBatchItemWriterBuilder<String[]>()
                .dataSource(ds)
                .sql(sql)
                .itemPreparedStatementSetter((item, ps) -> {
                    // corresponder cada “?” con item[0]…item[?]
                    for (int i = 0; i < item.length; i++) {
                        ps.setString(i+1, item[i]);
                    }
                })
                .build();
    }


    @Bean
    public Job jobFile(JobRepository jobRepository, Step step) {

        return new JobBuilder("jobFile", jobRepository)
                .start(step)
                .build();
    }


}
