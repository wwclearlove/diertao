package cdictv.diertao.bean;

import java.util.List;

public class WDXXPieBean {

    @Override
    public String toString() {
        return "WDXXPieBean{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }

    /**
     * code : 1
     * data : {"shuju":[{"id":1,"name":"pm2","cishu":9},{"id":2,"name":"guangzhao","cishu":11},{"id":3,"name":"wendu","cishu":7},{"id":4,"name":"shidu","cishu":2},{"id":5,"name":"co2","cishu":16}]}
     */

    private int code;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ShujuBean> shuju;

        @Override
        public String toString() {
            return "DataBean{" +
                    "shuju=" + shuju +
                    '}';
        }

        public List<ShujuBean> getShuju() {
            return shuju;
        }

        public void setShuju(List<ShujuBean> shuju) {
            this.shuju = shuju;
        }

        public static class ShujuBean {
            /**
             * id : 1
             * name : pm2
             * cishu : 9
             */

            private int id;
            private String name;
            private int cishu;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCishu() {
                return cishu;
            }

            public void setCishu(int cishu) {
                this.cishu = cishu;
            }
        }
    }
}
