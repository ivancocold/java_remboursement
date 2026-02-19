package org.example;
import org.junit.Before;
import org.junit.Test;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.connectiontodatabase.*;
import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;

public class processcsv
{
    // Variables de configuration

    //Chemin vers le dossier de recherche des CSV
    public static final String DIRECTORY_PATH = "D:\projets\java\java_remboursement";
    //Dossier d'archivage des fichiers traités
    public static final String ARCHIVE_DIRECTORY = "D:\projets\java\java_remboursement";

    public static void processCsvFile(File file) throws IOException, CsvException, SQLException
    {
        // Extraire la date du nom de fichier
        String fileName = file.getName();
        String fileDateStr = fileName.substring(fileName.length() - 18, fileName.length() - 3);

        // Convertir la chaîne de date en objet java.sql.Timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmSS");
        Date parsedDate;
        try
        {
            parsedDate = dateFormat.parse(fileDateStr);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return; // Arrêter le traitement si la date ne peut pas être analysée
        }
        Timestamp timestamp = new Timestamp(parsedDate.getTime());

        try (CSVReader reader = new CSVReader(new FileReader(file)))
        {
            List<String[]> rows = reader.readAll();
            // La première ligne doit être une en-tête
            String[] header = rows.get(0);
            rows.remove(0);

            // Insert rows into the database

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
            {
                for (String[] row : rows)
                {
                    // Insérer la date du fichier dans la colonne Timestamp_fichier
                    upsertRow(connection, header, row, timestamp);
                }
            }

        }
        // Déplacer le fichier après traitement
        removefile.moveFile(file, ARCHIVE_DIRECTORY);
    }

    // Méthode utilitaire pour obtenir l'index d'une colonne dans le tableau de l'en-tête
    public static int getIndex(String[] header, String columnName)
    {
        for (int i = 0; i < header.length; i++)
        {
            if (header[i].equals(columnName))
            {
                return i;
            }
        }
        return -1; // Retourner -1 si la colonne n'est pas trouvée
    }

}
