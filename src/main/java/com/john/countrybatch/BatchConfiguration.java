package com.john.countrybatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.validation.BindException;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${file.input}")
    private String fileInput;

    @Bean
    public Job readCSVFilesJob() {
        return jobBuilderFactory
                .get("readCSVFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<Country, Country>chunk(10)
                .reader(reader())
                .writer(writer())
                .build();
    }

    @SuppressWarnings({"rawTypes", "unchecked"})
    @Bean
    public FlatFileItemReader reader() {
        FlatFileItemReader reader = new FlatFileItemReader();
        reader.setResource(new PathResource(fileInput));

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();

        tokenizer.setDelimiter(",");
        tokenizer.setNames("name", "region", "population");
        tokenizer.setIncludedFields(0, 1, 3);

        BeanWrapperFieldSetMapper<Country> fieldSetMapper = new BeanWrapperFieldSetMapper<>(){
            @Override
            public Country mapFieldSet(FieldSet fs) throws BindException {
                return new Country(fs.readString(0), fs.readString(1), fs.readInt(2));
            }
        };


        DefaultLineMapper mapper = new DefaultLineMapper();

        mapper.setLineTokenizer(tokenizer);
        mapper.setFieldSetMapper(fieldSetMapper);

        reader.setLinesToSkip(1);
        reader.setLineMapper(mapper);
        return reader;
    }

    @Bean
    public ConsoleItemWriter<Country> writer(){
        return new ConsoleItemWriter<Country>();
    }


}
