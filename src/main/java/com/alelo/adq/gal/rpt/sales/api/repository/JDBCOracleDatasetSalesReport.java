package com.alelo.adq.gal.rpt.sales.api.repository;

import com.alelo.adq.gal.rpt.sales.api.constant.AppConst;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Component;

@Component
public class JDBCOracleDatasetSalesReport {


    public Dataset<Row> getAnalyticalSalesReportByDay(SparkSession spark) {

        return spark.read()
                .format(AppConst.DATAFRAME_TARGET_FORMAT)
                .option(AppConst.KEY_URL, "")
                .option(AppConst.KEY_DRIVER, "")
                .option(AppConst.KEY_QUERY, "")
                .option(AppConst.KEY_USER, "")
                .option(AppConst.KEY_PWD, "")
                .load();
    }

}
