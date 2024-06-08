package ui.terminal;

import model.journal.Entry;
import model.journal.Memory;
import model.user.Parent;
import model.user.ParentList;
import model.user.Peanut;
import model.user.User;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents user interface and main control of the Peanut app
// Includes: a list of parents (users of app), scanner input for user inputs, screen to display
//           currentParent using the app, currentPeanut user is browsing, currentEntry user is browsing
public class PeanutAppDF {
    // --------------------------------------- DISPLAY TEXT ----------------------------------------------------
    private static final String SELECT_PROMPT = "Please select from the following:";
    private static final String ENT = "\t>> Please Enter";
    private static final String OTHER_ENTER = "\n\tOtherwise, please enter:";

    private static final String LOAD = "\t\tload - load file";
    private static final String RTN = "\t\trtn - return";
    private static final String QUIT = "\t\tquit - exit app";
    private static final String SAVE = "\t\tsave - save files";

    private static final String SAVED = "*** Saved! ***   ---   Please refer to previous menu   ---";
    private static final String NOTSAVED = "*** Not saved ***   ---   Please refer to previous menu   ---";
    private static final String LOADED = "*** Loaded! ***";
    private static final String INVALID_SEL = "*** Selection not valid, please try again! ***";

    private static final String HOME = "Home";
    private static final String PARENTSTRING = "Parent";
    private static final String PEANUTSTRING = "Peanut";

    private static final String CONGRATS = "Congratulations and welcome to your first Peanut Journal!";
    private static final String EXIT_GREET = "\nYou have exited the program, see you next peanut moment! :)";

    // -------------------------------------------------- JSON ----------------------------------------------------
    private static final String JSON_FILE_PATH = "./data/";
    private static final String JSON_FILE_TYPE = ".json";
    private static String fileName = "";
    private static String jsonStore = "";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // --------------------------------------------- STATE VARIABLES ----------------------------------------------
    private Scanner input;                    // input scanner
    private Screen screen;                    // screen display
    private String command;                   // user input
    private boolean journaling = false;       // journaling state
    private boolean shouldRun = false;              // gate for screenSwitch()

    private ParentList parentList = null;
    private List<Parent> parents = new ArrayList<Parent>();

    private Parent currentParent = null;
    private Peanut currentPeanut = null;
    private Entry currentEntry = null;

    //----------------------------------------------- STATE METHODS ------------------------------------------------
    // EFFECTS: runs peanut application
    public PeanutAppDF() {
        runPeanut();
    }

    // MODIFIES: this
    // EFFECTS: process user input via command and display accordingly; may save, quit, or load at any page
    private void runPeanut() {
        init();
        while (journaling) {
            if (command.equals("save")) {
                saveCommand();
            } else if (command.equals("load")) {
                loadParentList();
            } else if (command.equals("quit")) {
                quitCommand();
            } else {
                processCommand(command);
            }
        }
        displayPrompt(EXIT_GREET);
    }

    // MODIFIES: this
    // EFFECTS: initializes scanner input, state variables, display initial home screen and scans for user input
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        screen = Screen.HOME;
        journaling = true;
        shouldRun = true;
        switchScreens();
        command = input.nextLine().toLowerCase();
    }

    // MODIFIES: this
    // EFFECTS: processes commands depending user input on current state of screen, switch screen accordingly,
    //          scans for next user input
    private void processCommand(String c) {
        switch (screen) {
            case HOME:
                homeCommand(c);
                break;
            case PARENT:
                parentCommand(c);
                break;
            case PEANUT:
                peanutCommand(c);
                break;
            case ENTRY:
                memoryCommand(c);
                break;
            default:
        }
        switchScreens();
        command = input.nextLine().toLowerCase();
    }

    // REQUIRES: non-null run;
    // EFFECTS: displays proper screen depending on current state of screen if run is true;
    private void switchScreens() {
        if (shouldRun) {
            switch (screen) {
                case HOME:
                    displayStart();
                    break;
                case PARENT:
                    displayParent(currentParent);
                    break;
                case PEANUT:
                    displayPeanut(currentPeanut);
                    break;
                case ENTRY:
                    displayEntry(currentEntry);
                    break;
                default:
            }
            displayMenu();
        }
        shouldRun = false;
    }


    // ----------------------------------------- SAVE/LOAD/QUIT METHODS ----------------------------------------------

    // MODIFIES: this
    // EFFECTS: processes user command to quit, checks if user would like to save before quitting
    private void quitCommand() {
        displayPrompt(">> Would you like to save before exit?");
        displayPrompt("\tPlease enter: \t\"yes\" - save \t \"no\" - exit \t \"rtn\" - return");
        String c = input.nextLine().toLowerCase();
        if (c.equals("no")) {
            journaling = false;
        } else if (c.equals("yes")) {
            saveCommand();
            journaling = false;
        } else if (c.equals("rtn")) {
            shouldRun = true;
            switchScreens();
            command = input.nextLine().toLowerCase();
        } else {
            displayPrompt(INVALID_SEL);
            command = "quit";
        }
    }

    // MODIFIES: this
    // EFFECTS: checks if there is a parentList to save, if so then checks if user wants to save to current file,
    //          save to another file, or not save at all
    public void saveCommand() {
        if (parentList == null) {
            nothingToSave();
        } else {
            if (jsonWriter == null) {
                saveNewFile();
            } else {
                displaySaveToSpecificFile();
                String i = input.nextLine().toLowerCase();
                if (i.equals("yes")) {
                    saveFile();
                } else if (i.equals("no") && command != "quit") {
                    displayPrompt(NOTSAVED);
                    command = input.nextLine().toLowerCase();
                } else if (i.equals("other")) {
                    saveNewFile();
                } else {
                    invalidSaveSel();
                }
            }
        }
    }



    // MODIFIES: this
    // EFFECTS: request for new name to save as new file in data package via safeFile
    private void saveNewFile() {
        displayPrompt(">> Save file as: ");
        fileName = input.nextLine();
        jsonStore = String.join("", JSON_FILE_PATH, fileName, JSON_FILE_TYPE);
        jsonWriter = new JsonWriter(jsonStore);
        saveFile();
        command = input.nextLine().toLowerCase();
    }

    // MODIFIES: this, if saveFile is called after "load"
    // EFFECTS: save current state of parentList to the file destination of jsonWriter
    private void saveFile() {
        try {
            jsonWriter.open();
            jsonWriter.write(parentList);
            jsonWriter.close();
            if (!(command.equals("load") || command.equals("quit"))) {
                displayPrompt(SAVED);
                command = input.nextLine().toLowerCase();
            } else {
                displayPrompt("*** SAVED! ***");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + jsonStore);
        }
    }

    // MODIFIES: this
    // EFFECTS: display selection is invalid and returns user back to save selection page
    private void invalidSaveSel() {
        displayPrompt(INVALID_SEL);
        command = "save";
    }

    // MODIFIES: this
    // EFFECTS: display nothing to save and request user to enter input at home page
    private void nothingToSave() {
        System.out.println("*** Nothing to save! ***");
        command = input.nextLine().toLowerCase();
    }

    // MODIFIES: this
    // EFFECTS: sets up file path to load from user input;
    private void setLoadFile() {
        displayPrompt(">> Load file named: ");
        String i = input.nextLine();
        fileName = i;
        jsonStore = String.join("", JSON_FILE_PATH, fileName, JSON_FILE_TYPE);
        loadFile();
    }

    // MODIFIES: this
    // EFFECTS: checks if user would like to save current file first before loading over current file
    private void loadFileOverFile() {
        displayPrompt("Save current files before loading another file? <<note: data would be loss if loaded over>>");
        displayPrompt("\tyes - to save \t\tno - to load");
        String i = input.nextLine().toLowerCase();
        if (i.equals("yes")) {
            saveCommand();
            setLoadFile();
        } else if (i.equals("no")) {
            setLoadFile();
        } else {
            displayPrompt(INVALID_SEL);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads file from file path provided by jsonStore and switch screen accordingly
    private void loadFile() {
        jsonReader = new JsonReader(jsonStore);
        jsonWriter = new JsonWriter(jsonStore);
        try {
            parentList = jsonReader.read();
            parents = parentList.getParents();
            shouldRun = true;
            switchScreens();
            displayPrompt(LOADED);
        } catch (IOException e) {
            System.out.println("*** Unable to load file [" + fileName + "] from [" + jsonStore + "] ***");
            System.out.println(">> Please make selection from previous again!");
            jsonReader = null;
        }
    }

    // MODIFIES: this
    // EFFECTS: loads ParentList from file
    private void loadParentList() {
        if (jsonReader == null) {
            setLoadFile();
        } else {
            loadFileOverFile();
        }
        command = input.nextLine().toLowerCase();
    }

    // -------------------------------------- NAVIGATION COMMAND METHODS ---------------------------------------------
    // MODIFIES: this
    // EFFECTS: processes user command at Home screen
    private void homeCommand(String command) {
        if (command.equals("cpr")) {
            if (parentList == null) {
                parentList = new ParentList();
                parents = parentList.getParents();
            }
            newProfileCommand();
        } else {
            Integer parentFile;
            try {
                parentFile = Integer.valueOf(command);
                Parent pr = parentList.getParentByIndex(parentFile);
                if (pr != null) {
                    currentParent = pr;
                    screen = Screen.PARENT;
                    shouldRun = true;
                } else {
                    displayPrompt(INVALID_SEL);
                }
            } catch (NumberFormatException e) {
                displayPrompt(INVALID_SEL);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: set user state to home and display home screen
    private void setHome() {
        currentParent = null;
        screen = Screen.HOME;
        shouldRun = true;
    }

    // MODIFIES: this
    // EFFECTS: processes user command on Parent profile
    private void parentCommand(String command) {
        Integer peanutFile;
        if (command.equals("cpn")) {
            newProfileCommand();
        } else if (command.equals("rtn")) {
            setHome();
        } else {
            try {
                peanutFile = Integer.valueOf(command);
                Peanut pn = currentParent.getPeanutByIndex(peanutFile);
                if (pn != null) {
                    currentPeanut = pn;
                    screen = Screen.PEANUT;
                    shouldRun = true;
                } else {
                    displayPrompt(INVALID_SEL);
                }
            } catch (NumberFormatException e) {
                displayPrompt(INVALID_SEL);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command on peanut profile
    private void peanutCommand(String command) {
        Integer entryFile;
        if (command.equals("mem")) {
            createNewMemory(currentPeanut);
        } else if (command.equals("rtn")) {
            currentPeanut = null;
            screen = Screen.PARENT;
            shouldRun = true;
        } else {
            try {
                entryFile = Integer.valueOf(command);
                Entry e = currentPeanut.getJournal().getEntryByIndex(entryFile);
                if (e != null) {
                    currentEntry = e;
                    screen = Screen.ENTRY;
                    shouldRun = true;
                } else {
                    displayPrompt(INVALID_SEL);
                }
            } catch (NumberFormatException e) {
                displayPrompt(INVALID_SEL);
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: processes user command on Memory selected
    private void memoryCommand(String command) {
        if (command.equals("udt")) {
            updateMemory();
        } else if (command.equals("rtn")) {
            currentEntry = null;
            screen = Screen.PEANUT;
            shouldRun = true;
        } else {
            displayPrompt(INVALID_SEL);
        }
    }

    // --------------------------------------- PROFILE CREATION ---------------------------------------------------

    // MODIFIES: this, Peanut/Parent depending on which profile
    // EFFECTS: display title and prompts for profile creation depending on whether it is peanut or parent being created
    //          then process creation accordingly
    private void newProfileCommand() {
        switch (screen) {
            case HOME:
                displayPrompt(formatTitle(newProfileTitle(PARENTSTRING)));
                Parent pr = new Parent("fn", "ln");
                addUserInputAsProfileName(pr);
                parents.add(pr);
                screen = Screen.PARENT;
                currentParent = pr;
                break;
            case PARENT:
                displayPrompt(formatTitle(newProfileTitle(PEANUTSTRING)));
                Peanut pn = new Peanut("fn", "ln", currentParent);
                addUserInputAsProfileName(pn);
                screen = Screen.PEANUT;
                currentPeanut = pn;
                break;
            default:
        }
        shouldRun = true;
    }

    // REQUIRES: non-null user
    // MODIFIES: this
    // EFFECTS: update profile name from user input
    //          Generated array of userName has only 2 elements, any other ones will be disregarded
    private void addUserInputAsProfileName(User u) {
        List<String> userName = new ArrayList<String>();
        userName = userProfileNameInput(userName);
        u.updateFirstName(userName.get(0));
        u.updateLastName(userName.get(1));
    }

    // REQUIRES: Empty List
    // MODIFIES: this
    // EFFECTS: receives user name input
    private List userProfileNameInput(List l) {
        System.out.print("\tFirst Name: ");
        l.add(input.nextLine());
        System.out.print("\tLast Name: ");
        l.add(input.nextLine());
        return l;
    }

    // --------------------------------------- MEMORY CREATION ---------------------------------------------------

    // MODIFIES: this
    // EFFECTS: processes user command of new memory creation
    public void createNewMemory(Peanut pn) {
        displayPrompt(formatTitle("MEMORY ENTRY"));
        System.out.print("\tTitle: ");
        String title = input.nextLine();
        System.out.print("\tContent: ");
        String content = input.nextLine();
        Memory m = new Memory(currentPeanut, currentParent);
        m.addTitle(title);
        m.addContent(content);
        pn.getJournal().addEntry(m);
        shouldRun = true;
    }

    // MODIFIES: this
    // EFFECTS: processes user command of update Memory;
    // TODO: have to refactor interface appropriately to accommodate Milestones addTitle()
    public void updateMemory() {
        displayPrompt(formatTitle("UPDATING MEMORY"));
        System.out.print("\tTitle: ");
        String title = input.nextLine();
        System.out.print("\tContent: ");
        String content = input.nextLine();
        currentEntry.addTitle(title);
        currentEntry.addContent(content);
        System.out.println("*** Updated! ***");
        shouldRun = true;
    }


    // -------------------------------------------- DISPLAY METHODS -----------------------------------------------

    // EFFECTS: print given prompt
    private void displayPrompt(String s) {
        System.out.println(s);
    }

    // REQUIRES: user type in string
    // EFFECTS: return specific type of user profile creation page title
    private String newProfileTitle(String s) {
        return String.join(" ","Creating a New", s, "Profile");
    }

    // REQUIRES: non-null User
    // EFFECTS: returns specific user's name in profile title
    private String userProfileTitle(User u) {
        String userType = "default";
        switch (screen) {
            case PARENT:
                userType = PARENTSTRING;
                break;
            case PEANUT:
                userType = PEANUTSTRING;
                break;
        }
        return String.join(" ", userType, u.getFirstName().concat("'s Profile"));
    }

    // EFFECTS: formats title to have 59-60 char in length total with dashes on each side of title text
    private String formatTitle(String s) {
        String pre = "\n-";
        String post = "-";

        for (int i = s.length(); i < 57; i = i + 4) {
            pre = pre.concat("--");
            post = post.concat("--");
            if (i == 53 || i == 54)  {
                pre = pre.concat("-");
                post = post.concat("-");
            }
        }

        return String.join(" ", pre, s, post);
    }


    // EFFECTS: displays bottom menu depending on user state
    private void displayMenu() {
        if (screen != screen.HOME) {
            System.out.println(LOAD + RTN);
            System.out.println(SAVE + QUIT);
        } else if (parentList != null) {
            System.out.println(LOAD);
            System.out.println(SAVE + QUIT);
        } else {
            System.out.println(LOAD + QUIT);
        }
    }

    // EFFECTS: displays Start menu of options to user
    private void displayStart() {
        displayPrompt(formatTitle(HOME));
        if (parentList != null) {
            displayPrompt(SELECT_PROMPT);
            int i = 1;
            for (Parent p : parents) {
                System.out.println("\t" + i + ". " + p.getFirstName() + " " + p.getLastName());
                i++;
            }
            displayPrompt(OTHER_ENTER);
        } else {
            System.out.println("Welcome to Peanut Journaling App!");
            displayPrompt(ENT);
        }
        System.out.println("\t\tcpr - create new Parent");
    }

    // EFFECTS: displays Parent menu of options to user
    private void displayParent(Parent pr) {
        List<Peanut> peanuts = pr.getPeanutsOfParent();
        displayPrompt(formatTitle(userProfileTitle(pr)));
        if (!peanuts.isEmpty()) {
            displayPrompt(SELECT_PROMPT);
            int i = 1;
            for (Peanut p : peanuts) {
                System.out.println("\t" + i + ". " + p.getFirstName());
                i++;
            }
            displayPrompt(OTHER_ENTER);
        } else {
            displayPrompt(CONGRATS);
            displayPrompt(ENT);
        }
        System.out.println("\t\tcpn - create new Peanut");
    }

    // EFFECTS: displays peanut menu of options to user
    private void displayPeanut(Peanut pn) {
        List<Entry> pnJournal = pn.getJournal().getIndex();
        displayPrompt(formatTitle(userProfileTitle(pn)));
        if (pnJournal.isEmpty()) {
            displayPrompt(CONGRATS);
            displayPrompt(ENT);
        } else {
            displayPrompt(SELECT_PROMPT);
            int i = 1;
            for (Entry e : pnJournal) {
                System.out.println("\t" + i + ". " + e.getTitle());
                i++;
            }
            displayPrompt(OTHER_ENTER);
        }
        System.out.println("\t\tmem - create new memory");

        //System.out.println("\nEnter mile to fill in a milestone"); //TODO: add in milestone commands
    }

    // REQUIRES: Entry must be a Memory for now; TODO: add in Milestone features
    // EFFECTS: displays selected Entry e
    private void displayEntry(Entry e) {
        if (e instanceof Memory) {
            displayPrompt(formatTitle(e.getTitle()));
            System.out.println("\t" + e.getContent());
        } else {
            System.out.println(e.getTitle());
        }
        System.out.println("\n Please Enter");
        System.out.println("\t\tudt - update Memory");

    }

    // EFFECTS: display selection for save if saving in general or saving during quit screen
    private void displaySaveToSpecificFile() {
        if (command == "quit") {
            displayPrompt("\nWould you like to save file to " + fileName
                    + "?\n\tyes - save to \"" + fileName + "\" \tno - exit");
        } else {
            displayPrompt("\nWould you like to save file to " + fileName
                    + "?\n\tyes - save to \"" + fileName + "\" \tother - save to another file \n\tno - return");
        }
    }

}
