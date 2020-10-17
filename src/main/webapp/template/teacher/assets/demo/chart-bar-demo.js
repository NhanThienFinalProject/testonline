/* global Chart */

// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';
var falseScore = document.getElementById("falseScore").value;
var D = document.getElementById("D").value;
var C = document.getElementById("C").value;
var B = document.getElementById("B").value;
var A = document.getElementById("A").value;
var Aplus = document.getElementById("Aplus").value;

var ctx = document.getElementById("myBarChart");
var myLineChart = new Chart(ctx, {
  type: 'bar',
  data: {
    labels: ["[0,5)", "[5,6)", "[6,7)", "[7,8)", "[8,9)", "[9,10]"],
    datasets: [{
      label: "Amount",
      backgroundColor: "rgba(2,117,216,1)",
      borderColor: "rgba(2,117,216,1)",
      data: [falseScore, D, C, B, A, Aplus],
    }],
  },
  options: {
    scales: {
      xAxes: [{
        time: {
          unit: 'mark'
        },
        gridLines: {
          display: false
        },
        ticks: {
          maxTicksLimit: 6
        }
      }],
      yAxes: [{
      stacked: true,
      gridLines: {
        display: true,
        color: "rgba(255,99,132,0.2)"
      }
    }],
    },
    legend: {
      display: false
    }
  }
});
