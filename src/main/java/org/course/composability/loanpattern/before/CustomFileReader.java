package org.course.composability.loanpattern.before;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.reusable.Threads.delay;

public final class CustomFileReader implements ResourceHolder {

    private final org.apache.commons.lang3.time.StopWatch stopWatch;
    private final FileInputStream fileInputStream;

    public CustomFileReader() throws FileNotFoundException {
        System.out.println("Custom file reader created, acquiring resources");
        stopWatch = new org.apache.commons.lang3.time.StopWatch();  // resource acquisition
        fileInputStream = new FileInputStream("src/main/resources/file.txt");
        stopWatch.reset();
        stopWatch.start();
    }

    public void useResource() {
        System.out.println("The file reader read the following content: ");
        int i = 0;
        try {
            int fstByte = fileInputStream.read();
            while (fstByte != -1) {
            i++;
                if (i % 2 == 0) {
                    fstByte = fileInputStream.read();
                    continue;
                }
                System.out.print((char)fstByte);
                fstByte = fileInputStream.read();
            }
            delay(10);
        } catch (final IOException e) {
            System.out.println("Exception caught when reading the file" + e);
        }
    }

    @Override
    public void close() throws IOException {
        System.out.println();
        System.out.println("Custom file reader is closed");
        stopWatch.stop();
        final long g = stopWatch.getTime();
        System.out.println("Custom file reader was used for " + g + "ms, releasing resources");
        fileInputStream.close(); // resource release
        stopWatch.reset();
    }
}

