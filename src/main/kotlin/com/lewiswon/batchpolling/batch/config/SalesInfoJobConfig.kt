package com.lewiswon.batchpolling.batch.config

import com.lewiswon.batchpolling.batch.dto.SalesInfoDTO
import com.lewiswon.batchpolling.domain.SalesInfo
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.database.JpaItemWriter
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder
import org.springframework.batch.item.file.FlatFileItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import javax.net.ssl.KeyManagerFactory

@Configuration
class SalesInfoJobConfig {

    @Autowired
    private lateinit var jobRepository: JobRepository

    @Autowired
    private lateinit var jobBuilder : JobBuilder

    @Autowired
    private lateinit var stepBuilder : StepBuilder

    @Autowired
    private lateinit var entityManagerFactory: EntityManagerFactory

    @Bean
    fun importSalesInfo() : Job {
        return jobBuilder.repository(jobRepository)
                .get("importSalesInfo")
                .incrementer(RunIdIncrementer())
                .start(fromFileIntoDatabase())
                .build()
    }

    @Bean
    fun fromFileIntoDatabase() : Step {
        return stepBuilder.repository(jobRepository).get("fromFileIntoDatabase")
                .<SalesInfoDTO,SalesInfo>chunk(10)
        .reader(salesInfoFileReader())
                .processor(salesInfoItemProcessor())
                .writer(salesInfoItemWriter())
                .build()
    }

    @Bean
    fun salesInfoFileReader() : FlatFileItemReader<SalesInfoDTO> {
        return FlatFileItemReaderBuilder<SalesInfoDTO>()
                .resource(ClassPathResource("/data/PAsocal-Store.csv"))
                .name("salesInfoFileReader")
                .delimited()
                .delimiter(",")
                .names({"product";"seller";"sellerId";"price";"city";"category"}.toString())
                .linesToSkip(1)
                .linesToSkip(1)
                .targetType(SalesInfoDTO::class.java)
                .build()

    }

    @Bean
    fun salesInfoItemWriter() : JpaItemWriter<SalesInfo> {
        return JpaItemWriterBuilder<SalesInfo>()
                .entityManagerFactory(entityManagerFactory)
                .build()
    }

}