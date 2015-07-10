package org.rostiss.engine;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * File: Mesh.java
 * Created by Atlas IND on 7/8/15 at 4:23 PM.
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

public class Mesh {

    private int vbo, ibo, size;

    public Mesh() {
        vbo = glGenBuffers();
        ibo = glGenBuffers();
        size = 0;
    }

    public void addVertices(Vertex[] vertices, int[] indices) {
        addVertices(vertices, indices, false);
    }

    public void addVertices(Vertex[] vertices, int[] indices, boolean calculateNormals) {
        if(calculateNormals)
            calculateNormals(vertices, indices);
        size = indices.length;
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
    }

    public void draw() {
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.SIZE * 4, 12);
        glVertexAttribPointer(2, 3, GL_FLOAT, false, Vertex.SIZE * 4, 20);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);
        glDisableVertexAttribArray(2);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(0);
    }

    private void calculateNormals(Vertex[] vertices, int[] indices) {
        for(int i = 0; i < indices.length; i += 3) {
            int i1 = indices[i];
            int i2 = indices[i + 1];
            int i3 = indices[i + 2];
            Vector3f v1 = vertices[i2].getPosition().sub(vertices[i1].getPosition());
            Vector3f v2 = vertices[i3].getPosition().sub(vertices[i1].getPosition());
            Vector3f normal = v1.cross(v2).normalized();
            vertices[i1].setNormal(vertices[i1].getNormal().add(normal));
            vertices[i2].setNormal(vertices[i2].getNormal().add(normal));
            vertices[i3].setNormal(vertices[i3].getNormal().add(normal));
        }
        for(int i = 0; i < vertices.length; i += 3)
            vertices[i].setNormal(vertices[i].getNormal().normalized());
    }
}