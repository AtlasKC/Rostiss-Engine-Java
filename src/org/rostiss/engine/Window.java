package org.rostiss.engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

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

    public static void create(int width, int height, String title) {
        Display.setTitle(title);
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create();
            Keyboard.create();
            Mouse.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        System.out.println("Welcome to Rostiss | OpenGL: " + RenderUtil.getOpenGLVersion());
    }

    public static void render() {
        Display.update();
    }

    public static void clean() {
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
    }

    public static boolean isCloseRequested() { return Display.isCloseRequested(); }

    public static int getWidth() { return Display.getDisplayMode().getWidth(); }

    public static int getHeight() { return Display.getDisplayMode().getHeight(); }

    public static String getTitle() { return Display.getTitle(); }
}