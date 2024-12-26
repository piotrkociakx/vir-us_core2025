package ru.xyz.vir.drive;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import ru.xyz.Vir_USCore;
import ru.xyz.function.Function;
import ru.xyz.perm.manager.Perm;
import ru.xyz.perm.perms.FilePerm;
import ru.xyz.vir.device.mouse.MouseClicker;
import ru.xyz.vir.exception.Vir_usException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.net.URL;

public class FileManager implements Function {

    @Override
    public Perm requiredPermisionLevel() {
        return new FilePerm();
    }

    private final File file;

    public FileManager(File file) {
        if(!Vir_USCore.contains(requiredPermisionLevel())) {
            throw new Vir_usException("Brak wymaganych uprawnień", "Brak wymaganych uprawnień");
        }
        if (file == null) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new Vir_usException("Nie udało się utworzyć pliku", e.getMessage());
            }
        }
        this.file = file;
    }

    public void uploadFile(String url) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost uploadFile = new HttpPost(url);
            FileEntity fileEntity = new FileEntity(file, ContentType.DEFAULT_BINARY);
            uploadFile.setEntity(fileEntity);

            HttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                System.out.println(EntityUtils.toString(responseEntity));
            }
        } catch (IOException e) {
            throw new Vir_usException("Nie udało się wysłać pliku: " + file.getAbsolutePath() + " na adres: " + url, e.getMessage());
        }
    }

    public void putData(String data) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(data);
        } catch (IOException e) {
            throw new Vir_usException("Nie udało się zapisać danych do pliku: " + file.getAbsolutePath(), e.getMessage());
        }
    }

    public String getData() {
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new Vir_usException("Nie udało się odczytać danych z pliku: " + file.getAbsolutePath(), e.getMessage());
        }
    }

    public void deleteFile() {
        if (!file.delete()) {
            throw new Vir_usException("Nie udało się usunąć pliku: " + file.getAbsolutePath(), "none");
        }
    }

    public static void downloadFile(String url, String destination) {
        try {
            Files.copy(new URL(url).openStream(), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new Vir_usException("Nie udało się pobrać pliku z adresu: " + url + " do lokalizacji: " + destination, e.getMessage());
        }
    }
}