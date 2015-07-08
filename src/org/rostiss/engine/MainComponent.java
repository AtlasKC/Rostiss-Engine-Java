package org.rostiss.engine;

/**
 * File: MainComponent.java
 * Created by Atlas IND on 7/6/15 at 11:55 PM.
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

public class MainComponent {

    public static final String TITLE = "Rostiss Engine Java";
    public static final int WIDTH = 800, HEIGHT = 600;
    public static final double FRAME_CAP = 30.0;

    private Game game;
    private boolean running;

    public MainComponent() {
        RenderUtil.init();
        game = new Game();
        running = false;
    }

    public void start() {
        if(!running)
            run();
    }

    public void stop() {
        if(running) {
            running = false;
            clean();
        }
    }

    private void run() {
        running = true;
        final double frameTime = 1.0 / FRAME_CAP;
        double unprocessed = 0.0;
        int frames = 0;
        long previous = Time.getTime(), timer = 0;
        while (running) {
            boolean render = false;
            long current = Time.getTime();
            long difference = current - previous;
            previous = current;
            unprocessed += difference / (double)Time.SECOND;
            timer += difference;
            while(unprocessed > frameTime) {
                render = true;
                unprocessed -= frameTime;
                if (Window.isCloseRequested()) {
                    stop();
                    return;
                }
                Time.setDelta(timer);
                game.input();
                Input.update();
                game.update();
                if(timer >= Time.SECOND) {
                    System.out.println(frames + "fps");
                    frames = 0;
                    timer = 0;
                }
            }
            if(render) {
                render();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void render() {
        RenderUtil.clearScreen();
        game.render();
        Window.render();
    }

    private void clean() {
        Window.clean();
    }

    public static void main(String[] args) {
        Window.create(WIDTH, HEIGHT, TITLE);
        new MainComponent().start();
    }
}