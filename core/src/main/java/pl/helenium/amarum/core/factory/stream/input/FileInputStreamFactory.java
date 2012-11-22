package pl.helenium.amarum.core.factory.stream.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileInputStreamFactory extends AbstractInputStreamFactory {

    public FileInputStreamFactory(String location) {
        super(location);
    }

    @Override
    protected InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(new File(getLocation()));
    }

}
