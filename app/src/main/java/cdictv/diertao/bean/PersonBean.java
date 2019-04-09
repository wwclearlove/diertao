package cdictv.diertao.bean;

import java.util.List;

public class PersonBean {

    /**
     * code : 1
     * data : {"name":"小新","sex":"男","phone":"18111111111","idcard":"511111111111111111","createtime":"2017-10-21","car":[{"id":1,"chepai":"川a11111"},{"id":2,"chepai":"川a22222"},{"id":3,"chepai":"川a3333"},{"id":4,"chepai":"川a1444"}]}
     */

    public int code;
    public DataBean data;

    public static class DataBean {
        /**
         * name : 小新
         * sex : 男
         * phone : 18111111111
         * idcard : 511111111111111111
         * createtime : 2017-10-21
         * car : [{"id":1,"chepai":"川a11111"},{"id":2,"chepai":"川a22222"},{"id":3,"chepai":"川a3333"},{"id":4,"chepai":"川a1444"}]
         */

        public String name;
        public String sex;
        public String phone;
        public String idcard;
        public String createtime;
        public List<CarBean> car;

        public static class CarBean {
            /**
             * id : 1
             * chepai : 川a11111
             */

            public int id;
            public String chepai;
        }
    }
}
