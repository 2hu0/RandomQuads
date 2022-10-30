package com.example.demo.sample.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author 2hu0
 */
@TableName("record")
public class Record {

    @TableId(value = "record_id",type = IdType.AUTO)
    private Integer recordId;

    @TableField("record_user_name")
    private String recordName;

    @TableField("record_user_point")
    private Integer recordPoint;

    @TableField("record_time")
    private Date recordTime;

    public Record(Integer recordId, String recordName, Integer recordPoint, Date recordTime) {
        this.recordId = recordId;
        this.recordName = recordName;
        this.recordPoint = recordPoint;
        this.recordTime = recordTime;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public Integer getRecordPoint() {
        return recordPoint;
    }

    public void setRecordPoint(Integer recordPoint) {
        this.recordPoint = recordPoint;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Record() {
    }
}
