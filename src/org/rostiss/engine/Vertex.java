package org.rostiss.engine;

/**
 * File: Vertex.java
 * Created by Atlas IND on 7/8/15 at 4:20 PM.
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

public class Vertex {

    public static final int SIZE = 5;

    private Vector3f position;
    private Vector2f textureCoordinate;

    public Vertex(Vector3f position) {
        this(position, new Vector2f(0, 0));
    }

    public Vertex(Vector3f position, Vector2f textureCoordinate) {
        this.position = position;
        this.textureCoordinate = textureCoordinate;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector2f getTextureCoordinate() {
        return textureCoordinate;
    }

    public void setTextureCoordinate(Vector2f textureCoordinate) {
        this.textureCoordinate = textureCoordinate;
    }
}