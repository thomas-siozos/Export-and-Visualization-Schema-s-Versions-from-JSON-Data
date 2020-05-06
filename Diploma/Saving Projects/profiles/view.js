var objectConstructor = ({}).constructor;
google.charts.load('current', {packages:["orgchart"]});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
  var versions = new Array(data_counter);
  for (var i = 0; i <= data_counter; i++) {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Field');
    data.addColumn('string', 'Father');
    data.addRows([['root', '']]);
    setDataTable(window["data_" + i], data, "root");
    versions[i] = data;
    var newDiv = document.createElement("div");
    newDiv.style.height = "100px";
    newDiv.style.textAlign = "center";
    //newDiv.style.background = "red";
    newDiv.innerHTML = "Version " + (i + 1);
    newDiv.style.fontSize = "50px";
    document.body.appendChild(newDiv);
    var newDiv = document.createElement("div");
    newDiv.setAttribute("id", "version_" + i);
    document.body.appendChild(newDiv);
    //console.log(document.getElementById('version_' + i));
  }
  // var data = new google.visualization.DataTable();
  // data.addColumn('string', 'Field');
  // data.addColumn('string', 'Father');
  // data.addRows([['root', '']]);
  // for (var key in data_0) {
  //   if (data_0.hasOwnProperty(key)) {
  //     data.addRows([[key + " : " + data_0[key], 'root']]);
  //     if (data_0[key].constructor == objectConstructor) {
  //       var nested = data_0[key];
  //       for (var nested_key in nested) {
  //         if (nested.hasOwnProperty(nested_key)) {
  //           data.addRows([[nested_key + " : " + nested[nested_key], key + " : " + data_0[key]]]);
  //         }
  //       }
  //     }
  //   }
  // }
  for (var i = 0; i <= data_counter; i++) {
    var chart = new google.visualization.OrgChart(document.getElementById("version_" + i));
    chart.draw(versions[i], {'allowHtml' : true})
    //versios[i].setRowProperty(2, 'selectedStyle', 'background-color:#00FF00');
  }
}

function setDataTable(version, data, father) {
  var name = null;
  for (var key in version) {
    if (version.hasOwnProperty(key)) {
      name = key + " : " + version[key];
      data.addRows([[name, father]]);
      if (version[key].constructor == objectConstructor) {
        var nested = version[key];
        setDataTable(nested, data, name);
      }
    }
  }
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
