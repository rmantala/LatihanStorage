package kominfo.go.id.latihanstorage;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity {
    public static final String FILENAME = "fileInternalStorage.txt";
    private TextView tvBaca;

    //ganti baris
    private String lineSeparator = System.getProperty("line.separator");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        //views to object
        Button btBuatFile = findViewById(R.id.btBuatFile);
        Button btUbahFile = findViewById(R.id.btUbahFile);
        Button btBacaFile = findViewById(R.id.btBacaFile);
        Button btHapusFile = findViewById(R.id.btHapusFile);
        tvBaca = findViewById(R.id.tvBaca);

        //event handler with lambda
        btBuatFile.setOnClickListener(v -> buatFile());

        btUbahFile.setOnClickListener(v -> ubahFile());

        btBacaFile.setOnClickListener(v -> bacaFile());

        btHapusFile.setOnClickListener(v -> hapusFile());

    }

    void buatFile() {
        String isiFile = "The Rolling Stones!";
        File file = new File(getFilesDir(), FILENAME);

        //isi file ditambah terus (karena append: true)
        try (FileOutputStream fos = new FileOutputStream(file, true)){
            fos.write(isiFile.getBytes());
            fos.write(lineSeparator.getBytes());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void ubahFile() {
        String isiFile = "The Beatless!";
        File file = new File(getFilesDir(), FILENAME);

        //isi file diganti (karena append: false)
        try (FileOutputStream fos = new FileOutputStream(file, false)){
            fos.write(isiFile.getBytes());
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void bacaFile() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);

        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();

                while (line != null) {
                    text.append(line);
                    text.append("\r\n");  //tambahkan baris baru
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            tvBaca.setText(text.toString());
        }
    }

    void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists() && file.delete()) {
            tvBaca.setText("");
        }
    }

}
