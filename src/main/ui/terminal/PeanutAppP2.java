package ui.terminal;

import model.user.*;
import model.journal.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

// Represents user interface and main control of the Peanut app
// Includes: a list of parents (users of app), scanner input for user inputs, screen to display
//           currentParent using the app, currentPeanut user is browsing, currentEntry user is browsing
public class PeanutAppP2 {
    private static final String JSON_STORE = "./data/parentList.json";

    private ParentList parentList = new ParentList();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Scanner input;
    private Screen screen;
    private Parent currentParent = null;    // current fields initialized as null, user must select and
    private Peanut currentPeanut = null;    // set specific parent, peanut, and entry to view
    private Entry currentEntry = null;


    // --------------------------------------- METHOD ABSTRACTION ----------------------------------------------------
    // List of display entries
    private static final String ENT = "\t Please Enter";
    private static final String RTN = "\t\trtn - return to previous screen";
    private static final String QUIT = "\t\tquit - anytime you'd like to exit!\n";
    private static final String SAVE = "\t\tsave - to save files";
    private static final String SAVED = "\tSaved!";
    private static final String EXIT_GREET = "\nYou have exited the program, see you next peanut moment! :)";

    // EFFECTS: print given prompt
    private void displayPrompt(String s) {
        System.out.println(s);
    }

    private String newProfile(String s) {
        String rtn = String.join(" ","\n---------- Creating a new", s, "profile! ---------- \n");
        return rtn;
    }

    private void newProfileCommand() {
        switch (screen) {
            case HOME:
                displayPrompt(newProfile("parent"));
                Parent pr = new Parent("fn", "ln");
                createNewProfile(pr);
                parentList.getParents().add(pr);
                screen = Screen.PARENT;
                currentParent = pr;
                break;
            case PARENT:
                displayPrompt(newProfile("peanut"));
                Peanut pn = new Peanut("fn", "ln", currentParent);
                createNewProfile(pn);
                screen = Screen.PEANUT;
                currentPeanut = pn;
                break;
            default:
        }
    }

    private void createNewProfile(User u) {
        List<String> userName = new ArrayList<String>();
        displayPrompt(ENT);
        userName = userProfileNameInput(userName);
        u.updateFirstName(userName.get(0));
        u.updateLastName(userName.get(1));
    }

    private List userProfileNameInput(List l) {
        System.out.print("\t\tFirst Name: ");
        l.add(input.nextLine());
        System.out.print("\t\tLast Name: ");
        l.add(input.nextLine());
        return l;
    }

    // ----------------------------------------------------------------------------------------------------------------

    // EFFECTS: runs peanut application
    public PeanutAppP2() {
        runPeanut();
    }

    // MODIFIES: this
    // EFFECTS: calls switchScrees and processCommand to processes user input and display
    private void runPeanut() {
        boolean journaling = true;
        String command = null;
        init();
        while (journaling) {
            switchScreens();
            command = input.nextLine().toLowerCase();
            if (command.equals("save")) {
                saveCommand();
                System.out.println("Please navigate via menu above");
                command = input.nextLine().toLowerCase();
            }
            if (command.equals("quit")) {
                displayPrompt("Last check: To save, please enter \"save\" or type \"quit\" again to exit");
                command = input.nextLine().toLowerCase();
                if (command.equals("save")) {
                    saveCommand();
                }
                journaling = false;
            } else {
                processCommand(command);
            }
        }
        displayPrompt(EXIT_GREET);
    }

    // EFFECTS: displays proper screen depending on current state of screen
    private void switchScreens() {
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
        displayPrompt(SAVE);
        displayPrompt(QUIT);
    }

    // EFFECTS: processes commands depending user input on current state of screen
    private void processCommand(String c) {

        switch (screen) {
            case HOME:
                startCommand(c);
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
    }


    private void saveCommand() {

        try {
            jsonWriter.open();
            jsonWriter.write(parentList);
            jsonWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        displayPrompt(SAVED);
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadParentList() {
        try {
            parentList = jsonReader.read();
            System.out.println(parentList);
            System.out.println("Loaded");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    // EFFECTS: displays Start menu of options to user
    private void displayStart() {
        if (parentList.getParents().isEmpty()) {
            System.out.println("---------- Home ----------");
            System.out.println("Congratulations and welcome to your first Peanut Journal :)");
            displayPrompt(ENT);
            System.out.println("\t\tload - load previous file");
            System.out.println("\t\tcpr - create new Parent profile");
        } else {
            System.out.println("---------- Home ----------");
            System.out.println("If you have a file already, please select from the following:");
            int i = 1;
            for (Parent p : parentList.getParents()) {
                System.out.println("\t" + i + ". " + p.getFirstName() + " " + p.getLastName());
                i++;
            }
            System.out.println("Otherwise, please enter");
            System.out.println("\t\tcpr - create new Parent profile");

        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command at Home screen
    private void startCommand(String command) {
        Integer parentFile;
        if (command.equals("cpr")) {
            createNewParent();
        } else if (command.equals("load")) {
            loadParentList();
        } else {
            try {
                parentFile = Integer.valueOf(command);
                currentParent = parentList.getParents().get(parentFile - 1);
                screen = Screen.PARENT;
            } catch (NumberFormatException e) {
                System.out.println("Selection not valid, please try again!");
            }
        }
    }



    // MODIFIES: this
    // EFFECTS: processes user command of parent profile creation
    private void createNewParent() {
        System.out.print("\n---------- Creating a new parent profile! ---------- \n");
        displayPrompt(ENT);
        System.out.print("\t\tFirst Name: ");
        String fn = input.nextLine();
        System.out.print("\t\tLast Name: ");
        String ln = input.nextLine();
        Parent pr = new Parent(fn, ln);
        parentList.getParents().add(pr);
        screen = Screen.PARENT;
        currentParent = pr;
    }

    // EFFECTS: displays Parent menu of options to user
    private void displayParent(Parent pr) {
        List<Peanut> peanuts = pr.getPeanutsOfParent();
        if (peanuts.isEmpty()) {
            System.out.print("\n---------- Parent Profile ---------- \n"); //TODO: change to name of Parent
            displayPrompt(ENT);
            System.out.println("\t\tcpn - create new Peanut profile");
            displayPrompt(RTN);
        } else {
            System.out.print("\n---------- Parent Profile ---------- \n"); //TODO: change to name of Parent
            System.out.println("\nSelect from the following:");
            int i = 1;
            for (Peanut p : peanuts) {
                System.out.println("\t" + i + ". " + p.getFirstName());
                i++;
            }
            System.out.println("\t\tcpn - create new Peanut profile");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command on Parent profile
    private void parentCommand(String command) {
        Integer peanutFile;
        if (command.equals("cpn")) {
            createNewPeanut(currentParent);
        } else if (command.equals("rtn")) {
            currentParent = null;
            screen = Screen.HOME;
        } else {
            try {
                peanutFile = Integer.valueOf(command);
                Peanut pn = currentParent.getPeanutByIndex(peanutFile);
                if (pn != null) {
                    currentPeanut = pn;
                    screen = Screen.PEANUT;
                } else {
                    System.out.println("Selection not valid, please try again!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Selection not valid, please try again!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Processes user command of peanut profile creation
    private void createNewPeanut(Parent pr) {
        System.out.print("---------- Creating a new peanut profile! ---------- \n");
        displayPrompt(ENT);
        System.out.print("\t\tFirst Name: ");
        String fn = input.nextLine();
        System.out.print("\t\tLast Name: ");
        String ln = input.nextLine();
        Peanut pn = new Peanut(fn, ln, pr);
        screen = Screen.PEANUT;
        currentPeanut = pn;
    }

    // EFFECTS: displays peanut menu of options to user
    private void displayPeanut(Peanut pn) {
        List<Entry> pnJournal = pn.getJournal().getIndex();
        System.out.println("\n---------- Peanut " + pn.getFirstName() + "'s Journal ----------");
        displayPrompt(ENT);
        System.out.println("\t\tmem - create new memory");
        if (!pnJournal.isEmpty()) {
            System.out.println("\t\tEntry number - to read an entry");
            int i = 1;
            for (Entry e : pnJournal) {
                System.out.println("\t\t\t" + i + ". " + e.getTitle());
                i++;
            }
        }
        displayPrompt(RTN);
        //System.out.println("\nEnter mile to fill in a milestone"); //TODO: add in milestone commands
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
        } else {
            try {
                entryFile = Integer.valueOf(command);
                Entry e = currentPeanut.getJournal().getEntryByIndex(entryFile);
                if (e != null) {
                    currentEntry = e;
                    screen = Screen.ENTRY;
                } else {
                    System.out.println("Selection not valid, please try again!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Selection not valid, please try again!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command of new memory creation
    public void createNewMemory(Peanut pn) {
        System.out.println("\n---------- Memory Entry ----------");
        displayPrompt(ENT);
        System.out.print("\t\tTitle: ");
        String title = input.nextLine();
        System.out.print("\t\tContent: ");
        String content = input.nextLine();
        Memory m = new Memory(currentPeanut, currentParent);
        m.addTitle(title);
        m.addContent(content);
        pn.getJournal().addEntry(m);
    }

    // MODIFIES: this
    // EFFECTS: processes user command of update Memory;
    // TODO: have to refactor interface appropriately to accommodate Milestones addTitle()
    public void updateMemory() {
        System.out.print("---------- Updating Memory! ---------- \n");
        displayPrompt(ENT);
        System.out.print("\t\tTitle: ");
        String title = input.nextLine();
        System.out.print("\t\tContent: ");
        String content = input.nextLine();
        currentEntry.addTitle(title);
        currentEntry.addContent(content);
        System.out.println("Updated!");
    }

    // REQUIRES: Entry must be a Memory for now; TODO: add in Milestone features
    // EFFECTS: displays selected Entry e
    private void displayEntry(Entry e) {
        if (e instanceof Memory) {
            System.out.println("-----------" + e.getTitle() + "----------");
            System.out.println("\t" + ((Memory)e).getContent());
        } else {
            System.out.println(e.getTitle());
        }
        System.out.println("\n Please Enter");
        System.out.println("\t\tudt - update Memory");
        displayPrompt(RTN);
    }

    // MODIFIES: this
    // EFFECTS: processes user command on Memory selected
    private void memoryCommand(String command) {
        if (command.equals("udt")) {
            updateMemory();
        } else if (command.equals("rtn")) {
            currentEntry = null;
            screen = Screen.PEANUT;
        } else {
            System.out.println("Selection not valid, please try again!");
        }
    }


    // MODIFIES: this
    // EFFECTS: initializes scanner input
    private void init() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        screen = Screen.HOME;
    }














}
