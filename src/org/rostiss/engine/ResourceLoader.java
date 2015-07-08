package org.rostiss.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * File: ResourceLoader.java
 * Created by Atlas IND on 7/8/15 at 5:08 PM.
 * [2014] - [2015] Rostiss Development
 * All rights reserved.
 * NOTICE:  All information contained herein is, and remains
 * the property of Rostiss Development and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Rostiss Development
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Rostiss Development.
 */

public class ResourceLoader {

    public static String loadShader(String file) {
        StringBuilder shader = new StringBuilder();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./res/shaders/" + file));
            String line;
            while((line = reader.readLine()) != null)
                shader.append(line).append("\n");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shader.toString();
    }
}