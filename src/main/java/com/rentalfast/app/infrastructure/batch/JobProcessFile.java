package com.rentalfast.app.infrastructure.batch;

import com.rentalfast.app.domain.utils.CsvReaderManual;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
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
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Configuration
@EnableBatchProcessing
@DependsOn("dataSource")
public class JobProcessFile {

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager, DataSource dataSource) throws IOException {
        return new StepBuilder("step", jobRepository)
                .<List<String>, List<String>> chunk(5, transactionManager).
                reader(new ItemReader<List<String>>() {

                    private final Reader in;
                    private final Iterable<CSVRecord> records;
                    private final Iterator<CSVRecord> iterator;

                    {
                        in = new FileReader("src/main/resources/data/cars_us_2022.csv");
                        records = CSVFormat.EXCEL.parse(in);
                        iterator = records.iterator();
                    }

                    @Override
                    public List<String> read() {

                        if(iterator.hasNext()){
                            CSVRecord record = iterator.next();

                            return new ArrayList<>(Arrays.asList(
                                    record.get(0), //0
                                    record.get(1), //1
                                    record.get(2), //2
                                    record.get(3), //3
                                    record.get(4), //4
                                    record.get(5), //5
                                    record.get(6), //6
                                    record.get(7), //7
                                    record.get(8), //8
                                    record.get(9), //9
                                    record.get(11), //10
                                    record.get(12), //11
                                    record.get(13), //12
                                    record.get(16), //13
                                    record.get(17), //14
                                    record.get(18), //15
                                    record.get(19), //16
                                    record.get(20), //17
                                    record.get(21), //18
                                    record.get(23) //19
                            ));
                        }
                        return null;
                    }

                }).
                processor(new ItemProcessor<List<String>, List<String>>() {
                    @Override
                    public List<String> process(List<String> item) throws Exception {
                        return item;
                    }
                }).
                writer(writer(dataSource)).

                build();
    }


    public JdbcBatchItemWriter<List<String>> writer(DataSource ds) {
        // 27 columnas → 27 “?”
        String sql = "INSERT INTO entity_car (" +
                "tuition, car_color, created_date, descriptio_car, hot_price, image_src," +
                " name_car, price_per_day, price_per_hour, price_per_month, price_per_week," +
                " price_per_year, airaconditioner, body_type, brand, cylinders, doors, drive_train," +
                " engine_type, fuel_type, gear_box_type, height, hp, lenght, seats," +
                " torque_lbft, wheel_base, width" +
                ") VALUES (" +
                "?, 'BLUE', '2025-05-12 18:44:00.000000', 'car', ?, ?, ?, 0, 0, 0, 0, 0," +
                " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
                ")";

        return new JdbcBatchItemWriterBuilder<List<String>>()
                .dataSource(ds)
                .sql(sql)
                .itemPreparedStatementSetter((item, ps) -> {
                    ps.setString(1, item.get(0));
                    ps.setString(2, item.get(3));
                    ps.setString(3, item.get(4));
                    ps.setString(4, item.get(2));
                    ps.setString(5, item.get(19));
                    ps.setString(6, item.get(6));
                    ps.setString(7, item.get(1));
                    ps.setString(8, item.get(11));
                    ps.setString(9, item.get(14));
                    ps.setString(10, item.get(12));
                    ps.setString(11, item.get(5));
                    ps.setString(12, item.get(7));
                    ps.setString(13, item.get(8));
                    ps.setString(14, item.get(15));
                    ps.setString(15, item.get(9));
                    ps.setString(16, item.get(16));
                    ps.setString(17, item.get(13));
                    ps.setString(18, item.get(10));
                    ps.setString(19, item.get(18));
                    ps.setString(20, item.get(17));
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
