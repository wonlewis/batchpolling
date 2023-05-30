package com.lewiswon.batchpolling

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableBatchProcessing
class BatchpollingApplication

fun main(args: Array<String>) {
    runApplication<BatchpollingApplication>(*args)
}
