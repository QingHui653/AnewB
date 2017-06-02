package newb.c.backend.model.basemodel;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "t_order")
public class TOrder implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "order_id")
    /*@GeneratedValue(strategy = GenerationType.IDENTITY)*/
    private Integer orderId;

    @Column(name = "user_id")
    private Integer userId;

    private String status;

    /**
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

	public TOrder(Integer orderId, Integer userId, String status) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.status = status;
	}

	public TOrder() {
		super();
	}

	
}