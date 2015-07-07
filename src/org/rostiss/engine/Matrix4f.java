package org.rostiss.engine;

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

    public Matrix4f InitRotation(float x, float y, float z) {
        Matrix4f rx = new Matrix4f();
        Matrix4f ry = new Matrix4f();
        Matrix4f rz = new Matrix4f();
        x = (float)Math.toRadians(x);
        y = (float)Math.toRadians(y);
        z = (float)Math.toRadians(z);
        rz.matrix[0][0] = (float)Math.cos(z);   rz.matrix[0][1] = -(float)Math.sin(z);  rz.matrix[0][2] = 0;                    rz.matrix[0][3] = 0;
        rz.matrix[1][0] = (float)Math.sin(z);   rz.matrix[1][1] = (float)Math.cos(z);   rz.matrix[1][2] = 0;                    rz.matrix[1][3] = 0;
        rz.matrix[2][0] = 0;					rz.matrix[2][1] = 0;					rz.matrix[2][2] = 1;                    rz.matrix[2][3] = 0;
        rz.matrix[3][0] = 0;					rz.matrix[3][1] = 0;					rz.matrix[3][2] = 0;                    rz.matrix[3][3] = 1;
        rx.matrix[0][0] = 1;					rx.matrix[0][1] = 0;					rx.matrix[0][2] = 0;					rx.matrix[0][3] = 0;
        rx.matrix[1][0] = 0;					rx.matrix[1][1] = (float)Math.cos(x);   rx.matrix[1][2] = -(float)Math.sin(x);  rx.matrix[1][3] = 0;
        rx.matrix[2][0] = 0;					rx.matrix[2][1] = (float)Math.sin(x);   rx.matrix[2][2] = (float)Math.cos(x);   rx.matrix[2][3] = 0;
        rx.matrix[3][0] = 0;					rx.matrix[3][1] = 0;					rx.matrix[3][2] = 0;					rx.matrix[3][3] = 1;
        ry.matrix[0][0] = (float)Math.cos(y);   ry.matrix[0][1] = 0;				    ry.matrix[0][2] = -(float)Math.sin(y);  ry.matrix[0][3] = 0;
        ry.matrix[1][0] = 0;					ry.matrix[1][1] = 1;					ry.matrix[1][2] = 0;					ry.matrix[1][3] = 0;
        ry.matrix[2][0] = (float)Math.sin(y);   ry.matrix[2][1] = 0;				    ry.matrix[2][2] = (float)Math.cos(y);   ry.matrix[2][3] = 0;
        ry.matrix[3][0] = 0;					ry.matrix[3][1] = 0;					ry.matrix[3][2] = 0;					ry.matrix[3][3] = 1;
        matrix = rz.mul(ry.mul(rx)).getMatrix();
        return this;
    }

    public Matrix4f mul(Matrix4f matrix) {
        Matrix4f result = new Matrix4f();
        for(int x = 0; x < 4; x++)
            for(int y = 0; y < 4; y++)
                result.setMatrix(x, y, this.matrix[x][0] * matrix.getValue(0, y) + this.matrix[x][1] * matrix.getValue(1, y) + this.matrix[x][2] * matrix.getValue(2, y) + this.matrix[x][3] * matrix.getValue(3, y));
        return result;
    }

    public float[][] getMatrix() {
        return matrix;
    }

    public float getValue(int x, int y) {
        return matrix[x][y];
    }

    public void setMatrix(float[][] matrix) {
        this.matrix = matrix;
    }

    public void setMatrix(int x, int y, float value) {
        matrix[x][y] = value;
    }
}