package com.alelo.adq.gal.rpt.sales.api.controller;

import com.alelo.adq.gal.rpt.sales.api.model.SalesReportDto;
import com.alelo.adq.gal.rpt.sales.api.service.SalesReportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SalesReportController {

    @Autowired
    SalesReportService service;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200 Ok Return Analytical Sales Report using Json Format."),
            @ApiResponse(code = 403, message = "403 Forbidden."),
            @ApiResponse(code = 500, message = "500 Internal Server Error "),
    })
    @ApiOperation(value = "Sales Report Json Format")
    @RequestMapping(value = "/report/sales/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SalesReportDto getJsonAnalyticalSalesReport() {
        return service.getJsonAnalyticalSalesReport();
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "200 Ok Return Analytical Sales Report using CSV Format."),
            @ApiResponse(code = 403, message = "403 Forbidden."),
            @ApiResponse(code = 500, message = "500 Internal Server Error "),
    })
    @ApiOperation(value = "Sales Report CSV Format")
    @RequestMapping(value = "/report/sales/csv", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SalesReportDto getCsvAnalyticalSalesReport() {
        return service.getCsvAnalyticalSalesReport();
    }
}
