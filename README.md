# FinalProject
authors: Joshua OFORI<br/>
         Haris HIBIC<br/>
         Anthony TEYE-ADJEI<br/>
        
<b>Technologies used</b><br/>
1.Java<br/>
2.maven<br/>
3.bootstrap<br/>
<b>Tools</b><br/>
Scenebuilder<br/>

<b>General Info</b><br/>
This JavaFx Application Stores person data in a database.<br/>
Lists the person and render it on a table.<br/>
It uses form to add a new person to the database hence rendered
on the table.<br/>
The table is editable therefore a particula cell in the table can be edited and the 
chages is reflected in the database and on the table.<br/>
Person on the table can be deleted from the database when you select and click delete.<br/>
The data in the database can be exported in a Vcard format.
the export is at ./src/main/resources/files/persons.vcf<br/>

![Image description](https://github.com/Joshuaofori/FinalProject/tree/master/src/main/resources/files/app.png)

Flow of Code.<br/>
Person class is the entity(database) or model class.<br/>
    *It contains the fields of the database<br/>
Daos create the database connections and helps in communication with the database<br/>
PersonService has static methods and fields which using a PersonHolderClass which makes it possible
to share data between classes     
<b>Controllers</b><br/>
HomeScreenController and MainLayoutController
finally the main App<br/>

<b>Tests</b><br/>
PersonDaoTestCase class has methods for testing the database update
delete and fetch functionalities

    