package TreatmentHistory.DatabaseObjects;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Injection.class, Medicine.class}, version = 1, exportSchema = false)
public abstract class AllergyDatabase extends RoomDatabase {

    public abstract InjectionDao injectionDao();
    public abstract MedicineDao medicineDao();

    private static AllergyDatabase INSTANCE;

    static AllergyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AllergyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AllergyDatabase.class, "allergy_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
//            new AddOneMedicine(INSTANCE).execute();
        }
    };


    private static class AddOneMedicine extends AsyncTask<Void,Void,Void>{
        private final MedicineDao medDao;
        private final InjectionDao injDao;
        AddOneMedicine(AllergyDatabase db) {
            medDao = db.medicineDao();
            injDao = db.injectionDao();
        }
        @Override
        protected Void doInBackground(final Void... params){
            medDao.insert(new Medicine("Medicine Name", "50%"));
            injDao.insert(new Injection(1,0.1,"2017-11-11"));
            return null;
        }
    }
}
