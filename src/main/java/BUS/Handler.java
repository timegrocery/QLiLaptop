/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import java.io.File;
/**
 *
 * @author WindZ
 */
public class Handler {
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return false;
        return true;
    }
    public static String getFullPath(final String shortPath) {
        /*
        final URL resource = Handler.class.getClassLoader().getResource("./" + shortPath);
        String tempPath = resource.getFile(); // get the path of class
        tempPath = tempPath.substring(1); // removes the "/" character at the beginning of the path
        tempPath = tempPath.replace("%20", " "); // spaces handler with url
        return tempPath;
        */
        File file = new File(shortPath);
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }
}
