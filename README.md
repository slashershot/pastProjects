#Video Streaming Project

Tested Working on windows 8.1 64bit

###Installation Instructions:

####Database Import:
I use MYSQL Workbench for database handling.

1.Data Import

2.Import from Self-Contained File

3.Choose the latest.sql

4.Start Import

####Java Import in Eclipse
1.File -> Import  -> Projects from Git -> Next

2.Clone URI -> Next
>-Protocol:git

>-Host:github.com/slashershot

>-Repository path:pastProjects

3.Untick master -> Next -> Choose your Import Directory -> Next -> Import Existing  Projects -> Next

4.Tick all -> Finish

####Modifications Needed

**MySQL Database**

1.Double click the oop-project in MySQL WorkBench

2.Enter the command:
>select * from videos;

3.Set the sourceDirectory to be the full path of the Test Video.

**Eclipse**
Dependencies:

1.Right Click Project -> Properties -> Libraries -> Select All & Remove

2.Add External JARs -> Select all JARs within java lib -> Select all JARs within NEE

Database Connection:

1.Expand database -> DBManager

2.Fill in the user and password for your database.

Video Streaming Application:

1.Expand Server -> videoStreaming

Modify
>C:/Users/Nigel/Desktop/Resume Items/Past Projects.(Archive)(backup)/Final Presentation Project 1.2/java lib/NEE

to your NEE directory.
