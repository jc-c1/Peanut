<h1>Peanut</h1>
<p><em>Capturing all the beautiful moments</em></p>

[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/6AsM4lNqZMc/0.jpg)](https://www.youtube.com/watch?v=6AsM4lNqZMc)


<p><strong>Peanut</strong> is a journaling app that celebrates and documents baby milestones with parents
and help them capture all the memories of their little one's growth. The app caters to parents (Can including fur parents 
but some of the functionalities may not pertain). This project is dear for me because my mother also had giant 
journaling albums for me but they were lost during our immigration to Canada; in hopes that digitization can help
parents to keep these precious moments and easily and readily share with loved ones who may not live close by. 
The app will have feature prompts for common growth milestones and free form memory entries for the journal.</p>
<br>

<strong>PHASE 2 UPDATE:</strong>

Persistence package inspired by: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

User Story
<ul>
<li>As a user, I want to have the <strong> option to save my Peanut journal state </strong> to a local file, 
when I enter the quit in the application; specifically, Parent (first name, last name), Peanut (first name, last name), 
Journal index, and Memory (title, content).
</li>
<li>As a user, I want to have the <strong> option to load my Peanut Journal </strong> from a local file 
(with the saved fields as described above), when I start the application </li>
</ul>

<br>
<br>

<h3> <em>User Story</em> </h3>
<ul> <li>As a user, I want to be able to <strong>create a profile for myself</strong>, user profile should include:
    <ul><li>Name, date of birth, photo, family members, and optional description</li></ul></li>
<li>As a user, I want to be able to <strong>add a profile for the Peanut(s)</strong>, Peanut profile should include:
    <ul><li>Name, date of birth, photo, family members, journal collaborators, last documented height and weight, 
    and optional description</li></ul></li>

<li>As a user, I want to be able to <strong>fill in milestone entries</strong>, milestones in chronological order:
  <ul><li>1 month, 3 months, 6 months, 1 year, 18 months, 2 years, 3 years, 4 years, 5 years, 6 years... etc. </li>
  <li> Each milestone allows for a photo, a hand print, height, weight, optional text input </li>
  <li> Each annual milestone has optional question prompts for the parent to answer and a field for a letter to Peanut</li></ul></li>

<li>As a user, I want to be able to <strong>update a selected milestone entry</strong></li>
<li>As a user, I want to be able to <strong>delete a selected milestone entry</strong></li>

<li>As a user, I want to be able to <strong>add a free form memory entry</strong>
  <ul><li> Each entry has a title and a date field</li>
  <li> Option to upload photo and description of event, height and weight entry, customized tag
    <ul><li>Tag Examples: Firsts, Trouble, Proud, School, Friends, Fight, Sick, Art. </li></ul></li></ul></li>

<li>As a user, I want to be able to <strong>view a list of the memory entry titles of the journal</strong></li>
<li>As a user, I want to be able to <strong>search and filter a list memory entry titles based off associated tags</strong></li>
<li>As a user, I want to be able to <strong>select a memory entry in the journal and view the memory entry in detail</strong></li>
<li>As a user, I want to be able to <strong>update a memory entry</strong></li>
<li>As a user, I want to be able to <strong>remove a memory entry</strong> </li>

<li>As a user, I want to be able to <strong>save drafts of my entries</strong>
<em>because being a parent requires incredible task-switching abilities</em></li> 


</ul>

<br>
<br>

<h3> <em>Future</em> </h3>
<ul>
<li>As a user, I want to be able to backup data remotely</li>
<li>As a user, I want to be able to add collaborators to Peanut's adventures</li>
<li>As a user, I want to be able to incorporate AI for tag generations</li>
<li>As a user, I want to be able to customize and decorate the journal</li>
<li>As a user, I want to be able to generate a curated collection of memory entries to share with friends and family</li>
<li>As a user, I want to be able to visualize a growth chart (height, weight)</li>
<li>As a user, I want to receive personalized reminders for Peanut
  <ul><li>Upcoming vaccinations, doctors visit, first day of school... etc.</li></ul></li>
<li>As a user I want to be able to share and mail the letters to Peanut when they become parents.</li>
</ul>

<br>



Developer comment: @210-bot #project to check for coverage



<h3>Phase 4: Task 2</h3> 
Fri Mar 29 17:33:21 PDT 2024 <br>
Added a new parent, named "Coco". <br>
Fri Mar 29 17:33:26 PDT 2024  <br>
Added a new peanut, named "Pea" to parent "Coco". <br>
Fri Mar 29 17:33:44 PDT 2024 <br>
Added a new memory, titled "Peanut's first step!!". <br>
Fri Mar 29 17:34:12 PDT 2024 <br>
Added a new memory, titled "Peanut's first haircut!". <br>
Fri Mar 29 17:34:23 PDT 2024 <br>
Added a new memory, titled "toBeDeleted". <br>
Fri Mar 29 17:34:23 PDT 2024 <br>
Deleted a new memory, titled "toBeDeleted".



<h3> If I had more time to work on the project: </h3> 
I would add in enumeration for Home, Parent, Peanut, and Entry(Memory) that indicate which screen the user is on. 
The program is currently built with bruteforce and has a lot of redundancy, each level of Home/Parent/Peanut/Entry(Memory) has its own set of display, menu, list, button, form classes in the ui package and its own display, hide, add, get methods in AppUI.

<strong>UI: </strong>
<ul>
<li>I would have a class for menu, form, list, and display and have enumeration and display as accordingly.</li>
<li>I would have a class for addButton that would also use the enumeration and display and add as accordingly.</li>
<li>I would add in updateButton and deleteButtons, that would follow similar logic as Add in using enumeration.</li>
<li>In the class AppUI, I would factor out the display____ methods, hide____Page methods, add____ methods, get____form methods also basing it on the enumeration.</li>
</ul>
<br>
<strong>Model: </strong>
<ul>
<li>I would refactor the memory and milestone classes that have fields of the same field into the Entry interface, along with their CRUD methods.</li>
<li>For example, height, weight, entryDate; they are currently not implemented in the ui but they should be refactored before implementation.</li>
<li>Similarly, for Parent and Peanut, I would refactor them and add fields into User class. This will make the code more readable.</li>
</ul>
 
 
 
