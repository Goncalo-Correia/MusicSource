package org.musicsource.codezillas.server;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;

public class ServerFileManager {

    private final File folder = new File("C:\\Directory1");
    private final String path = "C:\\Directory1";
    private String fileName;

    public ServerFileManager() {
        fileName = "";
        initServerDirectory();
    }

    public String[] listServerFilesForFolder() {
        String[] serverFileNames = new String[folder.listFiles().length];
        int index = 0;
        for (File fileEntry : folder.listFiles()) {
            serverFileNames[index] = fileEntry.getName();
            index++;
        }
        return serverFileNames;
    }

    public String serverFilesString() {
        String[] serverFiles = listServerFilesForFolder();
        String wrapper = "\n";
        String result = "";
        for (String file : serverFiles) {
            result += file + wrapper;
        }
        return result;
    }

    public void uploadFile(byte[] fileData, String fileName) {
        File file = new File(path + "/" + fileName);

        System.out.println("Download is starting...");

        try {
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file.getPath());
            out.write(fileData);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public byte[] downloadFile(String string) {
        File file = null;
        byte[] data = null;

        for (File fileEntry : folder.listFiles()) {
            if (fileEntry.getName().equals(string)) {
                file = fileEntry;
                fileName = fileEntry.getName();
            }
        }

        if (file != null){

            try {

                FileInputStream reader = new FileInputStream(file);
                data = reader.readAllBytes();

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        return data;
    }

    private void initServerDirectory() {
        if (!folder.exists()) {
            System.out.println("Created directory");
            folder.mkdir();
        }
    }

    public String getFileName() {
        return fileName;
    }
}
