package cdictv.diertao.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "EtcBean")
public class EtcBean {

    @DatabaseField(columnName = "ectid")
    public String ectid;
    @DatabaseField(columnName = "ectnum")
    public String ectnum;
    @DatabaseField(columnName = "ectmoney")
    public String ectmoney;
    @DatabaseField(columnName = "ecttime")
    public String ecttime;

    public EtcBean() {
    }
}
