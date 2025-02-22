package ascii_art;

import java.util.Scanner;
import ascii_output.AsciiOutput;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import java.io.IOException;
import java.util.Arrays;
import image.Image;
import image_char_matching.SubImgCharMatcher;
/**
 * Shell class serves as the entry point for the ASCII art generation application.
 */
public class Shell {
    private SubImgCharMatcher matcher;
    private int resolution;
    private Image image;
    private AsciiOutput out;
    /**
     * The entry point of the program.
     * This method is called when the program starts execution.
     * It invokes the `run()` method of the `Shell` class
     * to initialize and run the shell for processing user commands.
     * @param args The command-line arguments passed to the program
     *(not used in this application).
     */
    public  static void main(String[] args){
        run();
    }

    /**
     * Starts the application.
     */
    public static void run ()  {
        Shell shell = new Shell();
        shell.userInputManagement();
    }
    /**
     * Initializes the Shell instance.
     */
    private Shell()  {
        this.out = new ConsoleAsciiOutput();

        this.resolution = Constant.DEFAULT_RESOLUTION;
        this.matcher = new SubImgCharMatcher(Constant.DEFAULT_CHAR_SET);
        try {
            this.image = new Image(Constant.DEFAULT_IMAGE_PATH);
        }
        catch (IOException e) {
            System.out.println(Constant.EXCEPTION_INVALID_IMAGE_PATH);
        }
    }
    /**
     * Manages user input and interaction.
     */
    private void userInputManagement() {
        while (true) {
            String userInput = getInput();
            if(userInput.equals("exit")){
                return;
            }
            if (userInput.equals("chars")) {
                getCharSetManagement();
                continue;
            }
            if ((userInput.startsWith("add "))|| (userInput.startsWith("remove "))) {
                AddAndRemovalManagement(userInput);
                continue;
            }
            if(userInput.startsWith("res ")){
                resManagement(userInput);
                continue;
            }
            if(userInput.startsWith("image ")){
                setImage(userInput.substring(6));
                continue;
            }
            if(userInput.startsWith("output ") ){
                setOutPut(userInput.substring(7));
                continue;
            }
            if(userInput.startsWith("asciiArt")){
                runAsciiArt();
                continue;
            }
                System.out.println(Constant.INVALID_USER_INPUT);
        }
    }
    /**
     * Generates and outputs ASCII art based on the provided image and resolution.
     */
    private void runAsciiArt(){
        if(this.matcher.getNotNormalizedCharsMap().isEmpty()){
            System.out.println(Constant.EMPTY_CHARSET_MESSAGE);
            return;
        }
        AsciiArtAlgorithm asc = new AsciiArtAlgorithm(this.image, this.resolution, this.matcher);
        char[][] outp = asc.run();
        this.out.out(outp);
    }
    /**
     * Sets the output location for the generated ASCII art.
     */
    private  void setOutPut(String command){
        switch (command) {
            case "html":
                this.out = new HtmlAsciiOutput(Constant.OUTPUT_FILE_NAME, Constant.FONT_NAME_OUTPUT);
                break;
            case "console":
                this.out = new ConsoleAsciiOutput();
                break;
            default:
                System.out.println(Constant.SET_OUT_PUT_INVALID_COMMAND);
        }
    }
    /**
     * Sets the image for ASCII art generation.
     */
    private void setImage(String newImagePath) {
        try {
            this.image = new Image(newImagePath);
        }
        catch (IOException e){
            System.out.println(Constant.EXCEPTION_INVALID_IMAGE_PATH);
        }
    }
    /**
     * Retrieves user input from the console.
     * @return User input string.
     */
    private String getInput() {
        System.out.print(Constant.INPUT_USER_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();


    }
    /**
     * print the charSet.
     */
    private void getCharSetManagement() {
        int charSetSize = this.matcher.getNotNormalizedCharsMap().size();
        char[] charSet = new char[charSetSize];
        int index = 0;
        for (Character key : this.matcher.getNotNormalizedCharsMap().keySet()) {
            charSet[index++] = key;
        }
        Arrays.sort(charSet);
        for (int i = 0; i < charSetSize; i++) {
            System.out.print(charSet[i] + " ");
        }
        System.out.println();
    }
    /**
     * Manages resolution changes based on user input.
     * @param userInput User input specifying the desired resolution change.
     */
    private void resManagement(String userInput) {
        int maxResolution = this.image.getWidth();
        int minResolution =  Math.max(1, this.image.getWidth()/this.image.getHeight());
        int newResolution;

        String command = userInput.substring(4);
        if (command.equals("up") || command.equals("down")) {

            if (command.equals("up")) {
                newResolution = this.resolution * 2;
            } else {
                newResolution= this.resolution / 2;
            }

            if(newResolution<=maxResolution && newResolution>=minResolution) {
                this.resolution = newResolution;
                System.out.println(Constant.CHANGE_RESOLUTION_MESSAGE + this.resolution);
            }
            else{
                System.out.println(Constant.EXCEEDING_BOUNDARIES_MESSAGE);
            }
        }
        else {
            System.out.println(Constant.INVALID_RES_INPUT );
        }
    }
    /**
     * Manages addition and removal of characters from the character set.
     * @param userInput User input specifying the action (add or remove) and characters.
     */
    private void AddAndRemovalManagement(String userInput) {
        String userAddInput, command;
        if (userInput.startsWith("add")) {
            command = "add";
            userAddInput = userInput.substring(4);
        } else {
            command = "remove";
            userAddInput = userInput.substring(7);
        }
        if (userAddInput.length() == 1) {
            updateCharacterSet(command, userAddInput.charAt(0));
            return;
        }
        if (userAddInput.length() == 3) {
            if (userAddInput.equals("all")) {
                for (int ascii = 32; ascii < 127; ascii++) {
                    updateCharacterSet(command, (char)ascii);
                }
                return;
            }
            if (userAddInput.charAt(1) == '-') {
                char firstChar = userAddInput.charAt(0);
                char secondChar = userAddInput.charAt(2);
                int firstIdx = Math.min(firstChar, secondChar);
                int lastIdx = Math.max(firstChar, secondChar);
                for (int ascii = firstIdx; ascii < lastIdx + 1; ascii++) {
                    updateCharacterSet(command, (char)ascii);
                }
                return;
            }
        }
        if (userAddInput.equals("space")) {
            updateCharacterSet(command, ' ');
            return;
        }
        printInvalidInput(command);
    }
    /**
     * Adds or removes a character from the character set based on the command.
     * @param command Action command (add or remove).
     * @param c Character to be added or removed.
     */
    private void updateCharacterSet(String command, char c) {
        if (command.equals("add")) {
            this.matcher.addChar(c);
        } else {
            if(this.matcher.getNotNormalizedCharsMap().containsKey(c)){
                this.matcher.removeChar(c);
            }
        }
    }
    /**
     * Prints an error message for an invalid input command.
     * @param command Action command (add or remove).
     */
    private void printInvalidInput(String command){
        if(command.equals("add")){
            System.out.println(Constant.ADD_INVALID_MESSAGE);
        }
        else{
            System.out.println(Constant.REMOVE_INVALID_MESSAGE);
        }
    }
}




