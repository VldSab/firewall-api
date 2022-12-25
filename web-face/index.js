const dataPie = {
  type: "pie",
  data: {
    labels: ["Monday", "Tuesday", "Wednesday", "Thursday"],
    datasets: [{
      data: [1234, 2234, 3234, 4234],
      backgroundColor: ["rgba(117,169,255,0.6)", "rgba(148,223,215,0.6)",
        "rgba(208,129,222,0.6)", "rgba(247,127,167,0.6)"
      ],
    }, ],
  },
};

new mdb.Chart(document.getElementById("chart-pie"), dataPie);