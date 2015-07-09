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
    private Transform transform;

    public Game() {
        mesh = ResourceLoader.loadMesh("ring.obj");
        shader = new Shader();
        transform = new Transform();
        shader.addVertex(ResourceLoader.loadShader("basic.rvs"));
        shader.addFragment(ResourceLoader.loadShader("basic.rfs"));
        shader.compileShader();
        shader.addUniform("transform");
    }

    public void input() {
    }

    float tmp;

    public void update() {
        tmp += Time.getDelta();
        float SIN = (float) Math.sin(tmp);
        transform.setTranslation(SIN, 0, 0);
        transform.setRotation(0, (float) Math.sin(tmp) * 180, 0);
        transform.setScale(0.5f * (float) Math.sin(tmp), 0.5f * (float) Math.sin(tmp), 0.5f * (float) Math.sin(tmp));
    }

    public void render() {
        shader.bind();
        shader.setUniform4f("transform", transform.getTransformation());
        mesh.draw();
    }
}