/* global Chart */

// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

var ctx = document.getElementById("myBarChart");
var myLineChart = new Chart(ctx, {
  type: 'bar',
  data: {
    labels: ["Mark > 3", "3 <= Mark <= 4", "5 <= Mark <= 6", "7 <= Mark <= 8", "7 <= Mark <= 8", "8 < Mark"],
    datasets: [{
      label: "Amount",
      backgroundColor: "rgba(2,117,216,1)",
      borderColor: "rgba(2,117,216,1)",
      data: [1234, 2345, 3456, 7841, 1503, 13456],
    }],
  },
  options: {
    scales: {
      xAxes: [{
        time: {
          unit: 'mark'
        },
        gridLines: {
          display: true
        },
        ticks: {
          maxTicksLimit: 6
        }
      }],
      yAxes: [{
        ticks: {
          min: 0,
          max: 15000,
          maxTicksLimit: 5
        },
        gridLines: {
          display: true
        }
      }],
    },
    legend: {
      display: false
    }
  }
});
