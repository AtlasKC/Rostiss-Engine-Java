package org.rostiss.engine;

import static java.lang.Math.*;

/**
 * File: Quaternion.java
 * Created by Atlas IND on 7/7/15 at 4:12 PM.
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

public class Quaternion {

    private float x;
    private float y;
    private float z;
    private float w;

    public Quaternion(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public float length() {
        return (float)sqrt(x * x + y * y + z * z + w * w);
    }

    public Quaternion normalize() {
        float length = length();
        return new Quaternion(x / length, y / length, z / length, w / length);
    }

    public Quaternion conjugate() {
        return new Quaternion(-x, -y, -z, w);
    }

    public Quaternion mul(float value) {
        return new Quaternion(x * value, y * value, z * value, w * value);
    }

    public Quaternion mul(Quaternion quaternion) {
        float dx = x * quaternion.getW() + w * quaternion.getX() + y * quaternion.getZ() - z * quaternion.getY();
        float dy = y * quaternion.getW() + w * quaternion.getY() + z * quaternion.getX() - x * quaternion.getZ();
        float dz = z * quaternion.getW() + w * quaternion.getZ() + x * quaternion.getY() - y * quaternion.getX();
        float dw = w * quaternion.getW() - x * quaternion.getX() - y * quaternion.getY() - z * quaternion.getZ();
        return new Quaternion(dx, dy, dz, dw);
    }

    public Quaternion mul(Vector3f vector) {
        float dw = -x * vector.getX() - y * vector.getY() - z * vector.getZ();
        float dx =  w * vector.getX() + y * vector.getZ() - z * vector.getY();
        float dy =  w * vector.getY() + z * vector.getX() - x * vector.getZ();
        float dz =  w * vector.getZ() + x * vector.getY() - y * vector.getX();
        return new Quaternion(dx, dy, dz, dw);
    }

    public Quaternion add(Quaternion quaternion) {
        return new Quaternion(x + quaternion.getX(), y + quaternion.getY(), z + quaternion.getZ(), w + quaternion.getW());
    }

    public Quaternion sub(Quaternion quaternion) {
        return new Quaternion(x - quaternion.getX(), y - quaternion.getY(), z - quaternion.getZ(), w - quaternion.getW());
    }

    public float dot(Quaternion quaternion) {
        return x * quaternion.getX() + y * quaternion.getY() + z * quaternion.getZ() + w * quaternion.getW();
    }

    public Quaternion nlerp(Quaternion quaternion, float factor, boolean shortest) {
        Quaternion destination = quaternion;
        if(shortest && this.dot(quaternion) < 0)
            destination = new Quaternion(-quaternion.getX(), -quaternion.getY(), -quaternion.getZ(), -quaternion.getW());
        return destination.sub(this).mul(factor).add(this).normalize();
    }

    public Quaternion slerp(Quaternion quaternion, float factor, boolean shortest) {
        final float EPSILON = 1e3f;
        float cos = this.dot(quaternion);
        Quaternion destination = quaternion;
        if(shortest && cos < 0) {
            cos = -cos;
            destination = new Quaternion(-quaternion.getX(), -quaternion.getY(), -quaternion.getZ(), -quaternion.getW());
        }
        if(abs(cos) >= 1 - EPSILON)
            return nlerp(destination, factor, false);
        float sin = (float)sqrt(1.0f - cos * cos);
        float angle = (float)atan2(sin, cos);
        float invSin =  1.0f / sin;
        float factor1 = (float)sin((1.0f - factor) * angle) * invSin;
        float factor2 = (float)sin((factor) * angle) * invSin;
        return this.mul(factor1).add(destination.mul(factor2));
    }

    public Quaternion set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    public Quaternion set(Quaternion quaternion) {
        set(quaternion.getX(), quaternion.getY(), quaternion.getZ(), quaternion.getW());
        return this;
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

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public boolean equals(Quaternion quaternion) {
        return x == quaternion.getX() && y == quaternion.getY() && z == quaternion.getZ() && w == quaternion.getW();
    }
}