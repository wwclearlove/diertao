package cdictv.diertao.bean;

public class WDXXListBean {
    private int id;
    private int type;
    private String bjlx;
    private String yz;
    private String dqz;

    public WDXXListBean() {
    }

    public WDXXListBean(int id, int type, String bjlx, String yz, String dqz) {
        this.id = id;
        this.type = type;
        this.bjlx = bjlx;
        this.yz = yz;
        this.dqz = dqz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBjlx() {
        return bjlx;
    }

    public void setBjlx(String bjlx) {
        this.bjlx = bjlx;
    }

    public String getYz() {
        return yz;
    }

    public void setYz(String yz) {
        this.yz = yz;
    }

    public String getDqz() {
        return dqz;
    }

    public void setDqz(String dqz) {
        this.dqz = dqz;
    }

    @Override
    public String toString() {
        return "WDXXListBean{" +
                "id=" + id +
                ", type=" + type +
                ", bjlx='" + bjlx + '\'' +
                ", yz='" + yz + '\'' +
                ", dqz='" + dqz + '\'' +
                '}';
    }
}
