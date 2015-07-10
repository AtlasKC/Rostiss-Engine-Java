package org.rostiss.engine;

import java.util.HashMap;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

/**
 * File: Shader.java
 * Created by Atlas IND on 7/8/15 at 5:12 PM.
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

@SuppressWarnings("deprecation")
public class Shader {

    private HashMap<String, Integer> uniforms;
    private int program;

    public Shader() {
        program = glCreateProgram();
        uniforms = new HashMap<>();
        if(program == 0) {
            System.err.println("Error: Could not glCreateProgram() in Shader class.");
            System.exit(-1);
        }
    }

    public void addUniform(String name) {
        int location = glGetUniformLocation(program, name);
        if(location == 0xFFFFFF) {
            System.err.println("Error: Could not find uniform " + name);
            System.exit(-1);
        }
        uniforms.put(name, location);
    }

    public void updateUniforms(Matrix4f world, Matrix4f projected, Material material) {}

    public void setUniform(String name, int value) {
        glUniform1i(uniforms.get(name), value);
    }

    public void setUniform(String name, float value) {
        glUniform1f(uniforms.get(name), value);
    }

    public void setUniform(String name, Vector3f value) {
        glUniform3f(uniforms.get(name), value.getX(), value.getY(), value.getZ());
    }

    public void setUniform(String name, Matrix4f value) {
        glUniformMatrix4(uniforms.get(name), true, Util.createFlippedBuffer(value));
    }

    public void bind() {
        glUseProgram(program);
    }

    public void compileShader() {
        glLinkProgram(program);
        if(glGetShader(program, GL_LINK_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(program, 1024));
            System.exit(-1);
        }
        glValidateProgram(program);
        if(glGetShader(program, GL_VALIDATE_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(program, 1024));
            System.exit(-1);
        }
    }

    public void addVertex(String text) {
        addProgram(text, GL_VERTEX_SHADER);
    }

    public void addGeometry(String text) {
        addProgram(text, GL_GEOMETRY_SHADER);
    }

    public void addFragment(String text) {
        addProgram(text, GL_FRAGMENT_SHADER);
    }

    private void addProgram(String text, int type) {
        int shader = glCreateShader(type);
        if(shader == 0) {
            System.err.println("Error: Could not glCreateShader(" + type + ") in Shader class.");
            System.exit(-1);
        }
        glShaderSource(shader, text);
        glCompileShader(shader);
        if(glGetShader(shader, GL_COMPILE_STATUS) == 0) {
            System.err.println(glGetShaderInfoLog(shader, 1024));
            System.exit(-1);
        }
        glAttachShader(program, shader);
    }
}