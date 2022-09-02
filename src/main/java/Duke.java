import exceptions.DukeException;
import exceptions.DukeInvalidNumberFormatException;
import exceptions.DukeUnspecifiedNumberException;

import java.util.Scanner;

import tasks.TaskList;

import commands.Command;

import ui.Ui;
import storage.Storage;
import parser.Parser;

public class Duke {

    private static final String LINE = "    __________________________________________________________________________";
    private static final String INDENT = "     ";

    private boolean isClosed = false;
    private TaskList tasks = new TaskList();
    private int counter = 0;

    private Ui ui;
    private Storage storage;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.retrieveTasks());
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
        ui.showGreetingMessage();
        boolean isByeCommand = false;
        while (!isByeCommand) {
            try {
                String fullCommand = ui.readCommand();
                ui.drawLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isByeCommand = c.isByeCommand();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.drawLine();
            }
        }
    }

}