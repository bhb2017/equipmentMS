package com.lib.equipment.manager.model;

public class Material {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material.id
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material.name
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material.specification
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    private String specification;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material.price
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    private Float price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material.rate
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    private Float rate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column material.remark
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    private String remark;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material.id
     *
     * @return the value of material.id
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material.id
     *
     * @param id the value for material.id
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material.name
     *
     * @return the value of material.name
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material.name
     *
     * @param name the value for material.name
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material.specification
     *
     * @return the value of material.specification
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    public String getSpecification() {
        return specification;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material.specification
     *
     * @param specification the value for material.specification
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material.price
     *
     * @return the value of material.price
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    public Float getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material.price
     *
     * @param price the value for material.price
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material.rate
     *
     * @return the value of material.rate
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    public Float getRate() {
        return rate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material.rate
     *
     * @param rate the value for material.rate
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    public void setRate(Float rate) {
        this.rate = rate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column material.remark
     *
     * @return the value of material.remark
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column material.remark
     *
     * @param remark the value for material.remark
     *
     * @mbg.generated Sat Sep 07 09:15:14 CST 2019
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}