package com.lib.equipment.manager.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class Material extends BaseRowModel {
    private Long id;

    @NotNull(message = "不能为空")
    @ExcelProperty(value = "器材名",index = 0)
    private String name;

    @NotNull(message = "不能为空")
    @ExcelProperty(value = "规格",index = 1)
    private String specification;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @DecimalMin("0.001")
    @NotNull
    @ExcelProperty(value = "单价",index = 2)
    private Float price;

    @Digits(integer = 4, fraction = 2)
    @ExcelProperty(value = "损耗率",index = 3)
    private Float rate;

    @ExcelProperty(value = "备注",index = 4)
    private String remark;

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                ", price=" + price +
                ", rate=" + rate +
                ", remark='" + remark + '\'' +
                '}';
    }
}