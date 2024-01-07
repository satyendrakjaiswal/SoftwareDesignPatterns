import java.util.Stack;

// Receiver
class TextEditor {
    private StringBuilder content = new StringBuilder();

    void addText(String text) {
        content.append(text);
    }

    void deleteText(int length) {
        if (length <= content.length()) {
            content.delete(content.length() - length, content.length());
        } else {
            System.out.println("Cannot delete more characters than available.");
        }
    }

    void printContent() {
        System.out.println("Current Content: " + content.toString());
    }
}

// Command interface
interface TextOperation {
    void execute();
    void undo();
}

// ConcreteCommand for adding text
class AddTextCommand implements TextOperation {
    private TextEditor textEditor;
    private String text;

    AddTextCommand(TextEditor textEditor, String text) {
        this.textEditor = textEditor;
        this.text = text;
    }

    @Override
    public void execute() {
        textEditor.addText(text);
    }

    @Override
    public void undo() {
        textEditor.deleteText(text.length());
    }
}

// ConcreteCommand for deleting text
class DeleteTextCommand implements TextOperation {
    private TextEditor textEditor;
    private int length;

    DeleteTextCommand(TextEditor textEditor, int length) {
        this.textEditor = textEditor;
        this.length = length;
    }

    @Override
    public void execute() {
        textEditor.deleteText(length);
    }

    @Override
    public void undo() {
        // For simplicity, we'll assume the deleted text can be restored.
        textEditor.addText("Restored text");
    }
}

// Invoker
class TextEditorInvoker {
    private Stack<TextOperation> commandHistory = new Stack<>();

    void executeOperation(TextOperation operation) {
        operation.execute();
        commandHistory.push(operation);
    }

    void undoLastOperation() {
        if (!commandHistory.isEmpty()) {
            TextOperation lastOperation = commandHistory.pop();
            lastOperation.undo();
        } else {
            System.out.println("Nothing to undo.");
        }
    }
}

// Client code
public class CommandPatternClient {
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
        TextEditorInvoker invoker = new TextEditorInvoker();

        // Adding text
        TextOperation addTextCommand = new AddTextCommand(textEditor, "Hello, ");
        invoker.executeOperation(addTextCommand);

        // Deleting text
        TextOperation deleteCommand = new DeleteTextCommand(textEditor, 3);
        invoker.executeOperation(deleteCommand);

        // Adding more text
        addTextCommand = new AddTextCommand(textEditor, "Design Patterns!");
        invoker.executeOperation(addTextCommand);

        // Print current content
        textEditor.printContent();  // Output: Current Content: Hello, Design Patterns!

        // Undo last operation
        invoker.undoLastOperation();

        // Print current content after undo
        textEditor.printContent();  // Output: Current Content: Hello,

        // Undo again (nothing to undo)
        invoker.undoLastOperation();  // Output: Nothing to undo.
    }
}
