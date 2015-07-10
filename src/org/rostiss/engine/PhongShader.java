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

    private static Vector3f ambientLight = new Vector3f(0.1f, 0.1f, 0.1f);
    private static DirectionalLight directionalLight = new DirectionalLight(new BaseLight(new Vector3f(1, 1, 1), 0), new Vector3f(0, 0, 0));

    private PhongShader() {
        super();
        addVertex(ResourceLoader.loadShader("phong.rvs"));
        addFragment(ResourceLoader.loadShader("phong.rfs"));
        compileShader();
        addUniform("transform");
        addUniform("projected");
        addUniform("baseColor");
        addUniform("ambientLight");
        addUniform("specularIntensity");
        addUniform("specularExponent");
        addUniform("eyePosition");
        addUniform("directionalLight.baseLight.color");
        addUniform("directionalLight.baseLight.intensity");
        addUniform("directionalLight.direction");
    }

    public void updateUniforms(Matrix4f world, Matrix4f projected, Material material) {
        if(material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTextures();
        setUniform("transform", world);
        setUniform("projected", projected);
        setUniform("baseColor", material.getColor());
        setUniform("ambientLight", ambientLight);
        setUniform("directionalLight", directionalLight);
        setUniform("specularIntensity", material.getSpecularIntensity());
        setUniform("specularExponent", material.getSpecularExponent());
        setUniform("eyePosition", Transform.getCamera().getPosition());
    }

    public void setUniform(String name, BaseLight baseLight) {
        setUniform(name + ".color", baseLight.getColor());
        setUniform(name + ".intensity", baseLight.getIntensity());
    }

    public void setUniform(String name, DirectionalLight directionalLight) {
        setUniform(name + ".baseLight", directionalLight.getBaseLight());
        setUniform(name + ".direction", directionalLight.getDirection());
    }

    public static Vector3f getAmbientLight() {
        return ambientLight;
    }

    public static void setAmbientLight(Vector3f ambientLight) {
        PhongShader.ambientLight = ambientLight;
    }

    public static void setDirectionalLight(DirectionalLight directionalLight) {
        PhongShader.directionalLight = directionalLight;
    }

    public static PhongShader getInstance() {
        return instance;
    }
}