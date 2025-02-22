
import java.util.Scanner;

class KeyboardInput
{
    private static KeyboardInput keyboardInputObject = null;
    private Scanner scanner;
    
    private KeyboardInput()
    {
        this.scanner = new Scanner(System.in);
    }

    public static KeyboardInput getObject()
    {
        if(KeyboardInput.keyboardInputObject == null)
        {
            KeyboardInput.keyboardInputObject = new KeyboardInput();
        }
        return KeyboardInput.keyboardInputObject;
    }

    public static int readInt()
    {
        return KeyboardInput.getObject().scanner.nextInt();
    }
}