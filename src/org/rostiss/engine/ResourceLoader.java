package org.rostiss.engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

    public static Mesh loadMesh(String file) {
        String[] splitArray = file.split("\\.");
        String ext = splitArray[splitArray.length - 1];
        if(!ext.equals("obj")) {
            System.err.println("Error: File format not supported for mesh data: " + ext);
            new Exception().printStackTrace();
            System.exit(1);
        }
        ArrayList<Vertex> vertices = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        BufferedReader meshReader;
        try {
            meshReader = new BufferedReader(new FileReader("./res/models/" + file));
            String line;
            while((line = meshReader.readLine()) != null) {
                String[] tokens = line.split(" ");
                tokens = Util.removeEmptyStrings(tokens);
                if(tokens.length == 0 || tokens[0].equals("#"))
                    continue;
                else if(tokens[0].equals("v"))
                    vertices.add(new Vertex(new Vector3f(Float.valueOf(tokens[1]), Float.valueOf(tokens[2]), Float.valueOf(tokens[3]))));
                else if(tokens[0].equals("f")) {
                    indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[2].split("/")[0]) - 1);
                    indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                    if(tokens.length > 4) {
                        indices.add(Integer.parseInt(tokens[1].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[3].split("/")[0]) - 1);
                        indices.add(Integer.parseInt(tokens[4].split("/")[0]) - 1);
                    }
                }
            }
            meshReader.close();
            Mesh result = new Mesh();
            Vertex[] vertexData = new Vertex[vertices.size()];
            vertices.toArray(vertexData);
            Integer[] indexData = new Integer[indices.size()];
            indices.toArray(indexData);
            result.addVertices(vertexData, Util.toIntArray(indexData));
            return result;
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}