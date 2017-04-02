package java.com.mikeburke106.mines.basic;

import java.com.mikeburke106.mines.model.Field;
import java.com.mikeburke106.mines.model.FieldPrintStrategy;
import java.com.mikeburke106.mines.model.Position;
import java.io.PrintStream;

/**
 * Prints the field out to text.
 * <p>
 * Created by Mike Burke on 4/1/17.
 */
public class TextFieldPrintStrategy implements FieldPrintStrategy {
    private final PrintStream outStream;

    public TextFieldPrintStrategy(PrintStream outStream) {
        this.outStream = outStream;
    }

    @Override
    public void printField(Field field) {
        final Field.Configuration fieldConfiguration = field.configuration();
        final Position.Pool positionPool = fieldConfiguration.positionPool();
        final int width = positionPool.width();
        final int height = positionPool.height();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final Position position = positionPool.atLocation(x, y);
                final String positionText = field.isMine(position) ? "[x]" : "[ ]";
                outStream.print(positionText);
            }
            outStream.print("\n");
        }
    }
}
