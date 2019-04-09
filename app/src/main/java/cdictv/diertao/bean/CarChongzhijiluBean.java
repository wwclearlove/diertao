package cdictv.diertao.bean;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;



@DatabaseTable(tableName = "CarChongzhijiluBean")
 public class CarChongzhijiluBean  {

     @DatabaseField(columnName = "carid",generatedId = true)
    public int carid;
    @DatabaseField(columnName = "carnum")
    public String carnum;
    @DatabaseField(columnName = "carmoney")
    public String carmoney;
    @DatabaseField(columnName = "carper")
    public String carper;
    @DatabaseField(columnName = "cartime")
    public String cartime;
}
