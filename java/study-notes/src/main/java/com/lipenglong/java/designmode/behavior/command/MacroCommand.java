package com.lipenglong.java.designmode.behavior.command;

import java.util.Iterator;
import java.util.Stack;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public class MacroCommand implements Command {
    private Stack<Command> commands = new Stack<>();

    @Override
    public void execute() {
        Iterator<Command> iterator = commands.iterator();
        while (iterator.hasNext()) {
            iterator.next().execute();
        }
    }

    void append(Command cmd) {
        if (cmd != this) {
            commands.add(cmd);
        }
    }

    public void undo() {
        if (!commands.empty()) {
            commands.pop();
        }
    }

    void clear() {
        commands.clear();
    }
}
