package mark.entity;

import java.util.Date;

public class Balance {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column balance.userid
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    private String userid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column balance.username
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column balance.amount
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    private Double amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column balance.detail
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    private String detail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column balance.update_by
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    private String updateBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column balance.update_time
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column balance.userid
     *
     * @return the value of balance.userid
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    public String getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column balance.userid
     *
     * @param userid the value for balance.userid
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column balance.username
     *
     * @return the value of balance.username
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column balance.username
     *
     * @param username the value for balance.username
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column balance.amount
     *
     * @return the value of balance.amount
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column balance.amount
     *
     * @param amount the value for balance.amount
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column balance.detail
     *
     * @return the value of balance.detail
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    public String getDetail() {
        return detail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column balance.detail
     *
     * @param detail the value for balance.detail
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column balance.update_by
     *
     * @return the value of balance.update_by
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column balance.update_by
     *
     * @param updateBy the value for balance.update_by
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column balance.update_time
     *
     * @return the value of balance.update_time
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column balance.update_time
     *
     * @param updateTime the value for balance.update_time
     *
     * @mbg.generated Sat Sep 24 20:12:26 CST 2016
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}