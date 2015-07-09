package org.rostiss.engine;

/**
 * File: BasicShader.java
 * Created by Atlas IND on 7/9/2015 at 5:57 PM.
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

public class BasicShader extends Shader {

    private static final BasicShader instance = new BasicShader();

    private BasicShader() {
        super();
        addVertex(ResourceLoader.loadShader("basic.rvs"));
        addFragment(ResourceLoader.loadShader("basic.rfs"));
        compileShader();
        addUniform("transform");
        addUniform("color");
    }

    public void updateUniforms(Matrix4f world, Matrix4f projected, Material material) {
        if(material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTextures();
        setUniform4f("transform", projected);
        setUniform3f("color", material.getColor());
    }

    public static BasicShader getInstance() {
        return instance;
    }
}