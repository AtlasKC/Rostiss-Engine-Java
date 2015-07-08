package org.rostiss.engine;

/**
 * File: Game.java
 * Created by Atlas IND on 7/7/15 at 1:18 AM.
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

public class Game {

    private Mesh mesh;
    private Shader shader;

    public Game() {
        mesh = new Mesh();
        shader = new Shader();
        mesh.addVertices(new Vertex[] { new Vertex(new Vector3f(-1, -1, 0)), new Vertex(new Vector3f(0, 1, 0)), new Vertex(new Vector3f(1, -1, 0)) });
        shader.addVertex(ResourceLoader.loadShader("basic.rvs"));
        shader.addFragment(ResourceLoader.loadShader("basic.rfs"));
        shader.compileShader();
    }

    public void input() {}

    public void update() {}

    public void render() {
        shader.bind();
        mesh.render();
    }
}