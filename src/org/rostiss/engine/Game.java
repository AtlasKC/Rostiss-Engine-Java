package org.rostiss.engine;

import javax.swing.*;

import static org.lwjgl.opengl.GL11.*;

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

    public Game() {}

    public void input() {
        if(Input.getMouse(0))
            JOptionPane.showMessageDialog(null, Input.getMousePosition().toString());
    }

    public void update() {

    }

    public void render() {
        glBegin(GL_QUADS);
        glVertex2f(-0.5f, -0.5f);
        glVertex2f(-0.5f,  0.5f);
        glVertex2f( 0.5f,  0.5f);
        glVertex2f( 0.5f, -0.5f);
        glEnd();
    }
}