package org.huberb.nbjaranalyzer.misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import org.openide.ErrorManager;

import org.openide.awt.HtmlBrowser;
import org.openide.awt.HtmlBrowser.URLDisplayer;

/**
 * A simple Helper class for displaying the html content.
 */
public class HtmlResultHelper {
    /**
     * This is the main entry point for displaying the html content.
     *
     * @param result the html content
     */
    public void showResult(String result) {
        final URL url = dumpResultToFile(result);
        if (url != null) {
            final URLDisplayer ud = HtmlBrowser.URLDisplayer.getDefault();
            ud.showURL( url );
        }
    }
    
    /**
     * Dump html content to a temporary file
     * <p>
     * The temporary file is register for
     * <code>deleteOnExit</code>.
     *
     * @param html the html content
     * @return ULR of the temporary file
     */
    protected URL dumpResultToFile(String html) {
        FileWriter fw = null;
        try {
            File resultTempFile = File.createTempFile("jaranalyzer", ".html");
            resultTempFile.deleteOnExit();
            fw = new FileWriter(resultTempFile);
            fw.write( html );
            fw.flush();
            return resultTempFile.toURI().toURL();
        }  catch (IOException ex) {
            ErrorManager.getDefault().log(ErrorManager.WARNING, "Cannot create temporary html file" );
            return null;
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                // ignore it
            }
        }
    }
}