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
    private Camera camera;
    private Shader shader;
    private Material material;
    private Transform transform;

    public Game() {
        mesh = new Mesh();
        camera = new Camera();
        shader = PhongShader.getInstance();
        material = new Material(ResourceLoader.loadTexture("test.png"), new Vector3f(1, 1, 1));
        transform = new Transform();
        mesh.addVertices(new Vertex[] { new Vertex(new Vector3f(-1, -1, 0), new Vector2f(0, 0)), new Vertex(new Vector3f(0, 1, 0), new Vector2f(0.5f, 0)), new Vertex(new Vector3f(1, -1, 0), new Vector2f(1, 0)), new Vertex(new Vector3f(0, -1, 1), new Vector2f(0.5f, 1))}, new int[] { 3, 1, 0, 2, 1, 3, 0, 1, 2, 0, 2, 3 }, true);
        PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1, 1, 1), 0.8f), new Vector3f(1, 1, 1)));
        Transform.setProjection(75, Window.getWidth(), Window.getHeight(), 0.1f, 1000);
        Transform.setCamera(camera);
    }

    public void input() {
        camera.input();
    }

    float tmp;

    public void update() {
        tmp += Time.getDelta();
        float SIN = (float) Math.sin(tmp);
        transform.setTranslation(SIN, 0, 5);
        transform.setRotation(0, (float) Math.sin(tmp) * 180, 0);
    }

    public void render() {
        shader.bind();
        shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
        mesh.draw();
    }
}