# Export and Visualization Schema's Versions from JSON Data

The purpose of this project is to export and visualize schema's versions from
JSON data [JSON](https://www.json.org/json-en.html). With [Java](https://www.java.com/en/)
and her library [Jackson](https://github.com/FasterXML/jackson), I did the processing
of input files with JSON data. The visualization made with the help of Google's tool
[Google Charts](https://developers.google.com/chart) and specifically [Org Charts](https://developers.google.com/chart/interactive/docs/gallery/orgchart). I use the tool with [HTML](https://html.com/) and
[JavaScript](https://www.javascript.com/).
Στόχος του project είναι να εξάγει και να οπτικοποιήσει εκδόσεις του σχήματος
δεδομένων τύπου [JSON](https://www.json.org/json-en.html). Η επεξεργασία των
αρχείων εισόδου γίνεται με [Java](https://www.java.com/en/) και την βιβλιοθήκη της [Jackson](https://github.com/FasterXML/jackson). H οπτικοποίηση
με την βοήθεια του εργαλείου [Google Charts](https://developers.google.com/chart)
και συγκεκριμένα τα [Org Charts](https://developers.google.com/chart/interactive/docs/gallery/orgchart).
Το εργαλείο χρησιμοποιείται με την βοήθεια της [HTML](https://html.com/) και της
[JavaScript](https://www.javascript.com/).

## Benchmark

With [JMH](https://openjdk.java.net/projects/code-tools/jmh/) i executed a benchmark
with input files first with small size and then files with bigger size. So, i compared
the libraries that Java supports for JSON parsing.
Με την βοήθεια του εργαλείου [JMH](https://openjdk.java.net/projects/code-tools/jmh/)
εκτελέστηκε το benchmark με αρχεία εισόδου πρώτα με μικρό μέγεθος και ύστερα αρχεία
με μεγαλύτερο μέγεθος. Έτσι, έγινε η σύγκριση μεταξύ των βιβλιοθηκών της Java για
το ποια είναι πιο γρήγορη στο να διαβάζει αρχεία.

## Libraries

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

Οπότε όπως παρατηρούμε η Jackson είναι αρκετά γρήγορη και στις δύο περιπτώσεις.

## Running the tests

With framework [JUnit](https://junit.org/junit5/), i tested some methods. You can
find the tests in package testing.
Χρησιμοποιόντας το framework [JUnit](https://junit.org/junit5/), ελέγχθηκαν ορισμένες
μεθόδοι από κλάσεις. Τα tests βρίσκονται στο package testing.

## Visualization

Consequentially, after parsing JSON data the next problem was how to visualize the
versions with nice way, so the user can see the differences between versions that
program found. With JavaScript, HTML and Google Charts user can watch the differences,
since the application give this right to the user by using the default browser.
Οπότε μετά την επεξεργασία το επόμενο πρόβλημα ήταν να εμφανιστούν στον χρήστη τα αποτελέσματα
με "ωραίο" τρόπο ώστε να μπορεί να καταλάβει τις διαφορές ανάμεσα στις εκδόσεις που βρέθηκαν.
Με την βοήθεια της JavaScript, HTML και του εργαλείου Google Charts μπορεί ο χρήστης να
παρατηρήσει τις διαφορές στις εκδόσεις, καθώς η εφαρμογή του δίνει το δικαίωμα μέσω του default  
browser.

## Output Files

When application have finished the processing of input file, it will create output files
in order that each version and the differences that program found with the previous
version exists on these files. 
Η εφαρμογή με το που τελειώσει την επεξεργασία του αρχείου εισόδου θα δημιουργήσει αρχεία εξόδου
ώστε να υπάρχει η κάθε έκδοση σε αυτά και οι διαφορές που βρέθηκαν σε σχέση με την προηγούμενη έκδοση.

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
