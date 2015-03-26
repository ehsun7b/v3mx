package com.ehsunbehravesh.v3m.web.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Ehsun Behravesh
 */
public class StaticResource extends WebResources {

    private static final int DEFAULT_BUFFER_SIZE = 2048;
    protected String file;
    protected int bufferSize;

    public StaticResource(String contentType, String file) {
        this(contentType, file, DEFAULT_BUFFER_SIZE);
    }

    public StaticResource(String contentType, String file, int bufferSize) {
        super(contentType);
        this.file = file;
        this.bufferSize = bufferSize;
    }

    @Override
    public void writeContent(OutputStream outputStream) throws IOException {
        try (FileInputStream is = new FileInputStream(file)) {
            byte[] buffer = new byte[bufferSize];
            int l;

            while ((l = is.read(buffer)) > -1) {
                outputStream.write(buffer, 0, l);
            }
        }
    }

    @Override
    public long contentLength() {
        return file.length();
    }

    public String getFile() {
        return file;
    }

    public int getBufferSize() {
        return bufferSize;
    }
}
