
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author phila
 */
public class Main extends Application
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primary_stage)
    {
        try
        {
            File[] files = new DirectoryChooser().showDialog(primary_stage).listFiles();

            String output_folder = new DirectoryChooser().showDialog(primary_stage).getPath();

            for (File i : files)
            {
                String output_file = output_folder + "\\" + i.getName().split("_")[0].toUpperCase() + "\\01\\" + i.getName();
                new File(output_file).getParentFile().mkdirs();
                Files.copy(i.toPath(), Paths.get(output_file));
            }

        }
        catch (Exception exception)
        {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
        }
        Platform.exit();
    }

}
