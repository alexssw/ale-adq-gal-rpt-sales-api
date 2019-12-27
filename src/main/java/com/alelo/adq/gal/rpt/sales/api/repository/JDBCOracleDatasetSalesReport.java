package com.alelo.adq.gal.rpt.sales.api.repository;

import com.alelo.adq.gal.rpt.sales.api.constant.AppConst;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JDBCOracleDatasetSalesReport {

    @Value("${sales-report.database.url}")
    private String url;

    @Value("${sales-report.database.driver}")
    private String driver;

    @Value("${sales-report.database.user}")
    private String user;

    @Value("$sales-report.database.password")
    private String password;

    public Dataset<Row> getAnalyticalSalesReportByDay(SparkSession spark) {
        return spark.read()
                .format(AppConst.DATAFRAME_TARGET_FORMAT)
                .option(AppConst.KEY_URL, url)
                .option(AppConst.KEY_DRIVER, driver)
                .option(AppConst.KEY_QUERY, "")
                .option(AppConst.KEY_USER, user)
                .option(AppConst.KEY_PWD, password)
                .load();
    }
}
