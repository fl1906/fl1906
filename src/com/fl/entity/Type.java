package com.fl.entity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 风离
 * @Date: 2020/12/12/16:56
 * @Description:
 */
public class Type implements Serializable {

        private static  final long serialVersionUID = 1L;

        private int  tid;          // '类别的主键id',
        private String tname;    // '类别的名称',
        private String tinfo;   //'类别的描述',

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getTinfo() {
            return tinfo;
        }

        public void setTinfo(String tinfo) {
            this.tinfo = tinfo;
        }

        @Override
        public String toString() {
            return "Type{" +
                    "tid=" + tid +
                    ", tname='" + tname + '\'' +
                    ", tinfo='" + tinfo + '\'' +
                    '}';
        }
    }

