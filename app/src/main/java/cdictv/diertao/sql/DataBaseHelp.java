package cdictv.diertao.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import cdictv.diertao.bean.CarChongzhijiluBean;
import cdictv.diertao.bean.ChongzhiguanliBean;

public class DataBaseHelp extends OrmLiteSqliteOpenHelper {

    private static DataBaseHelp dataBaseHelp;

    private DataBaseHelp(Context context) {
        super(context, "dieertao.db", null, 1);
    }

    public static DataBaseHelp getDataBase(Context context){
        if(dataBaseHelp == null){
            dataBaseHelp = new DataBaseHelp(context);
        }
        return dataBaseHelp;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, CarChongzhijiluBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
