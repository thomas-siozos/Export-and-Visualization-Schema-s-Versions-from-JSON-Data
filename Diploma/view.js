var objectConstructor = ({}).constructor;

for (var i = 0; i <= data_counter; i++) {
  for (var key in window["data_" + i]) {
      if (window["data_" + i].hasOwnProperty(key)) {
          console.log(key + " -> " + window["data_" + i][key]);
          if (window["data_" + i].constructor === objectConstructor) {
            console.log(key);
          }
      }
  }
}
