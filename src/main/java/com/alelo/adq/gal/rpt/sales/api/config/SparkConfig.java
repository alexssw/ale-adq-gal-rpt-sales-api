package com.alelo.adq.gal.rpt.sales.api.config;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SparkConfig {

    @Autowired
    private Environment env;

    @Value("${spark.application.name}")
    private String applicationName = "ale-adq-gal-rpt-sales-api";

    @Value("${spark.master}")
    private String masterUri = "local[2]";

    @Bean
    public SparkConf config() {
        return new SparkConf().setAppName(applicationName).setMaster(masterUri);
    }

    @Bean
    public JavaSparkContext javaSparkContext() {
        return new JavaSparkContext(config());
    }

    @Bean
    public SparkSession sparkSession() {
        return SparkSession
                .builder()
                .sparkContext(javaSparkContext().sc())
                .getOrCreate();
    }

}
