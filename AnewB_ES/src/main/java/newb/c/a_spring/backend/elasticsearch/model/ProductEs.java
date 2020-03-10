package newb.c.a_spring.backend.elasticsearch.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qinghui
 * @Description: TODO
 * @date 2020/3/10 10:58
 */
@Document(indexName = "mini_product", type = "doc", createIndex = false)
public class ProductEs implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long productId;
    /**
     * 企业ID
     */
    private Long corpId;

    /**
     *创建时间
     */
    private Date createTime;
    /**
     *修改时间
     */
    private Date modifyTime;
    /**
     *价格
     */
    private BigDecimal price;
    /**
     *货品名
     */
    private String productName;
    /**
     * 货品销量
     */
    private BigDecimal productSales;
    /**
     *是否上架云店 0默认 1已上架
     */
    private Integer onlineStoreFlag;
    /**
     *云店上架时间
     */
    private Date  onlineStoreTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 条码
     */
    private String barcodes;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCorpId() {
        return corpId;
    }

    public void setCorpId(Long corpId) {
        this.corpId = corpId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getOnlineStoreFlag() {
        return onlineStoreFlag;
    }

    public void setOnlineStoreFlag(Integer onlineStoreFlag) {
        this.onlineStoreFlag = onlineStoreFlag;
    }

    public Date getOnlineStoreTime() {
        return onlineStoreTime;
    }

    public void setOnlineStoreTime(Date onlineStoreTime) {
        this.onlineStoreTime = onlineStoreTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBarcodes() {
        return barcodes;
    }

    public void setBarcodes(String barcodes) {
        this.barcodes = barcodes;
    }

    public BigDecimal getProductSales() {
        return productSales;
    }

    public void setProductSales(BigDecimal productSales) {
        this.productSales = productSales;
    }

    @Override
    public String toString() {
        return "ProductEs{" +
                "productId=" + productId +
                ", corpId=" + corpId +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", price=" + price +
                ", productName='" + productName + '\'' +
                ", productSales=" + productSales +
                ", onlineStoreFlag=" + onlineStoreFlag +
                ", onlineStoreTime=" + onlineStoreTime +
                ", remark='" + remark + '\'' +
                ", barcodes='" + barcodes + '\'' +
                '}';
    }
}
