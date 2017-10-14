/*
 * CpdProcessor.java
 *
 * Created on 21. April 2007, 14:35
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.cpd.cpdlib;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.pmd.cpd.CPD;
import net.sourceforge.pmd.cpd.CPDListener;
import net.sourceforge.pmd.cpd.JavaTokenizer;
import net.sourceforge.pmd.cpd.Language;
import net.sourceforge.pmd.cpd.LanguageFactory;
import net.sourceforge.pmd.cpd.Renderer;
import org.huberb.cpd.options.CpdSettings;
import org.openide.ErrorManager;
import org.openide.util.NbBundle;

/**
 * Run CPD
 *
 * @author HuberB1
 */
public class CpdProcessor {

    private final CpdSettings cpo;
    private final List<File> filesets;

    private CPDListener cpdListener;
    private final PrintWriter printWriter;

    /**
     * Creates a new instance of CpdProcessor
     *
     * @param writer
     */
    public CpdProcessor(PrintWriter writer) {
        this.cpdListener = null;
        this.printWriter = writer;

        //
        this.cpo = CpdSettings.getDefault();
        this.filesets = new ArrayList<>();
    }

    public void setCpdListener(CPDListener cpdListener) {
        this.cpdListener = cpdListener;
    }

    public void addFile(File f) {
        this.filesets.add(f);
    }

    /**
     * Analyze the tokenized files, and directories.
     * <p>
     * The result is written to the specified printWriter.
     */
    public void analyze() {
        CPD cpd = null;
        try {
            //this.cpo.loadSettings();
            cpd = new CPD(this.cpo.getMinimalTokenCount(), createLanguage());
            if (this.cpdListener != null) {
                cpd.setCpdListener(this.cpdListener);
            }

            log("Tokenizing files");
            tokenizeFiles(cpd);

            log("Starting to analyze code");
            long timeTaken = analyzeCode(cpd);
            log("Done analyzing code; that took " + timeTaken + " milliseconds");

            log("Generating report");
            report(cpd);
        } finally {
            // cpd remove listener
            if (cpd != null) {
                cpd.setCpdListener(null);
            }
        }
    }

    /**
     * Tokenize the added files, and directories.
     *
     * @param cpd
     */
    protected void tokenizeFiles(CPD cpd) {
        for (File f : filesets) {
            try {
                if (!f.canRead()) {
                    continue;
                }

                if (f.isDirectory()) {
                    if (this.cpo.getRecursivly()) {
                        cpd.addRecursively(f.toString());
                    } else {
                        cpd.addAllInDirectory(f.toString());
                    }
                } else if (f.isFile()) {
                    cpd.add(f);
                }
            } catch (IOException ioex) {
                ErrorManager em = ErrorManager.getDefault();
                em.notify(ErrorManager.WARNING, ioex);
            } catch (Exception t) {
                ErrorManager em = ErrorManager.getDefault();
                em.notify(ErrorManager.WARNING, t);
            }
        }
    }

    /**
     * Analyze the files
     *
     * @param cpd
     * @return
     */
    protected long analyzeCode(CPD cpd) {
        long start = System.currentTimeMillis();
        cpd.go();
        long stop = System.currentTimeMillis();
        return stop - start;
    }

    /**
     * Report the result of a cpd analyze using the appropriate CPD Renderer
     *
     * @param cpd
     */
    protected void report(CPD cpd) {
        try {
            if (!cpd.getMatches().hasNext()) {
                int tokenCount = this.cpo.getMinimalTokenCount();
                String m = NbBundle.getMessage(CpdProcessor.class, "no_duplicates", tokenCount);
                this.printWriter.println(m);
            } else {
                Renderer renderer = this.cpo.getRendererEnum().createRenderer();
                String content = renderer.render(cpd.getMatches());
                this.printWriter.print(content);
            }
        } finally {
            this.printWriter.flush();
        }
    }

    /**
     * Create a CPD Language
     */
    private Language createLanguage() {
        Properties p = new Properties();
        if (this.cpo.getIgnoreLiterals()) {
            p.setProperty(JavaTokenizer.IGNORE_LITERALS, "true");
        }
        if (this.cpo.getIgnoreIdentifiers()) {
            p.setProperty(JavaTokenizer.IGNORE_IDENTIFIERS, "true");
        }
        p.setProperty(LanguageFactory.EXTENSION, this.cpo.getLanguageExt());

        return new LanguageFactory().createLanguage(this.cpo.getLanguage(), p);
    }

    //---
    private final static Logger logger = Logger.getLogger(CpdProcessor.class.getName());

    private void log(String msg) {
        log(msg, null);
    }

    private void log(String msg, Throwable thrown) {
        if (thrown == null) {
            logger.log(Level.INFO, msg);
        } else {
            logger.log(Level.INFO, msg, thrown);
        }
    }

}
