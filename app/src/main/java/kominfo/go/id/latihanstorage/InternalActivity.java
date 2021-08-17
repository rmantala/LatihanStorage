package kominfo.go.id.latihanstorage;

import android.content.Context;
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
    private final String lineSeparator = System.getProperty("line.separator");
    private TextView tvBaca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        //views to object
        Button btBuatFile = findViewById(R.id.bt_buat_file);
        Button btUbahFile = findViewById(R.id.bt_ubah_file);
        Button btBacaFile = findViewById(R.id.bt_baca_file);
        Button btHapusFile = findViewById(R.id.bt_hapus_file);
        tvBaca = findViewById(R.id.tv_baca);

        //event handler with lambda
        btBuatFile.setOnClickListener(v -> buatFile());

        btUbahFile.setOnClickListener(v -> ubahFile());

        btBacaFile.setOnClickListener(v -> bacaFile());

        btHapusFile.setOnClickListener(v -> hapusFile());

    }

    void buatFile() {
        String isiFile = "Hai, saya adalah konten file - internal storage!";

        //method openFileOutput untuk membuat/membuka file di internal storage
        try (FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND)){
            fos.write(isiFile.getBytes());  //isiFile diubah ke bytes kemudian tulis ke file
            fos.write(lineSeparator.getBytes()); //tambahkan line separator
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void ubahFile() {
        String isiFileBaru = "Dirgahayu Republik Indonesia ke 76, Indonesia Bangkit!";

        //konten file diganti
        try (FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE)){
            fos.write(isiFileBaru.getBytes());
            fos.write(lineSeparator.getBytes());
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
