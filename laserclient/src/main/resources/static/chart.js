var chartDom = document.getElementById('ajaxChart');
var myChart = echarts.init(chartDom);
var option;
var chartdata;
//显示加载


myChart.showLoading();



$.ajax({
    url:"http://localhost:8080/api/example",
    async:false,
    cache:false,

    success:function (data){
        // console.info(data)
        chartdata = []
        data.map(function (x){
            chartdata.push(x)
        })
        // console.log(chartdata)
        option = {
            xAxis: {},
            yAxis: {},
            series: [
                {
                    symbolSize: 5,
                    data: chartdata,
                    type: 'scatter'
                }
            ]
        };
        //隐藏加载
        myChart.hideLoading();
        myChart.setOption(option);
    },
    error:function (error){
        console.log(error)
    }

})
