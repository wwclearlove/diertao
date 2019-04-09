package cdictv.diertao.bean;

import java.util.List;

public class ChongzhiguanliBean {

    /**
     * code : 1
     * data : [{"id":1,"chepai":"A17666","chezhu":"张一","money":100},{"id":2,"chepai":"A17777","chezhu":"张二","money":100},{"id":3,"chepai":"A17888","chezhu":"张三","money":100},{"id":4,"chepai":"A17999","chezhu":"张四","money":100}]
     */

    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 1
         * chepai : A17666
         * chezhu : 张一
         * money : 100
         */

        public int id;
        public String chepai;
        public String chezhu;
        public int money;

        public boolean flag = false;
    }
}
