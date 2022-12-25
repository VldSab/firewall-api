var CHART = (function(){

    var _args = {}; // private
    var dataArray = [4, 2, 6, 5, 1, 7, 6]
    return {
        init : function(Args) {
            _args = Args;
            // some other initialising
        },
        helloWorld : function() {
            alert('Hello World! -' + _args[0]);
        },
        chart: function() {
            var ctx = document.getElementById(_args[0]).getContext("2d");
            var myChart = new Chart(ctx, {
            type: "line",
            data: {
            labels: [
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday",
            ],
            datasets: [
            {
                label: "work load",
                data: dataArray,
                backgroundColor: "rgba(153,205,1,0.6)",
            },
            {
                label: "free hours",
                data: [2, 2, 5, 5, 2, 1, 10],
                backgroundColor: "rgba(155,153,10,0.6)",
            },
            ],
            },
            });
            return myChart;
        }
    };
}());