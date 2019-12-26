package com.alelo.adq.gal.rpt.sales.api.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesReportDto {
    private String reportName;
    private String url;
    private String dateTime;
}
