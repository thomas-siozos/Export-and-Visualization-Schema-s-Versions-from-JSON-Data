# Export and Visualization Schema's Versions from JSON Data

The goal of this project is to export and visualize the different versions (schema) of the [JSON](https://www.json.org/json-en.html) data format. The processing of input files (JSON) is done with the [Java](https://www.java.com/en/) and the help of the library [Jackson](https://github.com/FasterXML/jackson). The visualization is done with the tool [Google Charts](https://developers.google.com/chart), specifically with [Org Charts](https://developers.google.com/chart/interactive/docs/gallery/orgchart), in [HTML](https://html.com/) and [JavaScript](https://www.javascript.com/). 

## Table of Contents

* [Benchamrk](#Benchamrk)
  * [Libraries](#Libraries)
  * [Run](#Run)
  * [Results](#Results)
* [Running the tests](#Running-the-tests)
* [Visualization](#Visualization)
  * [Example of Visualization](#Example-of-Visualization)
* [Output Files](#Output-Files)
* [Build With](#Build-With)
* [Versioning](#Versioning)
* [Authors](#Authors)
* [Copyright](#Copyright)


## Benchamrk

The benchmark was performed with the tool [JMH](https://openjdk.java.net/projects/code-tools/jmh/) with small size input files first and then with bigger size. As a result, the comparison was made between the Java's libraries, that support parsing of Json data format, to find out which one is faster.

### Libraries

* [Jackson](https://github.com/FasterXML/jackson)
* [Gson](https://github.com/google/gson)
* [JSON-P](https://javaee.github.io/jsonp/)
* [Json-simple](https://code.google.com/archive/p/json-simple/)
* [mJson](https://bolerio.github.io/mjson/)

### Run
<b>mvn install</b><br/>
<b>java -jar target/benchmarks.jar</b>

### Results

<div align="center"><b>Small Files Table</b></div>

<div align="center"><img src="/benchmark/small_files_table.png"/></div>

<div align="center"><b>Big Files Table</b></div>

<div align="center"><img src="/benchmark/big_files_table.png"/></div>

The tables show the Jackson library is fast to both cases.

## Running the tests

The tests about some crucial methods are in the package testing. The framework [JUnit](https://junit.org/junit5/) was used.

## Visualization

After the processing of the data the next step was to show the results to the user with a nice way to help him understand the differences between the versions. With JavaScript, HTML and the tool Google Charts the user is able to watch a chart with the versions through a browser.

### Example of Visualization
**Input file: cards.json**<br/>
![example](https://github.com/SiozosThomas/Export-and-Visualization-Schema-s-Versions-from-JSON-Data/blob/master/Export_and_Visualization_Schemas_Versions_from_JSON_Data/presentation/cards_visual_example.png)

## Output Files

Once the processing of the data is finished, output files will be created so that each file contains the versions that found in the input file.

## Build With

* [Eclipse](https://www.eclipse.org/)
* [Atom](https://atom.io/)
* [Maven](https://maven.apache.org/)
* **Java Microbenchmark Harness**
* **Java**
* **HTML**
* **JavaScript**
* **Google Charts**
* **Jackson**
* **Swing**
* **JUnit5**

## Versioning

<b>v1.0.0</b>

## Authors

:pencil2: <b>Siozos Thomas</b>

## Copyright

[MIT](https://github.com/SiozosThomas/Export-and-Visualization-Schema-s-Versions-from-JSON-Data/blob/master/LICENSE) © 2019 Siozos Thomas
