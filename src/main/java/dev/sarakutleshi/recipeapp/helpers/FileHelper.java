package dev.sarakutleshi.recipeapp.helpers;

public interface FileHelper {
    String uploadFile(String folder, String fileName, byte[] fileContent);
}