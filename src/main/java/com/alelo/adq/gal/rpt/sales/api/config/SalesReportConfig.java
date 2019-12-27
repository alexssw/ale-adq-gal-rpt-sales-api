package com.alelo.adq.gal.rpt.sales.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("sales-report")
@Getter
@Setter
public class SalesReportConfig {

    @Value("${sales-report.bucket}")
    private String bucket="gal-rpt-sales";

    @Value("${sales-report.filename}")
    private String fileName="salesReport";
}
