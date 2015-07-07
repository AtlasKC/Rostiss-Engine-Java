package org.rostiss.engine;

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

    public Quaternion(Vector3f axis, float angle) {
        float sinHalfAngle = (float)Math.sin(angle / 2);
        float cosHalfAngle = (float)Math.cos(angle / 2);
        this.x = axis.getX() * sinHalfAngle;
        this.y = axis.getY() * sinHalfAngle;
        this.z = axis.getZ() * sinHalfAngle;
        this.w = cosHalfAngle;
    }

    public Quaternion(Matrix4f rot) {
        float trace = rot.getValue(0, 0) + rot.getValue(1, 1) + rot.getValue(2, 2);
        if(trace > 0) {
            float s = 0.5f / (float)Math.sqrt(trace+ 1.0f);
            w = 0.25f / s;
            x = (rot.getValue(1, 2) - rot.getValue(2, 1)) * s;
            y = (rot.getValue(2, 0) - rot.getValue(0, 2)) * s;
            z = (rot.getValue(0, 1) - rot.getValue(1, 0)) * s;
        } else {
            if(rot.getValue(0, 0) > rot.getValue(1, 1) && rot.getValue(0, 0) > rot.getValue(2, 2)) {
                float s = 2.0f * (float)Math.sqrt(1.0f + rot.getValue(0, 0) - rot.getValue(1, 1) - rot.getValue(2, 2));
                w = (rot.getValue(1, 2) - rot.getValue(2, 1)) / s;
                x = 0.25f * s;
                y = (rot.getValue(1, 0) + rot.getValue(0, 1)) / s;
                z = (rot.getValue(2, 0) + rot.getValue(0, 2)) / s;
            } else if(rot.getValue(1, 1) > rot.getValue(2, 2)) {
                float s = 2.0f * (float)Math.sqrt(1.0f + rot.getValue(1, 1) - rot.getValue(0, 0) - rot.getValue(2, 2));
                w = (rot.getValue(2, 0) - rot.getValue(0, 2)) / s;
                x = (rot.getValue(1, 0) + rot.getValue(0, 1)) / s;
                y = 0.25f * s;
                z = (rot.getValue(2, 1) + rot.getValue(1, 2)) / s;
            } else {
                float s = 2.0f * (float)Math.sqrt(1.0f + rot.getValue(2, 2) - rot.getValue(0, 0) - rot.getValue(1, 1));
                w = (rot.getValue(0, 1) - rot.getValue(1, 0) ) / s;
                x = (rot.getValue(2, 0) + rot.getValue(0, 2) ) / s;
                y = (rot.getValue(1, 2) + rot.getValue(2, 1) ) / s;
                z = 0.25f * s;
            }
        }
        float length = (float)Math.sqrt(x * x + y * y + z * z + w * w);
        x /= length;
        y /= length;
        z /= length;
        w /= length;
    }

    public float length() {
        return (float)Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Quaternion normalized() {
        float length = length();
        return new Quaternion(x / length, y / length, z / length, w / length);
    }

    public Quaternion conjugate() {
        return new Quaternion(-x, -y, -z, w);
    }

    public Quaternion mul(float r) {
        return new Quaternion(x * r, y * r, z * r, w * r);
    }

    public Quaternion mul(Quaternion r) {
        float w_ = w * r.getW() - x * r.getX() - y * r.getY() - z * r.getZ();
        float x_ = x * r.getW() + w * r.getX() + y * r.getZ() - z * r.getY();
        float y_ = y * r.getW() + w * r.getY() + z * r.getX() - x * r.getZ();
        float z_ = z * r.getW() + w * r.getZ() + x * r.getY() - y * r.getX();
        return new Quaternion(x_, y_, z_, w_);
    }

    public Quaternion mul(Vector3f r) {
        float w_ = -x * r.getX() - y * r.getY() - z * r.getZ();
        float x_ =  w * r.getX() + y * r.getZ() - z * r.getY();
        float y_ =  w * r.getY() + z * r.getX() - x * r.getZ();
        float z_ =  w * r.getZ() + x * r.getY() - y * r.getX();
        return new Quaternion(x_, y_, z_, w_);
    }

    public Quaternion sub(Quaternion r) {
        return new Quaternion(x - r.getX(), y - r.getY(), z - r.getZ(), w - r.getW());
    }

    public Quaternion add(Quaternion r) {
        return new Quaternion(x + r.getX(), y + r.getY(), z + r.getZ(), w + r.getW());
    }

    public float dot(Quaternion r) {
        return x * r.getX() + y * r.getY() + z * r.getZ() + w * r.getW();
    }

    public Quaternion nlerp(Quaternion dest, float lerpFactor, boolean shortest) {
        Quaternion correctedDest = dest;
        if(shortest && this.dot(dest) < 0)
            correctedDest = new Quaternion(-dest.getX(), -dest.getY(), -dest.getZ(), -dest.getW());
        return correctedDest.sub(this).mul(lerpFactor).add(this).normalized();
    }

    public Quaternion slerp(Quaternion dest, float lerpFactor, boolean shortest) {
        final float EPSILON = 1e3f;
        float cos = this.dot(dest);
        Quaternion correctedDest = dest;
        if(shortest && cos < 0) {
            cos = -cos;
            correctedDest = new Quaternion(-dest.getX(), -dest.getY(), -dest.getZ(), -dest.getW());
        }
        if(Math.abs(cos) >= 1 - EPSILON)
            return nlerp(correctedDest, lerpFactor, false);
        float sin = (float)Math.sqrt(1.0f - cos * cos);
        float angle = (float)Math.atan2(sin, cos);
        float invSin =  1.0f / sin;
        float srcFactor = (float)Math.sin((1.0f - lerpFactor) * angle) * invSin;
        float destFactor = (float)Math.sin((lerpFactor) * angle) * invSin;
        return this.mul(srcFactor).add(correctedDest.mul(destFactor));
    }

    /*public Vector3f getForward() {
        return new Vector3f(0, 0, 1).rotate(this);
    }

    public Vector3f getBack() {
        return new Vector3f(0, 0, -1).rotate(this);
    }

    public Vector3f getUp() {
        return new Vector3f(0, 1, 0).rotate(this);
    }

    public Vector3f getDown() {
        return new Vector3f(0, -1, 0).rotate(this);
    }

    public Vector3f getRight() {
        return new Vector3f(1, 0, 0).rotate(this);
    }

    public Vector3f getLeft() {
        return new Vector3f(-1, 0, 0).rotate(this);
    }*/

    public Quaternion set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    public Quaternion set(Quaternion r) {
        set(r.getX(), r.getY(), r.getZ(), r.getW());
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

    public boolean equals(Quaternion r) {
        return x == r.getX() && y == r.getY() && z == r.getZ() && w == r.getW();
    }
}