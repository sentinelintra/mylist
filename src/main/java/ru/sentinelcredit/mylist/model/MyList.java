package ru.sentinelcredit.mylist.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CX_MY_LIST")
public class MyList {
    @Id
    @GeneratedValue(generator = "my_list_seq")
    @GenericGenerator(name = "my_list_seq", strategy = "ru.sentinelcredit.mylist.model.MyListSeq")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String rowId;
    private String createdBy;
    private String lastUpdBy;
    private String dbLastUpdSrc;
    private String jobId;
    private String typeCd;
    private String status;
    private String comments;
    private String c30_1;
    private String c30_2;
    private String c30_3;
    private String c100_1;
    private String c100_2;
    private String c100_3;
    private String c200_1;
    private String c200_2;
    private String c200_3;
    private String c200_4;
    private String c200_5;
    private String c200_6;
    private String c200_7;
    private String c200_8;
    private String c200_9;
    private String c200_10;
    private String c200_11;
    private String c200_12;
    private String c200_13;
    private String c200_14;
    private String c200_15;
    private String c200_16;
    private String c200_17;
    private String c200_18;
    private String c200_19;
    private String c200_20;
    private String c200_21;
    private String c200_22;
    private String c200_23;
    private String c200_24;
    private String c200_25;
    private String c200_26;
    private String c200_27;
    private String c200_28;
    private String c200_29;
    private String c400_1;
    @Lob
    private String b1;

    public boolean setCColumn(String name, String value) {
        if (name.length() == 0 || value.length() == 0)
            return false;

        boolean returnValue = false;

        switch(name) {
            case "Column 30_1":
                setC30_1(value);
                returnValue = true;
                break;
            case "Column 30_2":
                setC30_2(value);
                returnValue = true;
                break;
            case "Column 30_3":
                setC30_3(value);
                returnValue = true;
                break;
            case "Column 100_1":
                setC100_1(value);
                returnValue = true;
                break;
            case "Column 100_2":
                setC100_1(value);
                returnValue = true;
                break;
            case "Column 100_3":
                setC100_3(value);
                returnValue = true;
                break;
            case "Column 200_1":
                setC200_1(value);
                returnValue = true;
                break;
            case "Column 200_2":
                setC200_2(value);
                returnValue = true;
                break;
            case "Column 200_3":
                setC200_3(value);
                returnValue = true;
                break;
            case "Column 200_4":
                setC200_4(value);
                returnValue = true;
                break;
            case "Column 200_5":
                setC200_5(value);
                returnValue = true;
                break;
            case "Column 200_6":
                setC200_6(value);
                returnValue = true;
                break;
            case "Column 200_7":
                setC200_7(value);
                returnValue = true;
                break;
            case "Column 200_8":
                setC200_8(value);
                returnValue = true;
                break;
            case "Column 200_9":
                setC200_9(value);
                returnValue = true;
                break;
            case "Column 200_10":
                setC200_10(value);
                returnValue = true;
                break;
            case "Column 200_11":
                setC200_11(value);
                returnValue = true;
                break;
            case "Column 200_12":
                setC200_12(value);
                returnValue = true;
                break;
            case "Column 200_13":
                setC200_13(value);
                returnValue = true;
                break;
            case "Column 200_14":
                setC200_14(value);
                returnValue = true;
                break;
            case "Column 200_15":
                setC200_15(value);
                returnValue = true;
                break;
            case "Column 200_16":
                setC200_16(value);
                returnValue = true;
                break;
            case "Column 200_17":
                setC200_17(value);
                returnValue = true;
                break;
            case "Column 200_18":
                setC200_18(value);
                returnValue = true;
                break;
            case "Column 200_19":
                setC200_19(value);
                returnValue = true;
                break;
            case "Column 200_20":
                setC200_20(value);
                returnValue = true;
                break;
            case "Column 200_21":
                setC200_21(value);
                returnValue = true;
                break;
            case "Column 200_22":
                setC200_22(value);
                returnValue = true;
                break;
            case "Column 200_23":
                setC200_23(value);
                returnValue = true;
                break;
            case "Column 200_24":
                setC200_24(value);
                returnValue = true;
                break;
            case "Column 200_25":
                setC200_25(value);
                returnValue = true;
                break;
            case "Column 200_26":
                setC200_26(value);
                returnValue = true;
                break;
            case "Column 200_27":
                setC200_27(value);
                returnValue = true;
                break;
            case "Column 200_28":
                setC200_28(value);
                returnValue = true;
                break;
            case "Column 200_29":
                setC200_29(value);
                returnValue = true;
                break;
            case "Column 400_1":
                setC400_1(value);
                returnValue = true;
                break;
        }

        return returnValue;
    }

    public String getCColumn(String name) {
        if (name.length() == 0)
            return "";

        String value = "";

        switch(name) {
            case "Column 30_1":
                value = getC30_1();
                break;
            case "Column 30_2":
                value = getC30_2();
                break;
            case "Column 30_3":
                value = getC30_3();
                break;
            case "Column 100_1":
                value = getC100_1();
                break;
            case "Column 100_2":
                value = getC100_1();
                break;
            case "Column 100_3":
                value = getC100_3();
                break;
            case "Column 200_1":
                value = getC200_1();
                break;
            case "Column 200_2":
                value = getC200_2();
                break;
            case "Column 200_3":
                value = getC200_3();
                break;
            case "Column 200_4":
                value = getC200_4();
                break;
            case "Column 200_5":
                value = getC200_5();
                break;
            case "Column 200_6":
                value = getC200_6();
                break;
            case "Column 200_7":
                value = getC200_7();
                break;
            case "Column 200_8":
                value = getC200_8();
                break;
            case "Column 200_9":
                value = getC200_9();
                break;
            case "Column 200_10":
                value = getC200_10();
                break;
            case "Column 200_11":
                value = getC200_11();
                break;
            case "Column 200_12":
                value = getC200_12();
                break;
            case "Column 200_13":
                value = getC200_13();
                break;
            case "Column 200_14":
                value = getC200_14();
                break;
            case "Column 200_15":
                value = getC200_15();
                break;
            case "Column 200_16":
                value = getC200_16();
                break;
            case "Column 200_17":
                value = getC200_17();
                break;
            case "Column 200_18":
                value = getC200_18();
                break;
            case "Column 200_19":
                value = getC200_19();
                break;
            case "Column 200_20":
                value = getC200_20();
                break;
            case "Column 200_21":
                value = getC200_21();
                break;
            case "Column 200_22":
                value = getC200_22();
                break;
            case "Column 200_23":
                value = getC200_23();
                break;
            case "Column 200_24":
                value = getC200_24();
                break;
            case "Column 200_25":
                value = getC200_25();
                break;
            case "Column 200_26":
                value = getC200_26();
                break;
            case "Column 200_27":
                value = getC200_27();
                break;
            case "Column 200_28":
                value = getC200_28();
                break;
            case "Column 200_29":
                value = getC200_29();
                break;
            case "Column 400_1":
                value = getC400_1();
                break;
        }

        return value;
    }
}
