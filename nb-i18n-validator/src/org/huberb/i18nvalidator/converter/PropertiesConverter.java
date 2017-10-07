/*
 * PropertiesConverter.java
 *
 * Created on 19. Mai 2007, 22:23
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.huberb.i18nvalidator.converter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author HuberB1
 */
public class PropertiesConverter {
    
    /** Creates a new instance of PropertiesConverter */
    public PropertiesConverter() {
    }
    
    /**
     * Convert a properties file to an xml properties file
     */
    public void properties2XML(File file) throws IOException {
        FileObject foIn = FileUtil.toFileObject(file);
        
        AbstractPropertiesRead pr = new AbstractPropertiesRead() {
            protected void propertiesLoad(InputStream is) throws IOException {
                Properties props = this.getProps();
                props.load(is);
            }
        };
        pr.setProps( new Properties() );
        pr.readProperties(file);
        Properties properties = pr.getProps();
        
        AbstractPropertiesWrite pw = new AbstractPropertiesWrite() {
            protected void propertiesStore(OutputStream os, String comment) throws IOException {
                Properties props = this.getProps();
                props.storeToXML(os,comment);
            }
        };
        pw.setProps(properties);
        pw.setExtension(".xml");
        pw.writeProperties(foIn);
    }
    
    public void xml2Properties(File file) throws IOException {
        FileObject foIn = FileUtil.toFileObject(file);
        
        AbstractPropertiesRead pr = new AbstractPropertiesRead() {
            protected void propertiesLoad(InputStream is) throws IOException {
                Properties props = this.getProps();
                props.loadFromXML(is);
            }
        };
        pr.setProps( new Properties() );
        pr.readProperties(file);
        Properties properties = pr.getProps();
        
        AbstractPropertiesWrite pw = new AbstractPropertiesWrite() {
            protected void propertiesStore(OutputStream os, String comment) throws IOException {
                Properties props = this.getProps();
                props.store(os,comment);
            }
        };
        pw.setProps(properties);
        pw.setExtension(".properties");
        pw.writeProperties(foIn);
    }
    
    //------------------------------------------------------------------------
    
    static abstract class AbstractPropertiesRW {
        private Properties props;
        public void setProps(Properties props) {
            this.props = props;
        }
        public Properties getProps() {
            return props;
        }
    }
    
    static abstract class AbstractPropertiesWrite extends AbstractPropertiesRW {
        private String comment;
        private String extension;
        
        public void setComment(String comment) {
            this.comment = comment;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public void writeProperties(FileObject foIn) throws IOException {
            // write properties as properties file
            final String foOutFilename = foIn.getNameExt() + this.extension;
            
            final FileObject foOut = FileUtil.createData( foIn.getParent(), foOutFilename );
            final FileLock fLock = foOut.lock();
            final OutputStream fos = foOut.getOutputStream(fLock);
            try {
                // TODO make comment configurable
                //properties.store(fos, "---" );
                propertiesStore(fos,comment);
            } finally {
                fLock.releaseLock();
                fos.close();
            }
        }
        protected abstract void propertiesStore(OutputStream is,String comment) throws IOException;
    }
    
    static abstract class AbstractPropertiesRead extends AbstractPropertiesRW {
        public void readProperties(File file) throws IOException {
            FileObject foIn = FileUtil.toFileObject(file);
            // read properties as .xml files
            InputStream fis = foIn.getInputStream();
            try {
                //properties.loadFromXML(fis);
                propertiesLoad(fis);
            } finally {
                fis.close();
            }
        }
        protected abstract void propertiesLoad(InputStream is) throws IOException;
    }
    
}
