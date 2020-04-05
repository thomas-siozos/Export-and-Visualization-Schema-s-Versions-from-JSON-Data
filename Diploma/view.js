var objectConstructor = ({}).constructor;
google.charts.load('current', {packages:["orgchart"]});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
  var data = new google.visualization.DataTable();
  data.addColumn('string', 'Field');
  data.addColumn('string', 'Father');
  data.addRows([['root', '']]);
  for (var key in data_0) {
    if (data_0.hasOwnProperty(key)) {
      data.addRows([[key + " : " + data_0[key], 'root']]);
      if (data_0[key].constructor == objectConstructor) {
        var nested = data_0[key];
        for (var nested_key in nested) {
          if (nested.hasOwnProperty(nested_key)) {
            data.addRows([[nested_key + " : " + nested[nested_key], key + " : " + data_0[key]]]);
          }
        }
      }
    }
  }
  var chart = new google.visualization.OrgChart(document.getElementById('chart_div'));
  chart.draw(data, {'allowHtml' : true})
}

/*
for (var i = 0; i <= data_counter; i++) {
  for (var key in window["data_" + i]) {
      if (window["data_" + i].hasOwnProperty(key)) {
          console.log(key + " -> " + window["data_" + i][key]);
          if (window["data_" + i].constructor == objectConstructor) {
            console.log(key);
          }
      }
  }
}
*/
