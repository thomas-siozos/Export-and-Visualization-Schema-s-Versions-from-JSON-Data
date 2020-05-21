google.charts.load('current', {'packages':['Line']});
      google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {

      var data = new google.visualization.DataTable();
      data.addColumn('string', 'File');
      data.addColumn('number', 'File Size');
      data.addColumn('number', 'Total Json Objects');
      data.addColumn('number', 'Total Versions');

      data.addRows([
        ['test_countries_2_same_entries',	3, 2,  1],
        ['test_countries_2_entries',	3,	2,	2],
        ['countries_small',	422,	248,	159],
        ['profiles',	456,	1515,	11],
        ['countries-big',	2313,	21640,	3],
        ['cards',	2690,	9059,	464],
        ['city_inspections',	23842,	81047,	6],
        ['photo',	25060,	200000,	1],
        ['tip',	238805,	1223094,	1],
        ['review',	5222145,	6685900,	1]
        ]);
        // Some raw data (not necessarily accurate)
        // var data = google.visualization.arrayToDataTable([
        //   // ['Month', 'Bolivia', 'Ecuador', 'Madagascar', 'Papua New Guinea', 'Rwanda', 'Average'],
        //   // ['2004/05',  165,      938,         522,             998,           450,      614.6],
        //   // ['2005/06',  135,      1120,        599,             1268,          288,      682],
        //   // ['2006/07',  157,      1167,        587,             807,           397,      623],
        //   // ['2007/08',  139,      1110,        615,             968,           215,      609.4],
        //   // ['2008/09',  136,      691,         629,             1026,          366,      569.6]
        //   // ['File', 'File Size', 'Execution Time', 'Total Json Objects', 'Total Versions'],
        //   // ['test_countries_2_same_entries',	3, 1, 2,  1],
        //   // ['test_countries_2_entries',	3,	1,	2,	2],
        //   // ['countries_small',	422,	510,	248,	159],
        //   // ['profiles',	456,	490,	1515,	11],
        //   // ['countries-big',	2313,	111,	21640,	3],
        //   // ['cards',	2690,	640,	9059,	464],
        //   // ['city_inspections',	23842,	587,	81047,	6],
        //   // ['photo',	25060,	509,	200000,	1],
        //   // ['tip',	238805,	3571,	1223094,	1],
        //   // ['review',	5222145,	35914,	6685900,	1]
        //   ['File', 'File Size'],
        //   // ['test_countries_2_same_entries',	3],
        //   // ['test_countries_2_entries',	3],
        //   // ['countries_small',	422],
        //   // ['profiles',	456]
        //   ['countries-big',	2313],
        //   ['cards',	2690],
        //   ['city_inspections',	23842],
        //   ['photo',	25060]
        //   // ['tip',	238805],
        //   // ['review',	5222145]
        // ]);

        var options = {
            chart: {
            title: 'Box Office Earnings in First Two Weeks of Opening',
            subtitle: 'in millions of dollars (USD)'
          },
          width: 900,
          height: 500
        };

        var chart = new google.charts.Line(document.getElementById('linechart_material'));

        chart.draw(data, options);
      }
