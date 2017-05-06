package me.newo2001.util;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Color;

public class Geometry {
	
	/**
	 * Draw a triangle using OpenGL
	 * @param x The x-coordinate of the first vertex
	 * @param y The y-coordinate of the first vertex
	 * @param x2 The x-coordinate of the second vertex
	 * @param y2 The y-coordinate of the second vertex
	 * @param x3 The x-coordinate of the third vertex
	 * @param y3 The y-coordinate of the third vertex
	 * @param color The color of the triangle
	 * @see drawTriangleBorder
	 */
	public static void drawTriangle(int x, int y, int x2, int y2, int x3, int y3, Color color) {
		glColor3ub((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
		glBegin(GL_TRIANGLES);
			glVertex2i(x, y);
			glVertex2i(x2, y2);
			glVertex2i(x3, y3);
		glEnd();
	}
	
	/**
	 * Draw an empty triangle using OpenGL
	 * @param x The x-coordinate of the first vertex
	 * @param y The y-coordinate of the first vertex
	 * @param x2 The x-coordinate of the second vertex
	 * @param y2 The y-coordinate of the second vertex
	 * @param x3 The x-coordinate of the third vertex
	 * @param y3 The y-coordinate of the third vertex
	 * @param color The color of the triangle
	 * @see drawTriangle
	 */
	public static void drawTriangleBorder(int x, int y, int x2, int y2, int x3, int y3, Color color) {
		glColor3ub((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
		glBegin(GL_LINES);
			glVertex2i(x, y);
			glVertex2i(x2, y2);
		glEnd();
		glBegin(GL_LINES);
			glVertex2i(x2, y2);
			glVertex2i(x3, y3);
		glEnd();
		glBegin(GL_LINES);
			glVertex2i(x3, y3);
			glVertex2i(x, y);
		glEnd();
	}
	
	/**
	 * Draw an empty triangle using OpenGL
	 * @param x The x-coordinate of the first vertex
	 * @param y The y-coordinate of the first vertex
	 * @param x2 The x-coordinate of the second vertex
	 * @param y2 The y-coordinate of the second vertex
	 * @param x3 The x-coordinate of the third vertex
	 * @param y3 The y-coordinate of the third vertex
	 * @see drawTriangle
	 */
	public static void drawTriangleBorder(int x, int y, int x2, int y2, int x3, int y3) {
		drawTriangleBorder(x, y, x2, y2, x3, y3, new Color(0f, 0f, 0f));
	}
	
	/**
	 * Draw a rectangle using OpenGL
	 * @param x The x-coordinate to draw the rectangle at
	 * @param y The y-coordinate to draw the rectangle at
	 * @param width The width of the rectangle
	 * @param height The height of the rectangle
	 * @param color The color of the rectangle
	 * @see drawRectBorder
	 */
	public static void drawRect(int x, int y, int width, int height, Color color) {
		drawTriangle(x, y, x, y + height, x + width, y, color);
		drawTriangle(x + width, y + height, x, y + height, x + width, y, color);
	}
	
	/**
	 * Draw an empty rectangle using OpenGL
	 * @param x The x-coordinate to draw the rectangle at
	 * @param y The y-coordinate to draw the rectangle at
	 * @param width The width of the rectangle
	 * @param height The height of the rectangle
	 * @param color The color of the rectangle
	 * @see drawRect
	 */
	public static void drawRectBorder(int x, int y, int width, int height, Color color) {
		glColor3ub((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
		glBegin(GL_LINES);
			glVertex2i(x, y);
			glVertex2i(x + width, y);
		glEnd();
		glBegin(GL_LINES);
			glVertex2i(x + width, y);
			glVertex2i(x + width, y + height);
		glEnd();
		glBegin(GL_LINES);
			glVertex2i(x + width, y + height);
			glVertex2i(x, y + height);
		glEnd();
		glBegin(GL_LINES);
			glVertex2i(x, y + height);
			glVertex2i(x, y);
		glEnd();
	}
	
	/**
	 * Draw a rectangle using OpenGL
	 * @param x The x-coordinate to draw the rectangle at
	 * @param y The y-coordinate to draw the rectangle at
	 * @param width The width of the rectangle
	 * @param height The height of the rectangle
	 * @see drawRect
	 */
	public static void drawRectBorder(int x, int y, int width, int height) {
		drawRectBorder(x, y, width, height, new Color(0f, 0f, 0f));
	}
	
	/**
	 * Draw an approximate circle using OpenGL
	 * @param x The x-coordinate of the center of the circle
	 * @param y The y-coordinate of the center of the circle
	 * @param radius The radius of the circle
	 * @param color The color of the circle
	 * @param sections How many sections the circle should consist of (more is more accurate)
	 * @see drawCircleBorder
	 */
	public static void drawCircle(int x, int y, int radius, Color color, int sections) {
		glColor3ub((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
		glBegin(GL_TRIANGLE_FAN);
			double angle = 0;
			for (int i = 0; i < sections; i++) {
				angle = i * 2 * Math.PI / sections;
				glVertex2d(x + radius * Math.cos(angle), y + radius * Math.sin(angle));
			}
		glEnd();
	}
	
	/**
	 * Draw an approximate circle using OpenGL
	 * @param x The x-coordinate of the center of the circle
	 * @param y The y-coordinate of the center of the circle
	 * @param radius The radius of the circle
	 * @param color The color of the circle
	 * @see drawCircleBorder
	 */
	public static void drawCircle(int x, int y, int radius, Color color) {
		drawCircle(x, y, radius, color, 100);
	}
	
	/**
	 * Draw an approximate empty circle using OpenGL
	 * @param x The x-coordinate of the center of the circle
	 * @param y The y-coordinate of the center of the circle
	 * @param radius The radius of the circle
	 * @param color The color of the circle
	 * @param sections How many sections the circle should consist of (more is more accurate)
	 * @see drawCircle
	 */
	public static void drawCircleBorder(int x, int y, int radius, Color color, int sections) {
		glColor3ub((byte) color.getRed(), (byte) color.getGreen(), (byte) color.getBlue());
		glBegin(GL_LINE_LOOP);
			double angle = 0;
			for (int i = 0; i < sections; i++) {
				angle = i * 2 * Math.PI / sections;
				glVertex2d(x + radius * Math.cos(angle), y + radius * Math.sin(angle));
			}
		glEnd();
	}
	
	/**
	 * Draw an approximate empty circle using OpenGL
	 * @param x The x-coordinate of the center of the circle
	 * @param y The y-coordinate of the center of the circle
	 * @param radius The radius of the circle
	 * @param sections How many sections the circle should consist of (more is more accurate)
	 * @see drawCircle
	 */
	public static void drawCircleBorder(int x, int y, int radius, int sections) {
		drawCircleBorder(x, y, radius, Color.BLACK, sections);
	}
	
	/**
	 * Draw an approximate empty circle using OpenGL
	 * @param x The x-coordinate of the center of the circle
	 * @param y The y-coordinate of the center of the circle
	 * @param radius The radius of the circle
	 * @param color The color of the circle
	 * @see drawCircle
	 */
	public static void drawCircleBorder(int x, int y, int radius, Color color) {
		drawCircleBorder(x, y, radius, color, 100);
	}
	
	/**
	 * Draw an approximate empty circle using OpenGL
	 * @param x The x-coordinate of the center of the circle
	 * @param y The y-coordinate of the center of the circle
	 * @param radius The radius of the circle
	 * @see drawCircle
	 */
	public static void drawCircleBorder(int x, int y, int radius) {
		drawCircleBorder(x, y, radius, Color.BLACK, 100);
	}
}
