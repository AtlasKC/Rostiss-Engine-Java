package org.rostiss.engine;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * File: Util.java
 * Created by Atlas IND on 7/8/15 at 4:31 PM.
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

public class Util {

    public static FloatBuffer createFloatBuffer(int size) {
        return BufferUtils.createFloatBuffer(size);
    }

    public static IntBuffer createIntBuffer(int size) {
        return BufferUtils.createIntBuffer(size);
    }

    public static FloatBuffer createFlippedBuffer(Vertex[] vertices) {
        FloatBuffer result = createFloatBuffer(vertices.length * Vertex.SIZE);
        for(int i = 0; i < vertices.length; i++) {
            result.put(vertices[i].getPosition().getX());
            result.put(vertices[i].getPosition().getY());
            result.put(vertices[i].getPosition().getZ());
        }
        result.flip();
        return result;
    }

    public static IntBuffer createFlippedBuffer(int[] indices) {
        IntBuffer result = createIntBuffer(indices.length);
        result.put(indices);
        result.flip();
        return result;
    }

    public static FloatBuffer createFlippedBuffer(Matrix4f matrix) {
        FloatBuffer result = createFloatBuffer(16);
        for(int x = 0; x < 4; x++)
            for(int y = 0; y < 4; y++)
                result.put(matrix.getValue(x, y));
        result.flip();
        return result;
    }
}