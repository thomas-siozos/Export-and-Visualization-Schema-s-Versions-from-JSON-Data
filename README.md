# Diploma

Στόχος του project είναι να εξάγει και να οπτικοποιήσει εκδόσεις του σχήματος
δεδομένων τύπου [JSON](https://www.json.org/json-en.html). Η επεξεργασία των
αρχείων εισόδου γίνεται με [Java](https://www.java.com/en/) και την βιβλιοθήκη της [Jackson](https://github.com/FasterXML/jackson). H οπτικοποίηση
με την βοήθεια του εργαλείου [Google Charts](https://developers.google.com/chart)
και συγκεκριμένα τα [Org Charts](https://developers.google.com/chart/interactive/docs/gallery/orgchart).
Το εργαλείο χρησιμοποιείται με την βοήθεια της [HTML](https://html.com/) και της
[JavaScript](https://www.javascript.com/).

## Benchmark

Με την βοήθεια του εργαλείου [JMH](https://openjdk.java.net/projects/code-tools/jmh/)
εκτελέστηκε το benchmark με αρχεία εισόδου πρώτα με μικρό μέγεθος και ύστερα αρχεία
με μεγαλύτερο μέγεθος. Έτσι, έγινε η σύγκριση μεταξύ των βιβλιοθηκών της Java για
το ποια είναι πιο γρήγορη στο να διαβάζει αρχεία.

## Libraries

<ul>
<li>&nbsp;[Jackson](https://github.com/FasterXML/jackson)&nbsp;</li>
<li>[Gson](https://github.com/google/gson)</li>
<li>[JSON-P](https://javaee.github.io/jsonp/)</li>
<li>[Json-simple](https://code.google.com/archive/p/json-simple/)</li>
<li>[mJson](https://bolerio.github.io/mjson/)</li>
</ul>

### Run
<b>mvn install</b>
<b>java -jar target/benchmarks.jar</b>

### Results

<div aligh="center"<b>Small Files Table</b>

![Small Files](/benchmark/small_files_table.png)

<div aligh="center"<b>Big Files Table</b>

![Big Files](/benchmark/big_files_table.png)

Οπότε όπως παρατηρούμε η Jackson είναι αρκετά γρήγορη και στις δύο περιπτώσεις.

## Running the tests

Χρησιμοποιόντας το framework [JUnit](https://junit.org/junit5/), ελέγχθηκαν ορισμένες
μεθόδοι από κλάσεις. Τα tests βρίσκονται στο package testing.

## Visualization

Οπότε μετά την επεξεργασία το επόμενο πρόβλημα ήταν να εμφανιστούν στον χρήστη τα αποτελέσματα
με "ωραίο" τρόπο ώστε να μπορεί να καταλάβει τις διαφορές ανάμεσα στις εκδόσεις που βρέθηκαν.
Με την βοήθεια της JavaScript, HTML και του εργαλείου Google Charts μπορεί ο χρήστης να
παρατηρήσει τις διαφορές στις εκδόσεις, καθώς η εφαρμογή του δίνει το δικαίωμα μέσω του default  
browser.

## Output Files

Η εφαρμογή με το που τελειώσει την επεξεργασία του αρχείου εισόδου θα δημιουργήσει αρχεία εξόδου
ώστε να υπάρχει η κάθε έκδοση σε αυτά και οι διαφορές που βρέθηκαν σε σχέση με την προηγούμενη έκδοση.

## Build With

<ul>
<li>[Eclipse](https://www.eclipse.org/)</li>
<li>[Atom](https://atom.io/)</li>
<li><b>Java</b></li>
<li><b>HTML</b></li>
<li><b>JavaScript</b></li>
<li><b>Google Charts</b></li>
<li><b>Java Microbenchmark Harness</b></li>
<li>[Maven](https://maven.apache.org/)</li>
<li><b>Jackson</b></li>
<li><b>Swing</b></li>
<li><b>JUnit5</b></li>
</ul>

## Versioning

<b>v1.0.0</b>

## Authors

<b>Siozos Thomas</b>

## Copyright

[MIT](https://github.com/SiozosThomas/Export-and-Visualization-Schema-s-Versions-from-JSON-Data/blob/master/LICENSE) © 2019 Siozos Thomas
