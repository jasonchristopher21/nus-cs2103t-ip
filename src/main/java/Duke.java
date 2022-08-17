import java.util.Scanner;

public class Duke {

    private static final String LINE = "    ____________________________________________________________";
    private static final String INDENT = "     ";

    private static boolean isClosed = false;
    private static String[] list = new String[100];
    private static int counter = 0;

    public static void main(String[] args) {
        greetingMessage();
        Scanner sc = new Scanner(System.in);
        while (!isClosed) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                bye();
            } else if (command.equals("list")) {
                printList();
            } else {
                addToList(command);
            }
        }
    }

    private static void greetingMessage() {
        drawLine();
        indentMessage("Hello! I'm Duke");
        indentMessage("What can I do for you?");
        drawLine();
    }

    private static void bye() {
        isClosed = true;
        drawLine();
        indentMessage("Bye, Hope to see you again soon!");
        drawLine();
    }

    private static void echo(String msg) {
        drawLine();
        indentMessage(msg);
        drawLine();
    }

    public static void indentMessage(String msg) {
        System.out.println(INDENT + msg);
    }

    public static void drawLine() {
        System.out.println(LINE);
    }

    private static void printList() {
        drawLine();
        for (int i = 0; i < counter; i++) {
            String msg = (i + 1) + ". " + list[i];
            indentMessage(msg);
        }
        drawLine();
    }

    private static void addToList(String msg) {
        list[counter] = msg;
        counter++;
        drawLine();
        indentMessage("Added: " + msg);
        drawLine();
    }
}