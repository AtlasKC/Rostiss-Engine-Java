package org.rostiss.engine;

import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWvidmode;

import java.nio.ByteBuffer;

import static org.lwjgl.glfw.Callbacks.errorCallbackPrint;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_TRUE;
import static org.lwjgl.system.MemoryUtil.NULL;

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

    private static GLFWErrorCallback errorCallback;
    private static String title;
    private static int width, height;
    private static long window;

    public static void create(int width, int height, String title) {
        System.out.println("Welcome to Rostiss | LWJGL " + Sys.getVersion());
        Window.width = width;
        Window.height = height;
        Window.title = title;
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
        glfwSwapInterval(0);
        glfwShowWindow(window);
    }

    public static long getWindow() { return window; }

    public static int getWidth() { return width; }

    public static int getHeight() { return height; }

    public static String getTitle() { return title; }

    public static boolean isCloseRequested() { return glfwWindowShouldClose(window) == GL_TRUE; }

    public static void clean() {
        glfwDestroyWindow(window);
        glfwTerminate();
    }
}