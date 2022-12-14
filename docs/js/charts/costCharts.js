import {gradientChartColor} from "./colors.js";

new Chart(
    document.getElementById('costsOfFixingVsPreventingBugsChartLogarithmic'), {
      type: 'line',
      data: {
        labels: [
          'Unit Testing/Test-Driven Development',
          "Full Build",
          "Integration Testing",
          "System Testing"],
        datasets: [{
          label: "Requirements/Architectural Design",
          data: [5, 50, 500, 5000],
          borderWidth: 1,
          tension: 0.25,
          borderColor: gradientChartColor(0, 0.3, 0.8, 1),
          backgroundColor: gradientChartColor(0, 0.3, 0.8, 0.2),
          fill: true
        }
        ],
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            display: false,
          },
          title: {
            display: true,
            text: 'Costs by Software Testing Phase where Bugs are found',
            color: "#FFFFFF",
          }
        },
        scales: {
          x: {
            display: true,
            ticks: {
              display: true,
              color: "#FFFFFF"
            },
            title: {
              display: true,
              text: '- Repair stage -',
              color: "#AAAAAA",
            },
          },
          y: {
            min: 0,
            display: true,
            type: "logarithmic",
            ticks: {
              display: true,
              color: "#FFFFFF",
            },
            title: {
              display: true,
              text: '- Estimated Cost per Bug in USD -',
              color: "#AAAAAA"
            },
          }
        }
      }
    })

new Chart(document.getElementById('relativeCostsToRepairDefectsWhenFoundChart'),
    {
      type: 'line',
      data: {
        labels: ['Analysis/Architectural Design',
          'Coding/Unit Test',
          'Integration and Component',
          'Beta Test Programms',
          'Post product Release'],
        datasets: [{
          label: "Requirements/Architectural Design",
          data: [1, 5, 10, 15, 30],
          borderWidth: 1,
          tension: 0.25,
          borderColor: gradientChartColor(0.033, 0.166, 0.5, 1),
          backgroundColor: gradientChartColor(0.033, 0.166, 0.5, 0.2),
          fill: true
        }
        ],
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            display: false,
          },
          title: {
            display: true,
            text: 'Relative Cost to Repair Defects When Found at Different Stages of Software Development',
            color: "#FFFFFF",
          },
          subtitle: {
            display: true,
            text: "X is a normalized unit of cost and can be expressed terms of person-hours, dollars, etc.",
            color: "#DDDDDD"
          }
        },
        scales: {
          x: {
            display: true,
            ticks: {
              display: true,
              color: "#FFFFFF"
            },
            title: {
              display: true,
              text: '- Repair stage -',
              color: "#AAAAAA",
            },
          },
          y: {
            display: true,
            type: "linear",
            ticks: {
              display: true,
              color: "#FFFFFF",
              callback: (value) => value + "x"
            },
            title: {
              display: true,
              text: '- normalized unit of costs -',
              color: "#AAAAAA"
            },
          }
        }
      }
    })

new Chart(document.getElementById('costsOverTimeChartLogarithmic'), {
  type: 'line',
  data: {
    labels: ['Analysis/Architectural Design',
      'Coding/Unit Test',
      'Integration and Component',
      'Beta Test Programms',
      'Post product Release'],
    datasets: [{
      label: "Requirements/Architectural Design",
      data: [1, 5, 10, 15, 30],
      borderWidth: 1,
      tension: 0.25,
      // borderColor: gradientChartColor(0, 0.25, 1),
      borderColor: "rgba(0, 255, 0, 1)",
      backgroundColor: gradientChartColor(0.033, 0.166, 0.5, 0.1),
      fill: true
    },
      {
        label: "Coding/Unit Test",
        data: [null, 1, 10, 20, 30],
        borderWidth: 1,
        tension: 0.25,
        // borderColor: gradientChartColor(0, 0.25, 1),
        borderColor: "rgba(255, 255, 0, 1)",
        backgroundColor: gradientChartColor(0, 0.033, 0.166, 0.1),
        fill: true
      },
      {
        label: "Integration and Component",
        data: [null, null, 1, 10, 20],
        borderWidth: 1,
        tension: 0.25,
        // borderColor: gradientChartColor(0, 0.25, 1),
        borderColor: "rgba(255, 0, 0, 1)",
        backgroundColor: gradientChartColor(0, 0, 0.033, 0.1),
        fill: true
      }],
  },
  options: {
    responsive: true,
    plugins: {
      legend: {
        display: 'true',
        labels: {
          color: "#AAAAAA"
        }
      },
      title: {
        display: true,
        text: 'Estimates of Relative Cost Factors of Correcting Errors as a Function of\n'
            + 'Where Errors Are Introduced and Found (Example Only)',
        color: "#FFFFFF",
      },
      subtitle: {
        display: true,
        text: "X is a normalized unit of cost and can be expressed terms of person-hours, dollars, etc.",
        color: "#DDDDDD"
      }

    },
    scales: {
      x: {
        display: true,
        ticks: {
          display: true,
          color: "#FFFFFF"
        },
        title: {
          display: true,
          text: '- Repair stage -',
          color: "#AAAAAA",
        },
      },
      y: {
        display: true,
        type: "linear",
        ticks: {
          display: true,
          color: "#FFFFFF",
          callback: (value) => value + "x"
        },
        title: {
          display: true,
          text: '- normalized unit of costs -',
          color: "#AAAAAA"
        }
      }
    }
  }
})
