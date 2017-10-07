/*
 * Persistance.java
 *
 * Created on 07. Oktober 2006, 21:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.huberb.httppost.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.Repository;

/**
 * A simple xml persister.
 * <p>
 * This persister usess <code>java.beans.XMLDecoder</code>, and
 * <code>java.beans.XMLEncoder</code>.
 *
 * @author HuberB1
 */
public class Persistance {

    /**
     * Creates a new instance of Persistance
     */
    public Persistance() {
    }

    protected FileObject getSaveFolder() {
        FileObject myFolder = null;
        try {
            FileSystem sfs = Repository.getDefault().getDefaultFileSystem();
            FileObject root = sfs.getRoot();
            myFolder = FileUtil.createFolder(root, "httppost");
        } catch (IOException ioex) {
            ErrorManager.getDefault().notify(ioex);
        }
        return myFolder;
    }

    protected File getFile(HttpPostForm hpf) {
        FileObject fo = getSaveFolder();
        File fileFolder = FileUtil.toFile(fo);
        String fileName = hpf.getFileName();

        File file = new File(fileFolder, fileName);
        return file;
    }

    public void save(HttpPostForm hpf) {
        final File file = getFile(hpf);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            XMLEncoder xmlEnc = new XMLEncoder(fos);
            xmlEnc.writeObject(hpf);
        } catch (IOException ex) {
            ErrorManager.getDefault().notify(ex);
        }
    }

    public void delete(HttpPostForm hpf) {
        final File file = getFile(hpf);
        file.delete();
    }

    public List<HttpPostForm> load() {
        final List<HttpPostForm> list = new ArrayList<>();

        final FileObject fo = getSaveFolder();
        final File fileFolder = FileUtil.toFile(fo);

        final File[] files = fileFolder.listFiles();
        for (int i = 0; i < files.length; i++) {
            final File aFile = files[i];

            try (FileInputStream fis = new FileInputStream(aFile)) {
                XMLDecoder xmlDec = new XMLDecoder(fis);
                HttpPostForm hpf = (HttpPostForm) xmlDec.readObject();

                list.add(hpf);
            } catch (IOException ex) {
                ErrorManager.getDefault().notify(ex);
            }
        }
        return list;
    }
}
