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
    newDiv.innerHTML = "Version " + (i + 1);
    newDiv.style.fontSize = "50px";
    document.body.appendChild(newDiv);
    var newDiv = document.createElement("div");
    newDiv.setAttribute("id", "version_" + i);
    document.body.appendChild(newDiv);
  }
  for (var i = 0; i <= data_counter; i++) {
    var chart = new google.visualization.OrgChart(document.getElementById("version_" + i));
    chart.draw(versions[i], {'allowHtml' : true})
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
