package id.duakoma.com.meetsquare;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    id.duakoma.com.meetsquare.http.Process process = new id.duakoma.com.meetsquare.http.Process();
    TableLayout tableLayout;
    Button buttonTambahDdata;
    ArrayList<Button> buttonEdit = new ArrayList<Button>();
    ArrayList<Button>buttonDelete = new ArrayList<Button>();
    JSONArray arrayData;
    Calendar myCalendar = Calendar.getInstance();
    TimePickerDialog picker;
    String[] items = {"Senin","Selasa", "Rabu","Kamis","Jumat"};
    TextView txtHome,txtAccount;
    CarouselView carouselView;
    Button buttonTambahData;
    int[] sampleImages = {R.drawable.ruang_a, R.drawable.ruang_a2, R.drawable.ruang_a3};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Jika SDK Android diatas API Ver.9
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        // Mendapatkan data widget dari XML Activity melalui ID
        tableLayout = (TableLayout) findViewById(R.id.tableBiodata);
        buttonTambahDdata = (Button) findViewById(R.id.buttonTambahBiodata);
        buttonTambahDdata.setOnClickListener(this);
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);

        carouselView.setImageListener(imageListener);
        // Menambahkan baris untuk tabel
        TableRow barisTabel = new TableRow(this);
        barisTabel.setBackgroundColor(Color.CYAN);


        // Menambahkan tampilan teks untuk judul pada tabel
        TextView viewHeaderId = new TextView(this);
        TextView viewHeaderDay = new TextView(this);
        TextView viewHeaderDate = new TextView(this);
        TextView viewHeaderTime = new TextView(this);
        TextView viewHeaderUnit = new TextView(this);
        TextView viewHeaderAgenda = new TextView(this);
        TextView viewHeaderPic = new TextView(this);
        TextView viewHeaderAction = new TextView(this);

        viewHeaderId.setText("No");
        viewHeaderDay.setText("Hari");
        viewHeaderDate.setText("Tanggal");
        viewHeaderTime.setText("Waktu");
        viewHeaderUnit.setText("Unit");
        viewHeaderAgenda.setText("Agenda");
        viewHeaderPic.setText("PIC");
        viewHeaderAction.setText("Action");

        viewHeaderId.setPadding(5, 1, 5, 1);
        viewHeaderDay.setPadding(5, 1, 5, 1);
        viewHeaderDate.setPadding(5, 1, 5, 1);
        viewHeaderTime.setPadding(5, 1, 5, 1);
        viewHeaderUnit.setPadding(5, 1, 5, 1);
        viewHeaderAgenda.setPadding(5, 1, 5, 1);
        viewHeaderPic.setPadding(5, 1, 5, 1);
        viewHeaderAction.setPadding(5, 1, 5, 1);

        // Menampilkan tampilan TextView ke dalam tabel
        barisTabel.addView(viewHeaderId);
        barisTabel.addView(viewHeaderDay);
        barisTabel.addView(viewHeaderDate);
        barisTabel.addView(viewHeaderTime);
        barisTabel.addView(viewHeaderUnit);
        barisTabel.addView(viewHeaderAgenda);
        barisTabel.addView(viewHeaderPic);
        barisTabel.addView(viewHeaderAction);

        // Menyusun ukuran dari tabel
        tableLayout.addView(barisTabel, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        System.out.println("sebelum hit");
        try {
            System.out.println("masuk hit");

            // Mengubah data dari BiodataActivity yang berupa String menjadi array
//            arrayBiodata = new JSONArray(biodataActivity.tampilBiodata());
            arrayData = new JSONArray(process.getInfoData());
            String aa = arrayData.toString();
            System.out.println("aaaa: "+aa);
            for (int i = 0; i < arrayData.length(); i++) {
                JSONObject jsonChildNode = arrayData.getJSONObject(i);
                String day = jsonChildNode.optString("day");
                String sdate = jsonChildNode.optString("date");
                String time = jsonChildNode.optString("time");
                String unit = jsonChildNode.optString("unit");
                String agenda = jsonChildNode.optString("agenda");
                String pic = jsonChildNode.optString("pic");
                String id = jsonChildNode.optString("id_room");

                System.out.println("ooday : " + day );
                System.out.println("oodate : " + sdate );
                System.out.println("ootime : " + time );
                System.out.println("oounit : " + unit);
                System.out.println("ooagenda : " + agenda);
                System.out.println("oopic : " + pic);
                System.out.println("ooID : " + id);

                barisTabel = new TableRow(this);

                // Memberi warna pada baris tabel
                if (i % 2 == 0) {
                    barisTabel.setBackgroundColor(Color.LTGRAY);
                }

                TextView viewId = new TextView(this);
                viewId.setText(id);
                viewId.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewId);

                TextView viewDay = new TextView(this);
                viewDay.setText(day);
                viewDay.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewDay);

                TextView viewDate = new TextView(this);
                viewDate.setText(sdate);
                viewDate.setPadding(5,1,1,1);
                barisTabel.addView(viewDate);

                TextView viewTime = new TextView(this);
                viewTime.setText(time);
                viewTime.setPadding(5,1,1,1);
                barisTabel.addView(viewTime);

                TextView viewUnit = new TextView(this);
                viewUnit.setText(unit);
                viewUnit.setPadding(5, 1, 5, 1);
                barisTabel.addView(viewUnit);

                TextView viewAgenda = new TextView(this);
                viewAgenda.setText(agenda);
                viewAgenda.setPadding(5,1,5,1);
                barisTabel.addView(viewAgenda);

                TextView viewPic = new TextView(this);
                viewPic.setText(pic);
                viewPic.setPadding(5,1,1,1);
                barisTabel.addView(viewPic);

                // Menambahkan button Edit
                buttonEdit.add(i, new Button(this));
                buttonEdit.get(i).setId(Integer.parseInt(id));
                buttonEdit.get(i).setTag("Edit");
                buttonEdit.get(i).setText("Edit");
                buttonEdit.get(i).setOnClickListener(this);
                barisTabel.addView(buttonEdit.get(i));

                // Menambahkan tombol Delete
                buttonDelete.add(i, new Button(this));
                buttonDelete.get(i).setId(Integer.parseInt(id));
                buttonDelete.get(i).setTag("Delete");
                buttonDelete.get(i).setText("Delete");
                buttonDelete.get(i).setOnClickListener(this);
                barisTabel.addView(buttonDelete.get(i));

                tableLayout.addView(barisTabel, new TableLayout.LayoutParams
                        (TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void onClick (View view) {
        if (view.getId() == R.id.buttonTambahBiodata) {
//            tambahBiodata();
            tambahData();
        }
        else {
            for (int  i= 0; i < buttonEdit.size(); i++) {
                // Jika ingin mengedit data pada biodata
                if (view.getId() == buttonEdit.get(i).getId() && view.getTag().toString().trim().equals("Edit")) {
                    Toast.makeText(MainActivity.this, "Edit : " + buttonEdit.get(i).getId(), Toast.LENGTH_SHORT).show();
                    int id = buttonEdit.get(i).getId();
                    getDataByID(id);
                }
                // Menghapus data di Tabel
                else if (view.getId() == buttonDelete.get(i).getId() && view.getTag().toString().trim().equals("Delete")){
                    Toast.makeText(MainActivity.this, "Delete : " + buttonDelete.get(i).getId(), Toast.LENGTH_SHORT).show();
                    int id = buttonDelete.get(i).getId();
                    deleteData(id);
                }
            }
        }
    }

    private void tambahData() {
        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        final Button editDate = new Button(this);
        editDate.setHint("Tanggal");
        layoutInput.addView(editDate);

        final Button editDay = new Button(this);
        editDay.setHint("Hari");

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String dateFormat = "yyyy-MM-dd";
                String hari = "EEEE";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                editDate.setText(simpleDateFormat.format(myCalendar.getTime()));
                SimpleDateFormat simpleDateFormatt = new SimpleDateFormat(hari, Locale.US);
                //System.out.println("hari hari: "+simpleDateFormatt.format(myCalendar.getTime()));
                String dayFormat = simpleDateFormatt.format(myCalendar.getTime());
                //System.out.println("haridah: "+dayFormat);
                //System.out.println("print hari: "+dayFormat.replace("Thursday","Kamis"));
                if(dayFormat.equalsIgnoreCase("Monday")){
                    editDay.setText(dayFormat.replace("Monday","Senin"));
                }if(dayFormat.equalsIgnoreCase("Tuesday")){
                    editDay.setText(dayFormat.replace("Tuesday","Selasa"));
                } else if(dayFormat.equalsIgnoreCase("Wednesday")){
                    editDay.setText(dayFormat.replace("Wednesday","Rabu"));
                } else if(dayFormat.equalsIgnoreCase("Thursday")){
                    editDay.setText(dayFormat.replace("Thursday","Kamis"));
                }else if(dayFormat.equalsIgnoreCase("Friday")){
                    editDay.setText(dayFormat.replace("Friday","Jumat"));
                }else if(dayFormat.equalsIgnoreCase("Saturday")){
                    editDay.setText(dayFormat.replace("Saturday","Sabtu"));
                }else if(dayFormat.equalsIgnoreCase("Sunday")){
                    editDay.setText(dayFormat.replace("Sunday","Minggu"));
                }
            }
        };

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        layoutInput.addView(editDay);


        final Button editTime = new Button(this);
        editTime.setHint("Waktu");
        layoutInput.addView(editTime);
        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                editTime.setText(sHour + ":" + sMinute + ":" + "00");
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });

        final EditText editUnit = new EditText(this);
        editUnit.setHint("Unit");
        layoutInput.addView(editUnit);

        final EditText editAgenda = new EditText(this);
        editAgenda.setHint("Agenda");
        layoutInput.addView(editAgenda);

        final EditText editPic = new EditText(this);
        editPic.setHint("PIC");
        layoutInput.addView(editPic);

        // Membuat AlertDialog untuk menambahkan data pada Biodata
        AlertDialog.Builder builderInsertData= new AlertDialog.Builder(this);
        builderInsertData.setIcon(R.drawable.ic_menu_gallery);
        builderInsertData.setTitle("Masukan Data");
        builderInsertData.setView(layoutInput);
        builderInsertData.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sDay = editDay.getText().toString();
                String sDate = editDate.getText().toString();
                String sTime = editTime.getText().toString();
                String sUnit = editUnit.getText().toString();
                String sAgenda = editAgenda.getText().toString();
                String sPIC = editPic.getText().toString();

                String laporan  = process.insertData(sDay, sDate, sTime, sUnit, sAgenda, sPIC);
                Toast.makeText(MainActivity.this, laporan, Toast.LENGTH_SHORT).show();

                finish();
                startActivity(getIntent());
            }
        });

        builderInsertData.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertData.show();
    }


    public void deleteData (int id) {
        process.deleteData(id);
        finish();
        startActivity(getIntent());
    }

    // Mendapatkan Biodata melalui ID
    public void getDataByID (int id) {
        String day = null, sdate = null, time = null,
                unit = null, agenda = null, pic = null;
        JSONArray arrayPersonal;

        try {
            arrayPersonal = new JSONArray(process.getDataByID(id));
            for (int i  = 0; i < arrayPersonal.length(); i++) {
                JSONObject jsonChildNode = arrayPersonal.getJSONObject(i);
                day = jsonChildNode.optString("day");
                sdate = jsonChildNode.optString("date");
                time = jsonChildNode.optString("time");
                unit = jsonChildNode.optString("unit");
                agenda = jsonChildNode.optString("agenda");
                pic = jsonChildNode.optString("pic");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout layoutInput = new LinearLayout(this);
        layoutInput.setOrientation(LinearLayout.VERTICAL);

        // Membuat id tersembunyi pada AlertDialog
        final TextView viewId = new TextView(this);
        viewId.setText(String.valueOf(id));
        viewId.setTextColor(Color.TRANSPARENT);
        layoutInput.addView(viewId);

        final EditText editDay = new EditText(this);
        editDay.setText(day);
        layoutInput.addView(editDay);
        editDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                //set the title for alert dialog
                builder.setTitle("Pilih Hari: ");

                //set items to alert dialog. i.e. our array , which will be shown as list view in alert dialog
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override public void onClick(DialogInterface dialog, int item) {
                        //setting the button text to the selected itenm from the list
                        editDay.setText(items[item]);
                    }
                });

                //Creating CANCEL button in alert dialog, to dismiss the dialog box when nothing is selected
                builder .setCancelable(false)
                        .setNegativeButton("CANCEL",new DialogInterface.OnClickListener() {

                            @Override  public void onClick(DialogInterface dialog, int id) {
                                //When clicked on CANCEL button the dalog will be dismissed
                                dialog.dismiss();
                            }
                        });

                //Creating alert dialog
                AlertDialog alert =builder.create();

                //Showingalert dialog
                alert.show();
            }
        });

        final EditText editDate = new EditText(this);
        editDate.setText(sdate);
        layoutInput.addView(editDate);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String dateFormat = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.US);
                editDate.setText(simpleDateFormat.format(myCalendar.getTime()));
            }

        };
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        final EditText editTime = new EditText(this);
        editTime.setText(time);
        layoutInput.addView(editTime);

        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                editTime.setText(sHour + ":" + sMinute + ":" + "00");
                            }
                        }, hour, minutes, true);
                picker.show();
            }
        });

        final EditText editUnit = new EditText(this);
        editUnit.setText(unit);
        layoutInput.addView(editUnit);

        final EditText editAgenda = new EditText(this);
        editAgenda.setText(agenda);
        layoutInput.addView(editAgenda);

        final EditText editPic = new EditText(this);
        editPic.setText(pic);
        layoutInput.addView(editPic);

        // Membuat AlertDialog untuk mengubah data di Biodata
        AlertDialog.Builder builderEditBiodata = new AlertDialog.Builder(this);
        builderEditBiodata.setIcon(R.drawable.ic_menu_camera);
        builderEditBiodata.setTitle("Update Data");
        builderEditBiodata.setView(layoutInput);
        builderEditBiodata.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sDay = editDay.getText().toString();
                String sDate = editDate.getText().toString();
                String sTime = editTime.getText().toString();
                String sUnit = editUnit.getText().toString();
                String sAgenda = editAgenda.getText().toString();
                String sPIC = editPic.getText().toString();

                String laporan = process.updateData(viewId.getText().toString(), sDay, sDate,sTime,
                        sUnit, sAgenda, sPIC);

                Toast.makeText(MainActivity.this, laporan, Toast.LENGTH_SHORT).show();

                finish();
                startActivity(getIntent());
            }
        });

        // Jika tidak ingin mengubah data pada Biodata
        builderEditBiodata.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builderEditBiodata.show();
    }
/*
    @Override
    public void onBackPressed() {
        AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
        ab.setTitle("Keluar Aplikasi");
        ab.setMessage("Yakin ingin keluar dari aplikasi?");
        ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //if you want to kill app . from other then your main avtivity.(Launcher)
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

                //if you want to finish just current activity

                MainActivity.this.finish();
            }
        });
        ab.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        ab.show();
    }
    */
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };
    /*
       public void tambahBiodata() {
           LinearLayout layoutInput = new LinearLayout(this);
           layoutInput.setOrientation(LinearLayout.VERTICAL);


           final EditText editNama = new EditText(this);
           editNama.setHint("Nama");
           layoutInput.addView(editNama);

           LinearLayout newLinear = new LinearLayout(this);
           Spinner newSpinner = new Spinner(this);
           ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                   this, R.array.DayOnWeek, android.R.layout.simple_spinner_item);
           adapter.setDropDownViewResource
                   (android.R.layout.simple_spinner_dropdown_item);
           newSpinner.setAdapter(adapter);
           newLinear.addView(newSpinner);
           setContentView(newLinear);

        final EditText editAlamat = new EditText(this);
        editAlamat.setHint("Alamat");
        layoutInput.addView(editAlamat);

        // Membuat AlertDialog untuk menambahkan data pada Biodata
        AlertDialog.Builder builderInsertBiodata= new AlertDialog.Builder(this);
        builderInsertBiodata.setIcon(R.drawable.ic_menu_gallery);
        builderInsertBiodata.setTitle("Insert Biodata");
        builderInsertBiodata.setView(layoutInput);
        builderInsertBiodata.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nama = editNama.getText().toString();
                String alamat = editAlamat.getText().toString();
                System.out.println("Nama : " + nama + "Alamat : " + alamat);

                String laporan  = biodataActivity.insertBiodata(nama, alamat);
                Toast.makeText(MainActivity.this, laporan, Toast.LENGTH_SHORT).show();

                finish();
                startActivity(getIntent());
            }
        });

        builderInsertBiodata.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builderInsertBiodata.show();


    }
       */
    /*
        editDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                //set the title for alert dialog
                builder.setTitle("Pilih Hari: ");

                //set items to alert dialog. i.e. our array , which will be shown as list view in alert dialog
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override public void onClick(DialogInterface dialog, int item) {
                        //setting the button text to the selected itenm from the list
                        editDay.setText(items[item]);
                    }
                });

                //Creating CANCEL button in alert dialog, to dismiss the dialog box when nothing is selected
                builder .setCancelable(false)
                        .setNegativeButton("CANCEL",new DialogInterface.OnClickListener() {

                            @Override  public void onClick(DialogInterface dialog, int id) {
                                //When clicked on CANCEL button the dalog will be dismissed
                                dialog.dismiss();
                            }
                        });

                //Creating alert dialog
                AlertDialog alert =builder.create();

                //Showingalert dialog
                alert.show();
            }
        });
        */
}

