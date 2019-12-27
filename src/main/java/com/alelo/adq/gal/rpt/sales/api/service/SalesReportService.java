package com.alelo.adq.gal.rpt.sales.api.service;

import com.alelo.adq.gal.rpt.sales.api.config.SalesReportConfig;
import com.alelo.adq.gal.rpt.sales.api.constant.AppConst;
import com.alelo.adq.gal.rpt.sales.api.model.dto.SalesReportDto;
import com.alelo.adq.gal.rpt.sales.api.repository.JDBCOracleDatasetSalesReport;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@EnableConfigurationProperties
@ConfigurationProperties
public class SalesReportService {

    @Autowired
    SalesReportConfig config;

    @Autowired
    private SparkSession sparkSession;

    @Autowired
    private JDBCOracleDatasetSalesReport salesRepository;

    private Dataset<Row> collectSalesData() {
        return salesRepository.getAnalyticalSalesReportByDay(sparkSession);
    }

    private String getFileName(String extention) {
        LocalDate targetDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppConst.DATETIME_FORMATTER);
        return String.format("%s-%s.%s", config.getFileName(), targetDate.format(formatter), extention);
    }

    private void saveJsonToS3Bucket(Dataset<Row> dataframe) {
        String targetURL = String.format("%s/%s/%s", AppConst.S3A_URL, config.getBucket(), getFileName(AppConst.JSON_FORMAT));
        dataframe.coalesce(1).write().mode(SaveMode.Append).format(AppConst.JSON_FORMAT).save(targetURL);
    }

    private void saveCsvToS3Bucket(Dataset<Row> dataframe) {
        String targetURL = String.format("%s/%s/%s", AppConst.S3A_URL, config.getBucket(), getFileName(AppConst.SCV_FORMAT));
        dataframe.coalesce(1).write().mode(SaveMode.Append).format(AppConst.SCV_FORMAT).save(targetURL);
    }

    public SalesReportDto getJsonAnalyticalSalesReport() {
        Dataset<Row> dataset = this.collectSalesData();
        dataset.show();
        this.saveJsonToS3Bucket(dataset);
        return SalesReportDto.builder()
                .reportName(this.getFileName(AppConst.JSON_FORMAT))
                .url("")
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern(AppConst.DATETIME_FORMATTER)))
                .build();
    }

    public SalesReportDto getCsvAnalyticalSalesReport() {
        Dataset<Row> dataset = this.collectSalesData();
        dataset.show();
        this.saveCsvToS3Bucket(dataset);
        return SalesReportDto.builder()
                .reportName(this.getFileName(AppConst.JSON_FORMAT))
                .url("")
                .dateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern(AppConst.DATETIME_FORMATTER)))
                .build();
    }
}