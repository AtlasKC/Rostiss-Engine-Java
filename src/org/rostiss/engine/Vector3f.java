package org.rostiss.engine;

import static java.lang.Math.*;

/**
 * File: Vector3f.java
 * Created by Atlas IND on 7/7/15 at 3:50 PM.
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

public class Vector3f {

    private float x, y, z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float length() {
        return (float)sqrt(x * x + y * y + z * z);
    }

    public float dot(Vector3f vector) {
        return x * vector.getX() + y * vector.getY() + z * vector.getZ();
    }

    public Vector3f cross(Vector3f vector) {
        float dx = y * vector.getZ() - z * vector.getY();
        float dy = z * vector.getX() - x * vector.getZ();
        float dz = x * vector.getY() - y * vector.getX();
        return new Vector3f(dx, dy, dz);
    }

    public Vector3f normalize() {
        float length = length();
        x /= length;
        y /= length;
        z /= length;
        return this;
    }

    public Vector3f rotate(float degrees, Vector3f axis) {
        float sin = (float)sin(toRadians(degrees / 2));
        float cos = (float)cos(toRadians(degrees / 2));
        float rX = axis.getX() * sin;
        float rY = axis.getY() * sin;
        float rZ = axis.getZ() * sin;
        float rW = cos;
        Quaternion quaternion = new Quaternion(rX, rY, rZ, rW);
        Quaternion conjugate = quaternion.conjugate();
        Quaternion rotation = quaternion.mul(this).mul(conjugate);
        x = rotation.getX();
        y = rotation.getY();
        z = rotation.getZ();
        return this;
    }

    public Vector3f add(Vector3f vector) {
        return new Vector3f(x + vector.getX(), y + vector.getY(), z + vector.getZ());
    }

    public Vector3f add(float value) {
        return new Vector3f(x + value, y + value, z + value);
    }

    public Vector3f sub(Vector3f vector) {
        return new Vector3f(x - vector.getX(), y - vector.getY(), z - vector.getZ());
    }

    public Vector3f sub(float value) {
        return new Vector3f(x - value, y - value, z - value);
    }

    public Vector3f mul(Vector3f vector) {
        return new Vector3f(x * vector.getX(), y * vector.getY(), z * vector.getZ());
    }

    public Vector3f mul(float value) {
        return new Vector3f(x * value, y * value, z * value);
    }

    public Vector3f div(Vector3f vector) {
        return new Vector3f(x / vector.getX(), y / vector.getY(), z / vector.getZ());
    }

    public Vector3f div(float value) {
        return new Vector3f(x / value, y / value, z / value);
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

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}