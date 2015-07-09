package org.rostiss.engine;

/**
 * File: Camera.java
 * Created by Atlas IND on 7/9/2015 at 12:46 AM.
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

public class Camera {

    public static final Vector3f yAxis = new Vector3f(0, 1, 0);

    private Vector3f position, forward, up;

    public Camera() {
        this(new Vector3f(0, 0, 0), new Vector3f(0, 0, 1), new Vector3f(0, 1, 0));
    }

    public Camera(Vector3f position, Vector3f forward, Vector3f up) {
        this.position = position;
        this.forward = forward.normalize();
        this.up = up.normalize();
    }

    public void input() {
        float amount = (float)Time.getDelta() * 10;
        float rotation = (float)Time.getDelta() * 100;
        if(Input.getKey(Input.KEY_W))
            move(getForward(), amount);
        if(Input.getKey(Input.KEY_S))
            move(getForward(), -amount);
        if(Input.getKey(Input.KEY_A))
            move(getLeft(), amount);
        if(Input.getKey(Input.KEY_D))
            move(getRight(), amount);
        if(Input.getKey(Input.KEY_UP))
            rotateX(-rotation);
        if(Input.getKey(Input.KEY_DOWN))
            rotateX(rotation);
        if(Input.getKey(Input.KEY_LEFT))
            rotateY(-rotation);
        if(Input.getKey(Input.KEY_RIGHT))
            rotateY(rotation);
    }

    public void move(Vector3f direction, float amount) {
        position = position.add(direction.mul(amount));
    }

    public void rotateX(float angle) {
        Vector3f horizontal = yAxis.cross(forward).normalize();
        forward.rotate(angle, horizontal).normalize();
        up = forward.cross(horizontal).normalize();
    }

    public void rotateY(float angle) {
        Vector3f horizontal = yAxis.cross(forward).normalize();
        forward.rotate(angle, yAxis).normalize();
        up = forward.cross(horizontal).normalize();
    }

    public Vector3f getLeft() {
        return forward.cross(up).normalize();
    }

    public Vector3f getRight() {
        return up.cross(forward).normalize();
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getForward() {
        return forward;
    }

    public void setForward(Vector3f forward) {
        this.forward = forward;
    }

    public Vector3f getUp() {
        return up;
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }
}