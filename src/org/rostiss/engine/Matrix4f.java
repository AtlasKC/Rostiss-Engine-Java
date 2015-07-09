package org.rostiss.engine;

import static java.lang.Math.*;

/**
 * File: Matrix4f.java
 * Created by Atlas IND on 7/7/15 at 3:56 PM.
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

public class Matrix4f {

    private float[][] matrix;

    public Matrix4f() {
        matrix = new float[4][4];
    }

    public Matrix4f initIdentity() {
        matrix[0][0] = 1; matrix[0][1] = 0; matrix[0][2] = 0; matrix[0][3] = 0;
        matrix[1][0] = 0; matrix[1][1] = 1; matrix[1][2] = 0; matrix[1][3] = 0;
        matrix[2][0] = 0; matrix[2][1] = 0; matrix[2][2] = 1; matrix[2][3] = 0;
        matrix[3][0] = 0; matrix[3][1] = 0; matrix[3][2] = 0; matrix[3][3] = 1;
        return this;
    }

    public Matrix4f initTranslation(float x, float y, float z) {
        matrix[0][0] = 1; matrix[0][1] = 0; matrix[0][2] = 0; matrix[0][3] = x;
        matrix[1][0] = 0; matrix[1][1] = 1; matrix[1][2] = 0; matrix[1][3] = y;
        matrix[2][0] = 0; matrix[2][1] = 0; matrix[2][2] = 1; matrix[2][3] = z;
        matrix[3][0] = 0; matrix[3][1] = 0; matrix[3][2] = 0; matrix[3][3] = 1;
        return this;
    }

    public Matrix4f initRotation(float x, float y, float z) {
        Matrix4f rotX = new Matrix4f();
        Matrix4f rotY = new Matrix4f();
        Matrix4f rotZ = new Matrix4f();
        x = (float)toRadians(x);
        y = (float)toRadians(y);
        z = (float)toRadians(z);
        rotX.matrix[0][0] = 1; rotX.matrix[0][1] = 0; rotX.matrix[0][2] = 0; rotX.matrix[0][3] = 0;
        rotX.matrix[1][0] = 0; rotX.matrix[1][1] = (float)cos(x); rotX.matrix[1][2] = -(float)sin(x); rotX.matrix[1][3] = 0;
        rotX.matrix[2][0] = 0; rotX.matrix[2][1] = (float)sin(x); rotX.matrix[2][2] = (float)cos(x); rotX.matrix[2][3] = 0;
        rotX.matrix[3][0] = 0; rotX.matrix[3][1] = 0; rotX.matrix[3][2] = 0; rotX.matrix[3][3] = 1;
        rotY.matrix[0][0] = (float)cos(y); rotY.matrix[0][1] = 0; rotY.matrix[0][2] = -(float)sin(y); rotY.matrix[0][3] = 0;
        rotY.matrix[1][0] = 0; rotY.matrix[1][1] = 1; rotY.matrix[1][2] = 0; rotY.matrix[1][3] = 0;
        rotY.matrix[2][0] = (float)sin(y); rotY.matrix[2][1] = 0; rotY.matrix[2][2] = (float)cos(y); rotY.matrix[2][3] = 0;
        rotY.matrix[3][0] = 0; rotY.matrix[3][1] = 0; rotY.matrix[3][2] = 0; rotY.matrix[3][3] = 1;
        rotZ.matrix[0][0] = (float)cos(z); rotZ.matrix[0][1] = -(float)sin(z); rotZ.matrix[0][2] = 0; rotZ.matrix[0][3] = 0;
        rotZ.matrix[1][0] = (float)sin(z); rotZ.matrix[1][1] = (float)cos(z); rotZ.matrix[1][2] = 0; rotZ.matrix[1][3] = 0;
        rotZ.matrix[2][0] = 0; rotZ.matrix[2][1] = 0; rotZ.matrix[2][2] = 1; rotZ.matrix[2][3] = 0;
        rotZ.matrix[3][0] = 0; rotZ.matrix[3][1] = 0; rotZ.matrix[3][2] = 0; rotZ.matrix[3][3] = 1;
        matrix = rotZ.mul(rotY.mul(rotX)).getMatrix();
        return this;
    }

    public Matrix4f initScale(float x, float y, float z) {
        matrix[0][0] = x; matrix[0][1] = 0; matrix[0][2] = 0; matrix[0][3] = 0;
        matrix[1][0] = 0; matrix[1][1] = y; matrix[1][2] = 0; matrix[1][3] = 0;
        matrix[2][0] = 0; matrix[2][1] = 0; matrix[2][2] = z; matrix[2][3] = 0;
        matrix[3][0] = 0; matrix[3][1] = 0; matrix[3][2] = 0; matrix[3][3] = 1;
        return this;
    }

    public Matrix4f initProjection(float fov, float width, float height, float zNear, float zFar) {
        float tan = (float)tan(toRadians(fov / 2));
        float aspect = width / height;
        float range = zNear - zFar;
        matrix[0][0] = 1 / (tan * aspect); matrix[0][1] = 0; matrix[0][2] = 0; matrix[0][3] = 0;
        matrix[1][0] = 0; matrix[1][1] = 1 / tan; matrix[1][2] = 0; matrix[1][3] = 0;
        matrix[2][0] = 0; matrix[2][1] = 0; matrix[2][2] = (-zNear - zFar) / range; matrix[2][3] = 2 * zFar * zNear / range;
        matrix[3][0] = 0; matrix[3][1] = 0; matrix[3][2] = 1; matrix[3][3] = 0;
        return this;
    }

    public Matrix4f initCamera(Vector3f forward, Vector3f up) {
        Vector3f f = forward.normalize();
        Vector3f r = up.normalize().cross(f);
        Vector3f u = f.cross(r);
        matrix[0][0] = r.getX(); matrix[0][1] = r.getY(); matrix[0][2] = r.getZ(); matrix[0][3] = 0;
        matrix[1][0] = u.getX(); matrix[1][1] = u.getY(); matrix[1][2] = u.getZ(); matrix[1][3] = 0;
        matrix[2][0] = f.getX(); matrix[2][1] = f.getY(); matrix[2][2] = f.getZ(); matrix[2][3] = 0;
        matrix[3][0] = 0; matrix[3][1] = 0; matrix[3][2] = 0; matrix[3][3] = 1;
        return this;
    }

    public Matrix4f mul(Matrix4f matrix) {
        Matrix4f result = new Matrix4f();
        for(int x = 0; x < 4; x++)
            for(int y = 0; y < 4; y++)
                result.setValue(x, y, this.matrix[x][0] * matrix.getValue(0, y) + this.matrix[x][1] * matrix.getValue(1, y) + this.matrix[x][2] * matrix.getValue(2, y) + this.matrix[x][3] * matrix.getValue(3, y));
        return result;
    }

    public float[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(float[][] matrix) {
        this.matrix = matrix;
    }

    public float getValue(int x, int y) {
        return matrix[x][y];
    }

    public void setValue(int x, int y, float value) {
        matrix[x][y] = value;
    }
}