import io.indico.Indico;
import io.indico.api.results.IndicoResult;
import io.indico.api.utils.IndicoException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class PhotoSegreg {
    /**
     * Constructor to initialize Photo Segregation.
     */
    PhotoSegreg() {
    }
    /**
     * Method to segregate photos by their content.
     * @param folder - path to directory where photos are stored
     */
    public void segreg(String folder) throws IndicoException, IOException {

        Indico indico = new Indico("e08d0b8aa43eee42fef4b010ee922a8a");
        File directory = new File(folder);
        if (!directory.exists()) throw new FileNotFoundException("Directory not found.");
        File[] images = directory.listFiles();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("top_n", 1);
        File newDirectory;
        for (File name : images) {
            IndicoResult res = indico.imageRecognition.predict(name, params);
            String cat = res.getImageRecognition().keySet().toString();
            newDirectory = new File(cat);
            if (!newDirectory.exists()) {
                newDirectory.mkdir();
                name.renameTo(new File(newDirectory.toString() + "/foto//" + name.getName()));
            }
            else{
                name.renameTo(new File(newDirectory.toString() + "/foto//" + name.getName()));
            }

        }
    }
}