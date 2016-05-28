var drawFailReportGraphs = function ()
{
    drawFailReportGlobalBarChart();
    totalWorkingCameras();
    totalFailingCameras();

    drawFailReportEntityBarChart();
    setEntities();

    drawFailReportBuildingBarChart();
    setBuildings();

    drawFailStatisticsGlobalBarChart();
    setFailReasons()

    return true;
};

var failReportGlobalBarChart;

function drawFailReportGlobalBarChart() {
    failReportGlobalBarChart = new Highcharts.Chart({
            chart: {
                type: 'column',
                renderTo: 'failReportGlobalBarChart'
            },
            title: {
                text: 'Camera Failures'
            },
            xAxis: {
                categories: ['Working', 'Failing'],
                title: {
                    text: "Status"
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Number of Cameras',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' cameras'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            credits: {
                enabled: false
            },
            legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'top',
                        x: -30,
                        y: 500,
                        floating: true,
                        borderWidth: 0,
                        backgroundColor: 'transparent',
                        shadow: false
                    },
            series: [{
                name: " ",
                data: [{
                          y:0,
                          color: '#99ff99'
                       },
                       {
                          y:0,
                          color: '#ff8080'
                       }]
            }]
})
};

function totalFailingCameras() {
                          $.ajax({
                              type:"GET",
                              url: "/totalFailingCameras",
                              dataType : 'json',
                              success: function(response){
                                failReportGlobalBarChart.series[0].data[1].update(response.failing);
                              }
                          });

                          };

function totalWorkingCameras() {
                         $.ajax({
                              type:"GET",
                              url: "/totalWorkingCameras",
                              dataType : 'json',
                              success: function(response){
                                failReportGlobalBarChart.series[0].data[0].update(response.working);
                              }
                          });

                          };


var failReportEntityBarChart;

function drawFailReportEntityBarChart() {
    failReportEntityBarChart = new Highcharts.Chart({
            chart: {
                type: 'bar',
                renderTo: 'failReportEntityBarChart'
            },
            title: {
                text: 'Camera Failures By Entity'
            },
            xAxis: {
                categories: [],
                title: {
                    text: "Entities"
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Number of Cameras',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' cameras'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            credits: {
                enabled: false
            },
            colors: ['#99ff99', '#ff8080'],
            legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'top',
                        x: -20,
                        y: 70,
                        floating: true,
                        borderWidth: 1,
                        backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
                        shadow: true
                    },
            series: [{
                            name: "Working",
                            data: []
                        },
                        {
                            name: "Failing",
                            data: []
                        }]
})
};

function setEntities(){
                        var orgId = 1;
                        $.ajax({
                            type:"GET",
                            url: "/entities/names/" + orgId,
                            dataType : 'json',
                            success: function(entities){
                                failReportEntityBarChart.xAxis[0].setCategories(entities);

                                $.ajax({
                                    type:"GET",
                                    url: "/cameras/entities/cameraStatuses",
                                    dataType : "json",
                                    contentType : "application/json",
                                    data: {entities: entities.toString()},
                                    success: function(result)
                                    {
                                        for(i=0; i<entities.length; i++)
                                        {
                                            failReportEntityBarChart.series[0].addPoint(result.response[0][entities[i]]);
                                            failReportEntityBarChart.series[1].addPoint(result.response[1][entities[i]]);
                                        }
                                    }
                                });
                            }
                        })
};


var failReportBuildingBarChart;

function drawFailReportBuildingBarChart() {
    failReportBuildingBarChart = new Highcharts.Chart({
            chart: {
                type: 'bar',
                renderTo: 'failReportBuildingBarChart'
            },
            title: {
                text: 'Camera Failures By Building'
            },
            xAxis: {
                categories: [],
                title: {
                    text: "Buildings"
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Number of Cameras',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' cameras'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            credits: {
                enabled: false
            },
            colors: ['#99ff99', '#ff8080'],
            legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'top',
                        x: -15,
                        y: 80,
                        floating: true,
                        borderWidth: 1,
                        backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
                        shadow: true
                    },
            series: [{
                name: "Working",
                data: []
            },
            {
                name: "Failing",
                data: []
            }]
})
};

function setBuildings(){
                        var orgId = 1;
                          $.ajax({
                              type:"GET",
                              url: "/buildings/names/" + orgId,
                              dataType : 'json',
                              success: function(buildings){
                                failReportBuildingBarChart.xAxis[0].setCategories(buildings);

                                $.ajax({
                                    type:"GET",
                                    url: "/cameras/buildings/cameraStatuses",
                                    dataType : "json",
                                    contentType : "application/json",
                                    data: {buildings: buildings.toString()},
                                    success: function(result)
                                    {
                                        for(i=0; i<buildings.length; i++)
                                        {
                                            failReportBuildingBarChart.series[0].addPoint(result.response[0][buildings[i]]);
                                            failReportBuildingBarChart.series[1].addPoint(result.response[1][buildings[i]]);
                                        }
                                    }
                                });
                              }
                          });
};




var drawFailStatisticGraphs = function () {
    drawFailStatisticsGlobalBarChart();
    setFailReasons()
    return true;
};

var failStatisticsGlobalPieChart;

function drawFailStatisticsGlobalBarChart() {
    failStatisticsGlobalPieChart = new Highcharts.Chart({
            chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie',
                        renderTo: 'failStatisticsGlobalPieChart'
                    },
                    title: {
                        text: 'Reasons for Camera Failures'
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                                style: {
                                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                }
                            }
                        }
                    },
                    series: [{
                        name: 'Reason',
                        colorByPoint: true,
                        data: [
                        {
                            name: 'Water Damage',
                            y: 0
                        },
                        {
                            name: 'Blurred Image',
                            y: 0
                        },
                        {
                            name: 'Broken Camera',
                            y: 0
                        },
                        {
                            name: 'Bad Batteries',
                            y: 0
                        },
                        {
                            name: 'Broken Zoom Lens',
                            y: 0
                        },
                        {
                            name: 'Sensor Malfunction',
                            y: 0
                        },
                        {
                            name: 'LCD Cracked',
                            y: 0
                        },
                        {
                            name: 'Software Issue',
                            y: 0
                        },
                        {
                            name: 'Bad Image quality',
                            y: 0
                        },
                        {
                            name: 'Lens Obstructed',
                            y: 0
                        },
                        {
                            name: 'Night Light Issue',
                            y: 0
                        },
                        {
                            name: 'Corrupted Image',
                            y: 0
                        },
                        {
                            name: 'Other',
                            y: 0
                        }
                    ]
                    }]
})
};

function setFailReasons() {
                          $.ajax({
                              type:"GET",
                              url: "/cameras/failReasonCounts/" + 1,
                              dataType : 'json',
                              success: function(failReasons){
                                var total = failReasons.WaterDamage + failReasons.BlurredImage + failReasons.BrokenCamera
                                + failReasons.BadBatteries + failReasons.BrokenZoomLens + failReasons.SensorMalfunction
                                + failReasons.LCDCracked + failReasons.SoftwareIssue + failReasons.BadImageQuality
                                + failReasons.LensObstructed + failReasons.NightLightIssue + failReasons.CorruptedImage
                                + failReasons.Other;

                                var divisor =  total/100;

                                failStatisticsGlobalPieChart.series[0].data[0].update(failReasons.WaterDamage * divisor);
                                failStatisticsGlobalPieChart.series[0].data[1].update(failReasons.BlurredImage * divisor);
                                failStatisticsGlobalPieChart.series[0].data[2].update(failReasons.BrokenCamera * divisor);
                                failStatisticsGlobalPieChart.series[0].data[3].update(failReasons.BadBatteries * divisor);
                                failStatisticsGlobalPieChart.series[0].data[4].update(failReasons.BrokenZoomLens * divisor);
                                failStatisticsGlobalPieChart.series[0].data[5].update(failReasons.SensorMalfunction * divisor);
                                failStatisticsGlobalPieChart.series[0].data[6].update(failReasons.LCDCracked * divisor);
                                failStatisticsGlobalPieChart.series[0].data[7].update(failReasons.SoftwareIssue * divisor);
                                failStatisticsGlobalPieChart.series[0].data[8].update(failReasons.BadImageQuality * divisor);
                                failStatisticsGlobalPieChart.series[0].data[9].update(failReasons.LensObstructed * divisor);
                                failStatisticsGlobalPieChart.series[0].data[10].update(failReasons.NightLightIssue * divisor);
                                failStatisticsGlobalPieChart.series[0].data[11].update(failReasons.CorruptedImage * divisor);
                                failStatisticsGlobalPieChart.series[0].data[12].update(failReasons.Other * divisor);

                                for (i =0; i < failStatisticsGlobalPieChart.series[0].data.length; i++)
                                {
                                    while(i < failStatisticsGlobalPieChart.series[0].data.length && failStatisticsGlobalPieChart.series[0].data[i].y == 0)
                                    {
                                        failStatisticsGlobalPieChart.series[0].removePoint(i);
                                    }
                                }
                              }
                          });

                          };


