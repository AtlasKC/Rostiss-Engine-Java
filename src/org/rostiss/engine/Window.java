package org.rostiss.engine;

import java.awt.*;

import org.lwjgl.Sys;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * File: Window.java
 * Created by Atlas IND on 7/6/15 at 11:57 PM.
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

public class Window {

    private GLFWErrorCallback errorCallback;
    private String title;
    private int width, height;
    private long window;

    public Window(int width, int height, String title) {
        System.out.println("Welcome to Rostiss | LWJGL " + Sys.getVersion());
        this.width = width;
        this.height = height;
        this.title = title;
        try {
            init();
            loop();
            glfwDestroyWindow(window);
        } finally {
            glfwTerminate();
            errorCallback.release();
        }
    }

    private void init() {
        glfwSetErrorCallback(errorCallback = errorCallbackPrint(System.err));
        if(glfwInit() != GL_TRUE)
            throw new IllegalStateException("Error: GLFW - Failed to initialize GLFW");
        glfwWindowHint(GLFW_VISIBLE, GL_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if(window == NULL)
            throw new RuntimeException("Error: GLFW - Failed to create window");
        ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (GLFWvidmode.width(vidmode) - width) / 2, (GLFWvidmode.height(vidmode) - height) / 2);
        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    private void loop() {
        GLContext.createFromCurrent();
        glClearColor(1, 0, 0, 0);
        while(glfwWindowShouldClose(window) == GL_FALSE) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }
}