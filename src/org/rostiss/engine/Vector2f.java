package org.rostiss.engine;

import static java.lang.Math.*;

/**
 * File: Vector2f.java
 * Created by Atlas IND on 7/7/15 at 12:41 PM.
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

public class Vector2f {

    private float x, y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float length() {
        return (float)sqrt(x * x + y * y);
    }

    public float dot(Vector2f vector) {
        return x * vector.getX() + y * vector.getY();
    }

    public Vector2f normalized() {
        float length = length();
        return new Vector2f(x / length, y / length);
    }

    public Vector2f rotate(float degrees) {
        double angle = toRadians(degrees);
        double cos = cos(angle);
        double sin = sin(angle);
        return new Vector2f((float)(x * cos - y * sin), (float)(x * sin + y * cos));
    }

    public Vector2f add(Vector2f vector) {
        return new Vector2f(x + vector.getX(), y + vector.getY());
    }

    public Vector2f add(float value) {
        return new Vector2f(x + value, y + value);
    }

    public Vector2f sub(Vector2f vector) {
        return new Vector2f(x - vector.getX(), y - vector.getY());
    }

    public Vector2f sub(float value) {
        return new Vector2f(x - value, y - value);
    }

    public Vector2f mul(Vector2f vector) {
        return new Vector2f(x * vector.getX(), y * vector.getY());
    }

    public Vector2f mul(float value) {
        return new Vector2f(x * value, y * value);
    }

    public Vector2f div(Vector2f vector) {
        return new Vector2f(x / vector.getX(), y / vector.getY());
    }

    public Vector2f div(float value) {
        return new Vector2f(x / value, y / value);
    }

    public Vector2f abs() {
        return new Vector2f(Math.abs(x), Math.abs(y));
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String toString() {
        return "Vector2f: (" + x + ", " + y + ")";
    }
}