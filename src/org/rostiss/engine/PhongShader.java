package org.rostiss.engine;

/**
 * File: PhongShader.java
 * Created by Atlas IND on 7/9/2015 at 6:23 PM.
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
public class PhongShader extends Shader {

    private static final PhongShader instance = new PhongShader();

    private static Vector3f ambientLight;

    private PhongShader() {
        super();
        addVertex(ResourceLoader.loadShader("phong.rvs"));
        addFragment(ResourceLoader.loadShader("phong.rfs"));
        compileShader();
        addUniform("transform");
        addUniform("baseColor");
        addUniform("ambientLight");
    }

    public void updateUniforms(Matrix4f world, Matrix4f projected, Material material) {
        if(material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTextures();
        setUniform4f("transform", projected);
        setUniform3f("baseColor", material.getColor());
        setUniform3f("ambientLight", ambientLight);
    }

    public static Vector3f getAmbientLight() {
        return ambientLight;
    }

    public static void setAmbientLight(Vector3f ambientLight) {
        PhongShader.ambientLight = ambientLight;
    }

    public static PhongShader getInstance() {
        return instance;
    }
}