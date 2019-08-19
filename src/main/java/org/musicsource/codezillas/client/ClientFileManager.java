package org.musicsource.codezillas.client;

import java.io.*;
import java.net.Socket;

public class ClientFileManager {

    private final File folder = new File("src/main/clientData");
    private final String path = "src/main/clientData";
    private String fileName;

    public ClientFileManager() {
        fileName = "";
    }

    public String[] listClientFilesForFolder() {
        String[] clientFileNames = new String[folder.listFiles().length];
        int index = 0;
        for (File fileEntry : folder.listFiles()) {
            clientFileNames[index] = fileEntry.getName();
            index++;
        }
        return clientFileNames;
    }

    public byte[] uploadFile(Integer selectedFile) {
        int index = 0;
        File file = null;
        byte[] data = null;

        for (File fileEntry : folder.listFiles()) {
            if (index == selectedFile - 1) {
                fileName = fileEntry.getName();
                file = fileEntry;
            }
            index++;
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

    public void downloadFile(byte[] fileData, String fileName) {
        File file = new File(path + "/" + fileName);

        System.out.println("Download is starting...");

        try {
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file.getPath());
            out.write(fileData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }
}
